<template>
  <b-container>
    <h1>Welcome Back {{utente.name}}</h1>
    <br><br>
   <b-button v-b-toggle.collapse-1 variant="primary" href="/utente/profile">profile</b-button>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      email: null,
      utente:[]
    }
  },
  created() {
    this.email = this.$auth.user.sub
    this.$axios.$get('/api/utente/'+this.email)
      .then((utente) => {
        this.utente = utente
        console.log(utente)
      })
      
    }
}
</script>
