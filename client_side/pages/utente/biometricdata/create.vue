<template>
  <b-container>
    <b-card class="mt-3" header="Update Patient">
        <b-form @submit="onSubmit">
      <b-form-group id="input-group-2" label="Type:" label-for="input-2">
          <b-form-select
            id="input-2"
            v-model="form.tipoDadosBiometrico"
            :disabled=false
            :options="types"
            required
          ></b-form-select>
        </b-form-group>
        <b-form-group id="input-group-1" label="Value: " label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.valor"
            type="number"
            placeholder="Enter the value"
            required
          ></b-form-input>
        </b-form-group>

    <br>
        <b-button type="submit" variant="primary">Add Biometric Data</b-button>
      <br>
     </b-form>
    <br><br>
      <b-button v-b-toggle.collapse-1 variant="primary" href="/utente/biometricdata">Back</b-button>
    </b-card>
     
    <br>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
    utente: {},
     form: {
       valor: '',
       tipoDadosBiometrico:null
      },
      types: [{ text: 'Select One', value: null }],
        tipoDadosBiometricos:[],
        selected: null,
        tipoDadoBiometrico:[]
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
      })
    this.$axios.$get('/api/tipoDadosBiometricos')
      .then((tipoDadosBiometricos) => {
        this.tipoDadosBiometricos = tipoDadosBiometricos
        for (let i = 0; i < tipoDadosBiometricos.length; i++) {
          if(tipoDadosBiometricos[i].deleted_at == null)
            this.types.push({text: tipoDadosBiometricos[i].name, value: tipoDadosBiometricos[i].id})
        }
      })
    },
     methods: {
      
        onSubmit(event) {
       event.preventDefault()
          this.$axios.$post('/api/biometricdata/',{
            tipodadosBiometricos_id:this.form.tipoDadosBiometrico,
            utente:this.utente,
            data_observacao: new Date().toISOString(),
            valor:this.form.valor
          }) .then(msg => {
          this.$toast.success("Professional type created with success").goAway(1500)
          })
        }}
}
</script>