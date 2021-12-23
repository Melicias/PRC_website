<template>
  <b-container>
    <h1>Type of Biometric data Management</h1>
    <br><br>
    <b-table striped over :items="tipoDadosBiometricos" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`biometricdata/${row.item.name}`">Details</nuxt-link>
      </template>
    </b-table>
    <nuxt-link to="/admin">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'min', 'max', 'quantitativo', 'deleted_at', 'actions'],
      tipoDadosBiometricos: []
    }
  },
  created () {
    this.$axios.$get('/api/tipoDadosBiometricos')
      .then((tipoDadosBiometricos) => {
        this.tipoDadosBiometricos = tipoDadosBiometricos
        console.log(this.tipoDadosBiometricos);
      })
  } }
</script>
<style></style>
