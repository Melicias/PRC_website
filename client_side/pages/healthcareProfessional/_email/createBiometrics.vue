<template>
    <div> 
        <b-container>
            <h2>Add Biometric Data: {{ this.$store.state.pacientName }}</h2>
            <b-form class="margin" @submit="onSubmit">
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
                <b-button variant="primary" :to="`/healthcareProfessional/${email}/patientManagement`">Back</b-button> 
                <b-button type="submit" variant="primary">Add Biometric Data</b-button>
            </b-form>         
        </b-container>
    </div>
</template>
<script>
export default {
  data() {
      return {
        form: {
          tipoDadosBiometrico: null,
          valor: ''
        },
        types: [{ text: 'Select One', value: null }],
        tipoDadosBiometricos:[]
      }
    },
    computed: {
      email() {
        return this.$route.params.email
      },
    },  
    methods: {
        onSubmit(event) {
          event.preventDefault()
          this.$axios.$post('/api/biometricdata/',{
            tipodadosBiometricos_id: this.form.tipoDadosBiometrico,
            utenteEmail: this.email,
            data_observacao: new Date().toISOString(),
            valor: this.form.valor
          }) 
          .then(msg => {
            this.$toast.success("Added Biometric Data with success! Pacient data result is " + msg).goAway(3000)
            this.form.tipoDadosBiometrico = null
            this.form.valor = ''
          })
          .catch(error => {
            this.$toast.error(error.response.data).goAway(3000)
            this.form.tipoDadosBiometrico = null
            this.form.valor = ''
          })
        },
      sendEmail(){
        this.$axios.$post(`/api/utente/${this.email}/send`, {
          subject: "You have a new Biometric Data added by the Healthcare: " + this.$auth.user.sub + "!",
          message: "Dear " + this.$store.state.pacientName + ", checkout your account and keep active on our platform please, you have a new Biometric added by the healthcare: " + this.$auth.user.sub + "!"
        })
          .then(msg => {
            this.$toast.success(msg).goAway(1500)
          })
          .catch(error => {
            this.$toast.error('error sending the e-mail').goAway(3000)
          })
      }
    },
    created (){
      if (!this.$auth.user.groups.includes('ProfissionalSaude')) {
        this.$router.push('not-found')
      }
      this.$axios.$get('/api/tipoDadosBiometricos/utente')
        .then((tipoDadosBiometricos) => {
            this.tipoDadosBiometricos = tipoDadosBiometricos
            for (let i = 0; i < tipoDadosBiometricos.length; i++) {
            if(tipoDadosBiometricos[i].deleted_at == null)
                this.types.push({text: tipoDadosBiometricos[i].name, value: tipoDadosBiometricos[i].id})
            }
        })
    } 
}
</script>
<style scoped>
.margin{
  margin-top: 30px;
}
</style>