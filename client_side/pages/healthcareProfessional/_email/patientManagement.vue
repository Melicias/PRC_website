<template>
    <div>
        <b-container>
          <h2 v-if="utente">Managing: {{ utente.name }}</h2>
          <b-button variant="primary" to="/healthcareProfessional/patientsManagement">Back</b-button>
          <b-card class="mt-3" header="Patient Data">
            <b-card-group deck class="mb-3">
              <b-card border-variant="light" header="Name:" class="text-center">
                <b-card-text>
                  <p>{{utente.name}}</p>
                </b-card-text>
              </b-card>
              <b-card border-variant="light" header="Email:" class="text-center">
                <b-card-text>
                  <p>{{utente.email}}</p>
                </b-card-text>
              </b-card>
            </b-card-group>
            <b-card-group deck class="mb-3">
              <b-card border-variant="light" header="Birthdate:" class="text-center">
                <b-card-text>
                  <p v-if="utente.dataNasc">{{utente.dataNasc.split('T')[0]}}</p>
                </b-card-text>
              </b-card>
              <b-card border-variant="light" header="Blocked:" class="text-center">
                <b-card-text>
                  <div v-if="utente.blocked == 0">
                    <p>No</p>
                  </div>
                  <div v-else>
                    <p>Yes</p>
                  </div>
                </b-card-text>
              </b-card>
            </b-card-group>
          </b-card>
          <b-card header-tag="nav">
            <template #header>
              <b-nav card-header tabs>
                <b-nav-item :active="tab === 1" @click="tab = 1">PRC's</b-nav-item>
                <b-nav-item :active="tab === 2" @click="tab = 2">Biometric Data</b-nav-item>
                <b-nav-item :active="tab === 3" @click="tab = 3">Specialists</b-nav-item>
              </b-nav>
            </template>
            <b-container v-if="tab == 1">
              <p v-if="utente.prcs">PRC's total: {{ utente.prcs.length }}</p>
              <b-button class="marginBottom" variant="primary" :to="`/healthcareProfessional/${email}/createPrc`">Create PRC</b-button>
              <b-card v-if="updatePrc" class="mt-3 margin" header="Update Prc">
                <b-form @reset="onReset">
                  <b-form-group
                      id="input-group-1"
                      label="Desease:"
                      label-for="input-1"
                  >
                      <b-form-input
                      id="input-1"
                      v-model="formUpdate.doenca"
                      type="text"
                      placeholder="Enter desease"
                      required
                      ></b-form-input>
                  </b-form-group>

                  <b-form-group id="input-group-2" label="Validade:" label-for="input-2">
                      <b-form-input
                      id="input-1"
                      v-model="formUpdate.validade"
                      type="date"
                      required
                      ></b-form-input>
                  </b-form-group>

                  <b-form-group id="input-group-2" label="Add Prescription:" label-for="input-2">
                    <b-form-select v-model="selectPrescriptionToAdd">
                      <b-form-select-option :value="null">To add select a prescription</b-form-select-option>
                      <b-form-select-option
                      v-for="prescriptionWithoutPrc in prescriptionsWithoutPrc"
                      :key="prescriptionWithoutPrc.id"
                      v-bind:value="prescriptionWithoutPrc.id"
                      >
                      {{ prescriptionWithoutPrc.name }}
                      </b-form-select-option>
                    </b-form-select>
                  </b-form-group>

                  <b-button class="marginBottom" @click="addPrescriptionToPrc" variant="success">Add</b-button>

                  <b-form-group id="input-group-2" label="Remove Prescription:" label-for="input-2">
                    <b-form-select v-model="selectPrescriptionToRemove">
                      <b-form-select-option :value="null">To remove select a prescription</b-form-select-option>
                      <b-form-select-option
                      v-for="prescriptionWithPrc in prescriptionsWithPrc"
                      :key="prescriptionWithPrc.id"
                      v-bind:value="prescriptionWithPrc.id"
                      >
                      {{ prescriptionWithPrc.name }}
                      </b-form-select-option>
                    </b-form-select>
                  </b-form-group>

                  <b-button class="marginBottom" @click="removePrescriptionFromPrc" variant="danger">Remove</b-button>
                  <div>
                    <b-button @click="updatePrc = false" variant="primary">Cancel</b-button>
                    <b-button @click="prcUpdate" variant="primary">Update</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
                  </div>

                </b-form>
              </b-card>
              <b-table striped hover sticky-header :items="utente.prcs" :fields="fieldsPrcs">
                <template v-slot:cell(Desease)="data">
                  <p>
                    {{data.item.doenca}}
                  </p>
                </template>
                <template v-slot:cell(CreatedAt)="data">
                  <p v-if="data.item.created_at">
                    {{data.item.created_at.split('T')[0]}}
                  </p>
                </template>
                <template v-slot:cell(Until)="data">
                  <p v-if="data.item.validade">
                    {{data.item.validade.split('T')[0]}}
                  </p>
                </template>
                <template v-slot:cell(Prescriptions)="data">
                  <p v-for="prescricao in data.item.prescricoes" :key="prescricao.descricao">
                    {{prescricao.descricao + " (" + prescricao.tipoPrescricao.name + ")"}}
                  </p>
                </template>
                <template v-slot:cell(Actions)="data">
                  <b-button variant="primary" @click="openUpdate(data.item)">Update</b-button>
                  <b-button :variant="data.item.deleted_at == null ? 'danger' : 'success'" @click="deletePrc(data.item.id)">{{data.item.deleted_at == null ? "Delete" : "Undo"}}</b-button>
                </template>
              </b-table>
            </b-container>
            <b-container v-if="tab == 2">
              <p v-if="utente.dadosBiometricos">Biometrics total: {{ utente.dadosBiometricos.length }}</p>
              <b-button class="marginBottom" variant="primary" :to="`/healthcareProfessional/${email}/createBiometrics`">Add Biometric Data</b-button>
              <b-table  striped hover sticky-header :items="utente.dadosBiometricos" :fields="fieldsBiometricData">
                <template v-slot:cell(BiometricDataType)="data">
                  <p>
                    {{data.item.tipoDadosBiometricos.name}}
                  </p>
                </template>
                <template v-slot:cell(Value)="data">
                  <p>
                    {{data.item.valor}}
                  </p>
                </template>
                <template v-slot:cell(Appraisal)="data">
                  <p>
                    {{data.item.avaliacao}}
                  </p>
                </template>
                <template v-slot:cell(CreationDate)="data">
                  <p v-if="data.item.created_at">
                    {{data.item.created_at.split('T')[0]}}
                  </p>
                </template>
                <template v-slot:cell(ObservationDate)="data">
                  <p v-if="data.item.data_observacao">
                    {{data.item.data_observacao.split('T')[0]}}
                  </p>
                </template>
              </b-table>
            </b-container>
            <b-container v-if="tab == 3">
              <p v-if="utente.profissionalSaude">Specialists total: {{ utente.profissionalSaude.length }}</p>
              <b-table  striped hover sticky-header :items="utente.profissionalSaude" :fields="fieldsSpecialists">
              </b-table>
            </b-container>
          </b-card>
        </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {
      utente: [],
      tab: 1,
      fieldsPrcs: ['Desease', 'CreatedAt', 'Until', 'Prescriptions', 'Actions'],
      fieldsBiometricData: ['BiometricDataType', 'Value', 'Appraisal', 'CreationDate', 'ObservationDate'],
      fieldsSpecialists: ['name', 'email'],
      formUpdate: {
        doenca: '',
        validade: ''
      },
      idPrc: '',
      prescriptionsWithoutPrc: [],
      selectPrescriptionToAdd: 'null',
      prescriptionsWithPrc: [],
      selectPrescriptionToRemove: 'null',
      updatePrc: false
    }
  },
  computed: {
    email() {
      return this.$route.params.email
    },
  },
  methods: {
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.formUpdate.doenca = ''
      this.formUpdate.validade = ''
      this.selectPrescriptionToAdd = 'null'
      this.selectPrescriptionToRemove = 'null'
    },
    prcUpdate(){
      this.$axios.$put('/api/prc', {
        id: this.idPrc,
        doenca: this.formUpdate.doenca,
        validade: new Date(this.formUpdate.validade).toISOString(),
      })
        .then(msg => {
          this.$toast.success("Prc updated with success").goAway(1500)
          this.fetchPacientProfile(this.utente.email)
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    addPrescriptionToPrc(){
      this.$axios.$post('/api/prc/addPrescription', {
        id: this.idPrc,
        ...(this.selectPrescriptionToAdd != null ? { idPrescricao: this.selectPrescriptionToAdd } : { idPrescricao: 0 })
      })
        .then(msg => {
          this.$toast.success("Prc updated with success").goAway(1500)
          this.fetchPacientProfile(this.utente.email)
          this.fetchPrescriptionWithoutPrc(this.idPrc)
          this.fetchPrescriptionWithPrc(this.idPrc)
          this.selectPrescriptionToAdd = 'null'
          this.selectPrescriptionToRemove = 'null'
        })
        .catch(error => {
         
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    removePrescriptionFromPrc(){
      this.$axios.$post('/api/prc/removePrescription', {
        id: this.idPrc,
        ...(this.selectPrescriptionToRemove != null ? { idPrescricao: this.selectPrescriptionToRemove } : { idPrescricao: 0 })
      })
        .then(msg => {
          this.$toast.success("Prc updated with success").goAway(1500)
          this.fetchPacientProfile(this.utente.email)
          this.fetchPrescriptionWithoutPrc(this.idPrc)
          this.fetchPrescriptionWithPrc(this.idPrc)
          this.selectPrescriptionToAdd = 'null'
          this.selectPrescriptionToRemove = 'null'
        })
        .catch(error => {
          
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    deletePrc(idPrc){
      this.$axios.$delete('/api/prc/' + idPrc)
        .then(msg => {
          this.$toast.success("Prc state changed with success").goAway(1500)
          this.fetchPacientProfile(this.utente.email)
        })
        .catch(error => {
        
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    fetchPacientProfile(pacientEmail){
      this.$axios.get("/api/utente/" + pacientEmail)
      .then((response) => {
        
        this.utente = response.data
        this.$store.commit('getPacientName', this.utente.name)
        
      })
    },
    fetchPrescriptionWithoutPrc(idPrc){
      this.$axios.get("/api/prescricao/semUmPrc/" + idPrc)
      .then((response) => {
       
        this.prescriptionsWithoutPrc = response.data
        
      })
    },
    fetchPrescriptionWithPrc(idPrc){
      this.$axios.get("/api/prescricao/comUmPrc/" + idPrc)
      .then((response) => {
        
        this.prescriptionsWithPrc = response.data
       
      })
    },
    openUpdate(prc){
      this.fetchPrescriptionWithoutPrc(prc.id)
      this.fetchPrescriptionWithPrc(prc.id)
      this.updatePrc = true
      this.idPrc = prc.id
      this.formUpdate.doenca = prc.doenca
      this.formUpdate.validade = prc.validade.split('T')[0]
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('ProfissionalSaude')) {
      this.$router.push('not-found')
    }
    this.fetchPacientProfile(this.email)
  }
}
</script>
<style scoped>
.marginBottom{
  margin-bottom: 16px;
}
</style>
