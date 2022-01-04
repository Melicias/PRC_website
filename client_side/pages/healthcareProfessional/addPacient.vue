<template>
    <div>
        <b-container>
            <h1>Add/Remove Patient To/From My Responsability</h1>
            <b-button variant="primary" to="/healthcareProfessional/">Back</b-button>
            <b-card class="mt-3 margin" header="Add Patient">
                <b-form-group
                    id="input-group-1"
                    label="Patient email address:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="userEmail"
                    type="email"
                    placeholder="Enter email"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-button @click="onSubmit" variant="primary">Add</b-button>
            </b-card>
            <b-card class="mt-3 margin" header="All Not Followed Patients">
                <b-table striped hover sticky-header :items="utentesSemProfissional" :fields="fields">
                    <template v-slot:cell(Name)="data">
                        <p>
                            {{data.item.name}}
                        </p>
                    </template>
                    <template v-slot:cell(Email)="data">
                        <p>
                            {{data.item.email}}
                        </p>
                    </template>
                    <template v-slot:cell(Actions)="data">
                        <b-button variant="success" @click="addPacient(data.item.email)">Add</b-button>
                    </template>
                </b-table>
            </b-card>
            <b-card class="mt-3 margin" header="Remove Patient">
                <b-form-group
                    id="input-group-1"
                    label="Patient email address:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="pacientEmail"
                    type="email"
                    placeholder="Enter email"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-button @click="onSubmitRemove" variant="primary">Remove</b-button>
            </b-card>
            <b-card class="mt-3 margin" header="All Followed Patients">
                <b-table striped hover sticky-header :items="utentesComProfissional" :fields="fields">
                    <template v-slot:cell(Name)="data">
                        <p>
                            {{data.item.name}}
                        </p>
                    </template>
                    <template v-slot:cell(Email)="data">
                        <p>
                            {{data.item.email}}
                        </p>
                    </template>
                    <template v-slot:cell(Actions)="data">
                        <b-button variant="danger" @click="removePacient(data.item.email)">Remove</b-button>
                    </template>
                </b-table>
            </b-card>
        </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {
      userEmail: '',
      pacientEmail: '',
      utentesSemProfissional: [],
      utentesComProfissional: [],
      fields: ['Name', 'Email', 'Actions']
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.$axios.$post('/api/profissionalsaude/addUtente/' + this.$auth.user.sub, {
        email: this.userEmail
      })
        .then(msg => {
          this.sendEmail()
          this.$toast.success("Pacient added to your responsability with success").goAway(1500)
          this.userEmail = ''
          this.fecthUtentes()
          this.fetchUtenteComProfissional()
        })
        .catch(error => {
      
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    addPacient(pacientEmail){
       this.$axios.$post('/api/profissionalsaude/addUtente/' + this.$auth.user.sub, {
        email: pacientEmail
      })
        .then(msg => {
          this.$toast.success("Pacient added to your responsability with success").goAway(1500)
          this.userEmail = ''
          this.sendEmail(pacientEmail)
          this.fecthUtentes()
          this.fetchUtenteComProfissional()
        })
        .catch(error => {
     
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    onSubmitRemove(event) {
      event.preventDefault()
      this.$axios.$post('/api/profissionalsaude/removeUtente/' + this.$auth.user.sub, {
        email: this.pacientEmail
      })
        .then(msg => {
          this.$toast.success("Pacient removed from your responsability with success").goAway(1500)
          this.pacientEmail = ''
          this.fecthUtentes()
          this.fetchUtenteComProfissional()
        })
        .catch(error => {
         
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    removePacient(pacientEmail){
       this.$axios.$post('/api/profissionalsaude/removeUtente/' + this.$auth.user.sub, {
        email: pacientEmail,
      })
        .then(msg => {
          this.$toast.success("Pacient removed from your responsability with success").goAway(1500)
          this.userEmail = ''
          this.fetchUtenteComProfissional()
          this.fecthUtentes()
        })
        .catch(error => {
        
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    fecthUtentes(){
        this.$axios.$get("/api/utente/semprofissional/"+this.$auth.user.sub)
        .then((utentes) => {
            this.utentesSemProfissional = utentes;
        })
    },
    fetchUtenteComProfissional(){
      this.$axios.$get('/api/utente/comprofissional/'+this.$auth.user.sub)
      .then((utentes) => {
    
        this.utentesComProfissional = utentes
      })
    },
    sendEmail(pacientEmail){
      this.$axios.$post(`/api/utente/${this.userEmail == '' ? pacientEmail : this.userEmail}/send`, {
        subject: "The Healthcare: " + this.$auth.user.sub + " is now following you",
        message: "Dear " + this.userEmail + " keep active in our platform you have a new Healthcare following you!"
      })
        .then(msg => {
          this.$toast.success(msg).goAway(1500)
        })
        .catch(error => {
          this.$toast.error('error sending the e-mail').goAway(3000)
        })
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('ProfissionalSaude')) {
      this.$router.push('not-found')
    }
    this.fecthUtentes()
    this.fetchUtenteComProfissional()
  }
}
</script>
<style scoped>
.margin{
    margin-top: 30px;
}
</style>
