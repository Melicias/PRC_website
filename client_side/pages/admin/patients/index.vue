<template>
  <b-container>
    <h2>Patients Management</h2>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
    <br><br>
    <b-table striped over :items="utentes" :fields="fields">
      <template v-slot:cell(specialists)="row">
        <p v-for="specialists in row.item.profissionalSaude">
          {{specialists.name + "" + (specialists.tipoProfissional != null ? " (" + specialists.tipoProfissional.name + ")" : "") }}
        </p>
      </template>
      <template #cell(deleted)="data">
        <div v-if="data.item.deleted_at != null">
          <p>Deleted</p>
        </div>
      </template>
      <template #cell(blocked)="data">
        <div v-if="data.item.blocked == 0">
          <p>No</p>
        </div>
        <div v-else>
          <p>Yes</p>
        </div>
      </template>
      <template v-slot:cell(actions)="row">
        <nuxt-link
          tag="img" style="cursor: pointer"
          :src="require('~/img/view-details.png')"
          :to="`patients/${row.item.email}`">
        </nuxt-link>
      </template>
    </b-table>
    <br><br>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'email','specialists','deleted','blocked','actions'],
      utentes: []
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('nuxt-error')
    }
    this.$axios.$get('/api/utente')
      .then((utentes) => {
        this.utentes = utentes
      })
  } 
}
</script>
<style></style>
