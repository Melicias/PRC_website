<template>
  <b-container>
    <h1>Patients Management</h1>
    <br><br>
    <b-table striped over :items="students" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/students/${row.item.username}`">Details</nuxt-link>
        <nuxt-link :to="`/students/${row.item.username}/send-email`">Send e- mail</nuxt-link>

      </template>
    </b-table>
    <nuxt-link to="/admin">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['email', 'name', 'deleted', 'blocked'],
      students: []
    }
  },
  created () {
    this.$axios.$get('/api/students')
      .then((students) => {
        this.students = students
      })
  } }
</script>
<style></style>
