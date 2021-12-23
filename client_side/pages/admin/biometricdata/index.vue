<template>
  <b-container>
    <h1>Type of Biometric data Management</h1>
    <br><br>
    <b-table striped over :items="tipoDadosBiometricos" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`biometricdata/${row.item.name}`">Details</nuxt-link>
        <button @click.prevent="deleteTipo(`${row.item.id}`)">
          del
        </button>
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
  },
  methods: {
    deleteTipo(id) {
      console.log(id);
      this.$axios.$delete(`/api/tipoDadosBiometricos/${id}`)
        .then(msg => {
          this.$toast.success(msg).goAway(1500)
        })
        .catch(error => {
          this.$toast.error('error sending the e-mail').goAway(3000)
        })
    }
  }
}
</script>
<style></style>
