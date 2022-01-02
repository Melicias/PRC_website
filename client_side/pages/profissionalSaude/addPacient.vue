<template>
    <div>
        <b-container>
            <h1>Add Pacient To My Responsability</h1>
            <b-button variant="primary" to="/profissionalSaude/">Back</b-button>
            <b-card class="mt-3 margin" header="Add Pacient">
                <b-form-group
                    id="input-group-1"
                    label="Pacient email address:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="userEmail"
                    type="email"
                    placeholder="Enter email"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-button @click="onSubmit" variant="primary">Submit</b-button>
            </b-card>
            <b-card class="mt-3 margin" header="All Pacients">
                <b-table  striped hover selectable :items="utentesSemProfissional" :fields="fields">
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
      utentesSemProfissional: [],
      fields: ['Name', 'Email']
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.$axios.$post('/api/utente/addprofissionalsaude', {
        email: this.userEmail,
        emailProfissionalSaude: this.$auth.user.sub
      })
        .then(msg => {
          this.$toast.success("User added to your responsability with success").goAway(1500)
          this.userEmail = ''
          this.fecthUtentes()
        })
        .catch(error => {
          console.log(error.response.data)
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    fecthUtentes(){
        this.$axios.$get("/api/utente/semprofissional/"+this.$auth.user.sub)
        .then((utentes) => {
            this.utentesSemProfissional = utentes;
        })
    }
  },
  created () {
    this.fecthUtentes()
} }
</script>
<style scoped>
.margin{
    margin-top: 30px;
}
</style>