<template>
  <b-container>
    <h1>Patients Management</h1>
    <br><br>
    <b-table striped over :items="tipoDadosBiometricos" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/students/${row.item.name}`">Details</nuxt-link>
        <nuxt-link :to="`/students/${row.item.name}/send-email`">Send e- mail</nuxt-link>
      </template>
    </b-table>
    <nuxt-link to="/admin">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'min', 'max', 'Other way'],
      tipoDadosBiometricos: []
    }
  },
  created () {
    this.$axios.$get('/api/tipoDadosBiometricos')
      .then((tipoDadosBiometricos) => {
        this.tipoDadosBiometricos = tipoDadosBiometricos
      })
  } }
</script>
<style></style>
