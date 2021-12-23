<template>
  <b-container>
    <h4>Student Details</h4>
    <p>Name: {{ tipoDadoBiometrico.name }}</p>
    <p>Min: {{ tipoDadoBiometrico.min }}</p>
    <p>Max: {{ tipoDadoBiometrico.max }}</p>
    <p>Quantitativo: {{ tipoDadoBiometrico.quantitativo }}</p>
    <p>Deleted_at: {{ tipoDadoBiometrico.deleted_at }}</p>
    <nuxt-link to="/admin/biometricdata">Back</nuxt-link>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      tipoDadoBiometrico: {}
    }
  },
  computed: {
    name() {
      return this.$route.params.name
    },
  },
  created() {
    this.$axios.$get(`/api/tipoDadosBiometricos/${this.name}`)
      .then((tipoDadoBiometrico) => {
        this.tipoDadoBiometrico = tipoDadoBiometrico || {} })
  }, }
</script>
