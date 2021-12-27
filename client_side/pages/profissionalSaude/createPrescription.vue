<template>
    <div> 
        <b-container>
            <h2>Create a Prescription</h2>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group
                    id="input-group-1"
                    label="Name:"
                    label-for="input-1"
                >
                    <b-form-input
                    id="input-1"
                    v-model="form.name"
                    type="text"
                    placeholder="Enter name"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Description:" label-for="input-2">
                    <b-form-textarea
                      id="textarea"
                      v-model="form.descricao"
                      placeholder="Enter description"
                      rows="3"
                      max-rows="6"
                    ></b-form-textarea>
                </b-form-group>

                <b-form-group id="input-group-2" label="Prescription Type:" label-for="input-2">
                    <b-form-select v-model="selectTipoPrescription">
                      <b-form-select-option :value="null">Please select an option</b-form-select-option>
                      <b-form-select-option  
                      v-for="tipoPrescricao in tiposPrescricao" 
                      :key="tipoPrescricao.id"
                      v-bind:value="tipoPrescricao.id"
                      >
                      {{ tipoPrescricao.name }}
                      </b-form-select-option>
                    </b-form-select>
                </b-form-group>
                
                <b-button class="btn-default" to="/profissionalSaude/main">Back</b-button> 
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
            
        </b-container>
        
    </div>
</template>
<script>
import moment from 'moment';
export default {
  data() {
      return {
        form: {
          name: '',
          descricao: '',
        },
        selectTipoPrescription: 'null',
        tiposPrescricao: [],
        show: true
      }
    },
    methods: {
      /* format (value) {
        return moment(value).format('YYYY-MM-DDThh:mm:ss')
      }, */
        onSubmit(event) {
          event.preventDefault()
          this.$axios.$post('/api/prescricao', {
            name: this.form.name,
            descricao: this.form.descricao,
            tipoPrescricaoId: this.selectTipoPrescription
          })
            .then(msg => {
              this.$toast.success("Prescription created with success").goAway(1500)
              this.form.name = ''
              this.form.descricao = ''
              // Trick to reset/clear native browser form validation state
              this.show = false
              this.$nextTick(() => {
                this.show = true
              })
            })
            .catch(error => {
              console.log(error.response.data)
              this.$toast.error(error.response.data).goAway(3000)
            })
        },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.name = ''
        this.form.descricao = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    },
    created (){
      this.$axios.$get('/api/tipoPrescricao')
        .then(response => {
          this.tiposPrescricao = response
        })
    } 
}
</script>
<style></style>