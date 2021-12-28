<template>
  <b-container>
    <b-card class="mt-3" header="Update Patient">
      <b-form-group id="input-group-1" label="Email: " label-for="input-1">
        <b-form-input
          :disabled=true
          id="input-1"
          v-model="this.utente.email"
          type="text"
          placeholder="Enter email"
          required
        ></b-form-input>
      </b-form-group>
      <b-form @submit="onSubmit">
        <b-form-group id="input-group-2" label="Name:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.name"
            type="text"
            placeholder="Enter Name"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-3" label="Password:" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="form.password"
            type="password"
            placeholder="Enter new password"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-4" label="Birth date:" label-for="input-4">
          <b-form-input
            id="input-4"
            type="date"
            v-model="form.dataNasc"
            required
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">Update</b-button>
      </b-form>
      <br>
      <b-button variant="danger" @click.prevent="deleteUtente()">{{utente.deleted_at == undefined ? "Delete" : "Recover"}}</b-button>
      <b-button variant="danger" @click.prevent="blockUtente()">{{utente.blocked == 0 ? "Block" : "Unblock"}}</b-button>
    </b-card>
    <br>
    <b-card header="Patient health specialists">
      <b-table striped over sticky-header :items="profissionaisUtente" :fields="fields" ref="tableCom">
        <template v-slot:cell(speciality)="row">
          {{row.item.tipoProfissional != null ? row.item.tipoProfissional.name : ""}}
        </template>
        <template v-slot:cell(actions)="row">
          <b-button variant="danger" @click.prevent="removeSpecialist(`${row.item.email}`)">Remove</b-button>
        </template>
      </b-table>
    </b-card>
    <br>
    <b-card header="Other health specialists">
      <b-table striped over sticky-header :items="profissionaisNaoLigadosUtente" :fields="fields" ref="tableSem">
        <template v-slot:cell(speciality)="row">
          {{row.item.tipoProfissional != null ? row.item.tipoProfissional.name : ""}}
        </template>
        <template v-slot:cell(actions)="row">
          <b-button variant="success" @click.prevent="addSpecialist(`${row.item.email}`)">Add</b-button>
        </template>
      </b-table>
    </b-card>
    <br>
    <b-card header="Prcs">
      <b-table striped over sticky-header :items="prcs" :fields="fieldsPrc" ref="tablePrcs" >
        <template v-slot:cell(Disease)="row">
          {{row.item.doenca}}
        </template>
        <template v-slot:cell(specialist)="row">
          {{row.item.profissionalSaude.name}}
        </template>
        <template v-slot:cell(Until)="row">
          {{/\d{4}-\d{2}-\d{2}/.exec(row.item.validade)[0]}}
        </template>
        <template v-slot:cell(Prescriptions)="row">
          <p v-for="prescricao in row.item.prescricoes">
            {{prescricao.descricao + " (" + prescricao.tipoPrescricao.name + ")"}}
          </p>
        </template>
      </b-table>
    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/patients">Back</b-button>
    <br><br>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      utente: {},
      form: {
        name: '',
        password: '',
        dataNasc: ''
      },
      profissionaisUtente: [],
      profissionaisNaoLigadosUtente: [],
      fields: ['name', 'email', 'speciality', 'actions'],
      fieldsPrc: ['Disease', 'specialist', 'Prescriptions','Until'],
      prcs: [],
    }
  },
  computed: {
    email() {
      return this.$route.params.email
    },
  },
  created() {
    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        if(!utente.hasOwnProperty("deleted_at"))
          utente.deleted_at = undefined
        this.utente = utente || {}
        this.profissionaisUtente = this.utente.profissionalSaude;
        this.form.name = this.utente.name
        this.form.dataNasc = this.utente.dataNasc != null ? this.utente.dataNasc.split('T')[0] : ""
        this.prcs = this.utente.prcs
      })
    this.$axios.$get(`/api/profissionalsaude/profissionaissemutente/${this.email}`)
      .then((profissionais) => {
        this.profissionaisNaoLigadosUtente = profissionais;
      })
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.$axios.$put(`/api/utente`, {
        email: this.email,
        dataNasc: new Date(this.form.dataNasc).toISOString(),
        name: this.form.name,
        password: this.form.password.length == 0 ? null : this.form.password,
      })
        .then(msg => {
          this.$toast.success("Patient updated with success").goAway(1500)
          this.form.password = ''
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    addSpecialist(emailProf){
      this.$axios.post(`/api/utente/addprofissionalsaude`, {
        email: this.email,
        emailProfissionalSaude: emailProf
      })
        .then(msg => {
          this.$toast.success("Healthcare Specialist added with success").goAway(1500)
          for (let i = 0; i < this.profissionaisNaoLigadosUtente.length; i++) {
            if(this.profissionaisNaoLigadosUtente[i].email == emailProf){
              let t = this.profissionaisNaoLigadosUtente.splice(i,1)
              if(t.length > 0 ){
                this.profissionaisUtente.push(t[0])
              }
              break;
            }
          }
          this.$refs.tableCom.refresh();
          this.$refs.tableSem.refresh();
        })
        .catch(error => {
          this.$toast.error(error).goAway(3000)
        })
    },
    removeSpecialist(emailProf){
      this.$axios.post(`/api/utente/removeprofissionalsaude`, {
        email: this.email,
        emailProfissionalSaude: emailProf
      })
        .then(msg => {
          this.$toast.success("Healthcare Specialist removed with success").goAway(1500)
          for (let i = 0; i < this.profissionaisUtente.length; i++) {
            if(this.profissionaisUtente[i].email == emailProf){
              let t = this.profissionaisUtente.splice(i,1)
              if(t.length > 0 ){
                this.profissionaisNaoLigadosUtente.push(t[0]);
              }
              break;
            }
          }
          this.$refs.tableCom.refresh();
          this.$refs.tableSem.refresh();
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    deleteUtente() {
      this.$axios.$delete(`/api/utente/${this.email}`)
          .then(msg => {
            this.$toast.success("Patient deleted with success").goAway(1500)
            if(msg === ''){
              this.$router.push("/admin/patients")
            }
            this.utente.deleted_at = msg.deleted_at;
          })
          .catch(error => {
            this.$toast.error('error while deleting').goAway(3000)
          })
    },
    blockUtente() {
      this.$axios.put(`/api/utente/block/${this.email}`)
          .then(msg => {
            this.$toast.success("Patient deleted with success").goAway(1500)
            this.utente.blocked = msg.data.blocked
          })
          .catch(error => {
            this.$toast.error('error while deleting').goAway(3000)
          })
    }
  }
}
</script>
