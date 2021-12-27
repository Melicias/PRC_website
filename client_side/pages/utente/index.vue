<template>
  <b-container>
    <h1 style="margin-top:5%">Welcome Back, {{utente.name}}</h1>
    <br><br>
    <nuxt-link :to="'/utente/profile'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/user.png" alt="Image not loaded" />
        <p>Profile</p>
      </div>
    </nuxt-link>
    <nuxt-link :to="'/utente/prescritions'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/doctor.png" alt="Image not loaded" />
        <p>My Prescriptions</p>
      </div>
    </nuxt-link>
    <nuxt-link :to="'/utente/biometricData'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/blood-test.png" alt="Image not loaded" />
        <p>Biometric data</p>
      </div>
    </nuxt-link>

  </b-container>
</template>
<style scoped>
.img-with-text {
  width:200px;
  text-align:center;
  display:block;
  background-color: transparent;
  border: 1px solid transparent;
  margin-top: 5%;
  margin-right: 15%;
  margin-bottom: 1px;
  float:left;
}
</style>
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
