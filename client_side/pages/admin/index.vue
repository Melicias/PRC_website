<template>
  <b-container>
    <h1>Welcome Back {{email}}</h1>
    <br><br>
    <nuxt-link :to="'/admin/patients'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/user.png" alt="Image not loaded" />
        <p>Patients management</p>
      </div>
    </nuxt-link>
    <nuxt-link :to="'/admin/medicosAKAFAZER'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/doctor.png" alt="Image not loaded" />
        <p>Healthcare specialists management</p>
      </div>
    </nuxt-link>
    <nuxt-link :to="'/admin/Biometricdata'">
      <div class="img-with-text">
        <img width="100" height="100" src="../../img/blood-test.png" alt="Image not loaded" />
        <p>Biometric data management</p>
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
  margin-right: 10px;
  margin-bottom: 1px;
  float:left;
}
</style>

<script>
export default {
  data() {
    return {
      email: null
    }
  },
  created() {
    this.email = this.$auth.user.sub
  },
}
</script>
