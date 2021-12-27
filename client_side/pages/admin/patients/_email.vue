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
      <b-button variant="danger" @click.prevent="deleteUtente()">{{utente.deleted_at == null ? "Delete" : "Recover"}}</b-button>
      <b-button variant="danger" @click.prevent="blockUtente()">{{utente.blocked == 0 ? "Block" : "Unblock"}}</b-button>
    </b-card>
    <br>
    <b-card header="Patient health specialists">
      <b-table striped over sticky-header :items="profissionaisUtente" :fields="fields" ref="tableCom">
        <template v-slot:cell(actions)="row">
          <b-button variant="danger" @click.prevent="removeSpecialist(`${row.item.email}`)">Remove</b-button>
        </template>
      </b-table>
    </b-card>
    <br>
    <b-card header="Other health specialists">
      <b-table striped over sticky-header :items="profissionaisNaoLigadosUtente" :fields="fields" ref="tableSem" >
        <template v-slot:cell(actions)="row">
          <b-button variant="success" @click.prevent="addSpecialist(`${row.item.email}`)">Add</b-button>
        </template>
      </b-table>
    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/patients">Back</b-button>
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
        this.utente = utente || {}
        this.profissionaisUtente = this.utente.profissionalSaude;
        this.form.name = this.utente.name
        this.form.dataNasc = this.utente.dataNasc != null ? this.utente.dataNasc.split('T')[0] : ""
      })
    this.$axios.$get(`/api/profissionalsaude/profissionaissemutente/${this.email}`)
      .then((profissionais) => {
        this.profissionaisNaoLigadosUtente = profissionais;
      })
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      var tipo = null;
      for (let i = 0; i < this.allTiposProfissionais.length; i++) {
        if(this.allTiposProfissionais[i].id == this.form.tipoProfissional){
          tipo = this.allTiposProfissionais[i];
          break;
        }
      }
      this.$axios.$put(`/api/profissionalsaude`, {
        email: this.profissionalsaude.email,
        tipoProfissional: tipo,
        name: this.form.name,
        password: this.form.password.length == 0 ? null : this.form.password,
      })
        .then(msg => {
          this.$toast.success("Specialist updated with success").goAway(1500)
          this.$router.push({path: "/admin/specialists"});
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
          this.$toast.error(error).goAway(3000)
        })
    }
  }
}
</script>
