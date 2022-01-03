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
    </b-card>
    <br>
    <b-card header="Patient health specialists">
      <b-table striped over sticky-header :items="profissionaisUtente" :fields="fields" ref="tableCom">
        <template v-slot:cell(speciality)="row">
          {{row.item.tipoProfissional != null ? row.item.tipoProfissional.name : ""}}
        </template>
      </b-table>
    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/utente">Back</b-button>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
       utente: {},
      form: {
        email:"",
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
      return this.$auth.user.sub
    },
  },
  created() {
    console.log(this.email);
    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        this.utente = utente || {}
        this.profissionaisUtente = this.utente.profissionalSaude;
        console.log(this.profissionaisUtente)
        console.log(this.utente)
        this.form.name = this.utente.name
        this.form.dataNasc = this.utente.dataNasc != null ? this.utente.dataNasc.split('T')[0] : ""

      })
    },
     methods: {
   
        onSubmit(event) {
          event.preventDefault()
          this.$axios.$put('/api/utente', {
            email: this.email,
        dataNasc: new Date(this.form.dataNasc).toISOString(),
        name: this.form.name,
        password: this.form.password.length == 0 ? null : this.form.password,
          })
        }}
}
</script>