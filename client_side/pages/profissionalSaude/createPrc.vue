<template>
    <div> 
        <b-container>
            <h2>Create PRC</h2>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group
                    id="input-group-1"
                    label="Disease:"
                    label-for="input-1"
                >
                    <b-form-input
                    id="input-1"
                    v-model="form.doenca"
                    type="text"
                    placeholder="Enter disease"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Validade:" label-for="input-2">
                    <b-form-input
                    id="input-1"
                    v-model="form.validade"
                    type="date"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Prescription:" label-for="input-2">
                    <b-form-group id="input-group-4">
                      <b-form-checkbox-group
                        v-model="select"
                        id="checkboxes-4"
                      >
                        <b-form-checkbox value="true">Select existing prescription</b-form-checkbox>
                        <b-form-checkbox value="false">Create new prescription</b-form-checkbox>
                      </b-form-checkbox-group>
                    </b-form-group>
                    <b-form-select v-if="select == 'true'" v-model="selectPrescription">
                      <b-form-select-option :value="null">Please select a prescription or create a new one</b-form-select-option>
                      <b-form-select-option  
                      v-for="prescricao in prescricoes" 
                      :key="prescricao.id"
                      v-bind:value="prescricao.id"
                      >
                      {{ prescricao.name }}
                      </b-form-select-option>
                    </b-form-select>
                    <b-container  v-if="select == 'false'">
                      <b-form @submit="onSubmitPrescription" @reset="onReset" v-if="show">
                          <b-form-group
                              id="input-group-1"
                              label="Name:"
                              label-for="input-1"
                          >
                              <b-form-input
                              id="input-1"
                              v-model="formPrescription.name"
                              type="text"
                              placeholder="Enter name"
                              required
                              ></b-form-input>
                          </b-form-group>

                          <b-form-group id="input-group-2" label="Description:" label-for="input-2">
                              <b-form-textarea
                                id="textarea"
                                v-model="formPrescription.descricao"
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
                          <b-button type="submit" variant="primary">Create</b-button>
                          <b-button type="reset" variant="danger">Reset</b-button>
                      </b-form>
                  </b-container>
                </b-form-group>     
                <b-button class="btn-default" to="/profissionalSaude/userManagement">Back</b-button> 
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
            
        </b-container>
        
    </div>
</template>
<script>
export default {
  data() {
      return {
        form: {
          doenca: '',
          validade: '',
        },
        select: "true",
        selectPrescription: 'null',
        prescricoes: [],
        formPrescription: {
          name: '',
          descricao: '',
        },
        selectTipoPrescription: 'null',
        tiposPrescricao: [],
        show: true
      }
    },  
    methods: {
        onSubmit(event) {
          event.preventDefault()
          this.$axios.$post('/api/prc', {
            doenca: this.form.doenca,
            validade: new Date(this.form.validade).toISOString(),
            idPrescricao: this.selectPrescription,
            emailUtente: "utente@utente.com",
            emailProfissionalSaude: "profissional@profissional.com"
          })
            .then(msg => {
              this.$toast.success("PRC created with success").goAway(1500)
              this.form.doenca = ''
              this.form.validade = ''
              this.selectPrescription = 'null'
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
        onSubmitPrescription(event){
          event.preventDefault()
            this.$axios.$post('/api/prescricao', {
              name: this.formPrescription.name,
              descricao: this.formPrescription.descricao,
              tipoPrescricaoId: this.selectTipoPrescription
            })
              .then(msg => {
                this.$toast.success("Prescription created with success").goAway(1500)
                this.formPrescription.name = ''
                this.formPrescription.descricao = ''
                this.selectTipoPrescription = 'null'
                this.getPrescriptions()
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
        this.form.doenca = ''
        this.form.validade = ''
        this.selectPrescription = 'null'
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      },
      getPrescriptions(){
        this.$axios.$get('/api/prescricao')
          .then(response => {
            this.prescricoes = response
          })
      }
    },
    created (){
      this.$axios.$get('/api/tipoPrescricao')
        .then(response => {
          this.tiposPrescricao = response
        })
      this.getPrescriptions()
    } 
}
</script>
<style scoped>
.margin{
  margin-top: 10px;
}
</style>