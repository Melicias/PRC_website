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
      <b-button v-b-toggle.collapse-1 variant="primary" href="/patient/biometricdata">Back</b-button>
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
    if (!this.$auth.user.groups.includes('Utente')) {
      this.$router.push('not-found')
    }

    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        this.utente = utente || {}
      })

    this.$axios.$get('/api/tipoDadosBiometricos/utente')
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
            utenteEmail:this.utente.email,
            data_observacao: new Date().toISOString(),
            valor:this.form.valor
          }) .then(msg => {
          this.$toast.success("Add Biometric Data with success, your "+this.form.tipoDadosBiometricos+" is "+msg).goAway(1500)
          this.form.tipoDadosBiometricos=this.types[0]
          this.form.valor=''
          })
          .catch(error => {
                this.$toast.error(error.response.data).goAway(3000)
                this.form.tipoDadosBiometricos=this.types[0]
                this.form.valor=''
              })
        }}
}
</script>
