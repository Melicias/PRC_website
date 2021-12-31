<template>
    <div> 
        <b-container>
          <h1>Prescription Management</h1>
          <b-button variant="primary" to="/profissionalSaude/">Back</b-button> 
          <b-card class="mt-3 margin" header="Create a Prescription">
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
              <b-button type="submit" variant="primary">Submit</b-button>
              <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
          </b-card>
          <b-card class="mt-3" header="All Prescriptions">
            <b-card v-if="updatePrescription" class="mt-3 margin" header="Update Prescription">
              <b-form @reset="onReset" v-if="show">
                <b-form-group
                    id="input-group-1"
                    label="Name:"
                    label-for="input-1"
                >
                    <b-form-input
                    id="input-1"
                    v-model="formUpdate.name"
                    type="text"
                    placeholder="Enter name"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Description:" label-for="input-2">
                    <b-form-textarea
                      id="textarea"
                      v-model="formUpdate.descricao"
                      placeholder="Enter description"
                      rows="3"
                      max-rows="6"
                    ></b-form-textarea>
                </b-form-group>

                <b-form-group id="input-group-2" label="Prescription Type:" label-for="input-2">
                    <b-form-select v-model="selectTipoPrescriptionUpdate">
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
                <b-button @click="updatePrescription = false" variant="primary">Cancel</b-button>       
                <b-button @click="prescriptionUpdate" variant="primary">Update</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
              </b-form>
            </b-card>
            <b-table :items="prescriptions" :fields="fields">
              <template v-slot:cell(description)="data">
                <p>
                  {{data.item.descricao}}
                </p>
              </template>
              <template v-slot:cell(PrescriptionType)="data">
                <p>
                  {{data.item.tipoPrescricao.name}}
                </p>
              </template>
              <template v-slot:cell(Actions)="data">
                <b-button variant="primary" @click="openUpdate(data.item)">Update</b-button>
                <b-button :variant="data.item.deleted_at == null ? 'danger' : 'success'" @click="deletePrescription(data.item.id)">{{data.item.deleted_at == null ? "Delete" : "Undo"}}</b-button>
              </template>
            </b-table>
          </b-card>
        </b-container>  
    </div>
</template>
<script>
export default {
  data() {
      return {
        form: {
          name: '',
          descricao: '',
        },
        selectTipoPrescription: 'null',
        tiposPrescricao: [],
        prescriptions: [],
        fields: ['name', 'description', 'PrescriptionType', 'Actions'],
        updatePrescription: false,
        formUpdate: {
          name: '',
          descricao: ''
        },
        selectTipoPrescriptionUpdate: '',
        idPrescriptionUpdate: '',
        show: true
      }
    },
    methods: {
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
              this.fetchPrecriptions()
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
      },
      prescriptionUpdate(){
        this.$axios.$put('/api/prescricao', {
          id: this.idPrescriptionUpdate,
          name: this.formUpdate.name,
          descricao: this.formUpdate.descricao,
          tipoPrescricaoId: this.selectTipoPrescriptionUpdate
        })
          .then(msg => {
            this.$toast.success("Prescription updated with success").goAway(1500)
            this.updatePrescription = false
            this.fetchPrecriptions()
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
      deletePrescription(idPrescription){
        this.$axios.$delete('/api/prescricao/' + idPrescription)
          .then(msg => {
            this.$toast.success("Prescription state changed with success").goAway(1500)
            this.fetchPrecriptions()
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
      fetchPrecriptions(){
        this.$axios.$get('/api/prescricao')
        .then(response => {
          this.prescriptions = response
        })
      },
      openUpdate(prescription){
        this.updatePrescription = true
        this.idPrescriptionUpdate = prescription.id
        this.formUpdate.name = prescription.name
        this.formUpdate.descricao = prescription.descricao
        this.selectTipoPrescriptionUpdate = prescription.tipoPrescricao.id
      }
    },
    created (){
      this.$axios.$get('/api/tipoPrescricao')
        .then(response => {
          this.tiposPrescricao = response
        })
      this.fetchPrecriptions()
    } 
}
</script>
<style scoped>
.margin {
  margin-top: 30px;
}
</style>