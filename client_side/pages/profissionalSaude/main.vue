<template>
    <div>
        <b-container>
            <h2>Profissional de Saúde</h2>
            <b-button to="/profissionalSaude/createUser">Create new user</b-button>
            <b-button to="/profissionalSaude/usersManagement">Manage users</b-button>
            <b-button to="/profissionalSaude/createPrescription">Manage prescriptions</b-button>
            <b-button @click="addUser = true">Add user to my responsability</b-button>
            <b-button to="/">Logout</b-button>
            <b-container v-show="addUser">
              <b-form-group
                id="input-group-1"
                label="User email address:"
                label-for="input-1">
                  <b-form-input
                  id="input-1"
                  v-model="userEmail"
                  type="email"
                  placeholder="Enter email"
                  required
                  ></b-form-input>
              </b-form-group>
              <b-button class="btn-default" @click="addUser = false">Cancel</b-button> 
              <b-button @click="onSubmit" variant="primary">Submit</b-button>
            </b-container>
        </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {
      addUser: false,
      userEmail: '',
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.$axios.$post('/api/utente/addprofissionalsaude', {
        email: this.userEmail,
        emailProfissionalSaude: "profissional1@profissional1.com"
      })
        .then(msg => {
          this.$toast.success("User add to your responsability").goAway(1500)
          this.userEmail = ''
          addUser = false
        })
        .catch(error => {
          console.log(error.response.data)
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
  },
  created () {
    
} }
</script>
<style></style>