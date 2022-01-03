<template>
  <b-container>
    <h2>Type of Biometric data Management</h2>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/biometricdata/create">Create New</b-button>
    <br><br>
    <b-table striped over :items="tipoDadosBiometricos" :fields="fields" ref="table">
      <template #cell(min)="data">
        {{data.item.type == 2 ? data.item.min : ""}}
      </template>
      <template #cell(max)="data">
        {{data.item.type == 2 ? data.item.max : ""}}
      </template>
      <template #cell(deleted)="data">
        <div v-if="data.item.deleted_at == null">
          <b-button variant="danger" @click.prevent="deleteTipo(`${data.item.id}`, `${data.index}`)">delete</b-button>
        </div>
        <div v-else>
          <b-button variant="success"  @click.prevent="deleteTipo(`${data.item.id}`, `${data.index}`)">undo</b-button>
        </div>
      </template>
      <template #cell(quantitativo)="data">
        <p v-for="tipoDado in data.item.tipoDadosBiometricosQuantitativo">
          {{tipoDado.name + " - min: " + tipoDado.min + " max: " + tipoDado.max }}
        </p>
      </template>
      <template v-slot:cell(actions)="row">
        <nuxt-link
          tag="img" style="cursor: pointer"
          :src="require('~/img/view-details.png')"
          :to="`biometricdata/${row.item.name}`">
        </nuxt-link>
      </template>
    </b-table>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'min', 'max', 'quantitativo', 'deleted', 'actions'],
      tipoDadosBiometricos: [],
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('nuxt-error')
    }
    this.$axios.$get('/api/tipoDadosBiometricos')
      .then((tipoDadosBiometricos) => {
        this.tipoDadosBiometricos = tipoDadosBiometricos
        console.log(this.tipoDadosBiometricos)
      })
  },
  methods: {
    deleteTipo(id, index) {
      console.log(id);
      this.$axios.$delete(`/api/tipoDadosBiometricos/${id}`)
        .then(msg => {
          this.$toast.success("Biometric data deleted with success").goAway(1500)
          if(msg === ''){
            location.reload();
          }
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
