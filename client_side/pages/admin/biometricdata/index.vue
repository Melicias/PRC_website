<template>
  <b-container>
    <h1>Type of Biometric data Management</h1>
    <br><br>
    <b-table striped over :items="tipoDadosBiometricos" :fields="fields" ref="table">
      <template #cell(deleted_at)="data">
        <b class="text-info">{{data.item.deleted_at == null ? "" : "deleted"}}</b>
      </template>
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`biometricdata/${row.item.name}`">Details</nuxt-link>
        <button @click.prevent="deleteTipo(`${row.item.id}`, `${row.index}`)">
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
      })
  },
  methods: {
    deleteTipo(id, index) {
      console.log(id);
      this.$axios.$delete(`/api/tipoDadosBiometricos/${id}`)
        .then(msg => {
          this.$toast.success("Biometric data deleted with success").goAway(1500)
          this.tipoDadosBiometricos[index].deleted_at = msg.deleted_at;
          this.$refs.table.refresh();
        })
        .catch(error => {
          this.$toast.error('error while deleting').goAway(3000)
        })
    }
  }
}
</script>
<style></style>
