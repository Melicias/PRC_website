<template>
  <b-container>
    <h2>Admins Management</h2>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/admins/create">Create New</b-button>
    <br><br>
    <b-table striped over :items="admins" :fields="fields" ref="table">
      <template #cell(deleted)="data">
        <div v-if="data.item.deleted_at != null">
          <p>Deleted</p>
        </div>
      </template>
      <template #cell(blocked)="data">
        <div v-if="data.item.blocked == 0">
          <b-button variant="danger" @click.prevent="blockAdmin(`${data.item.email}`, `${data.index}`)">Block</b-button>
        </div>
        <div v-else>
          <b-button variant="success"  @click.prevent="blockAdmin(`${data.item.email}`, `${data.index}`)">Unblock</b-button>
        </div>
      </template>
    </b-table>
    <br><br>
  </b-container>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'email','deleted','blocked'],
      admins: []
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('not-found')
    }
    this.$axios.$get('/api/admin')
      .then((admins) => {
        this.admins = admins
      })
  },
  methods: {
    blockAdmin(email, index) {
      this.$axios.put(`/api/admin/block/${email}`)
        .then(msg => {
          this.$toast.success("Admin blocked/unblicked with success").goAway(1500)
          this.admins[index].blocked = msg.data.blocked
          this.$refs.table.refresh();
        })
        .catch(error => {
          this.$toast.error('error while deleting').goAway(3000)
        })
    }
  }}
</script>
<style></style>
