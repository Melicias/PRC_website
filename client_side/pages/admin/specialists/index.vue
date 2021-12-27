<template>
  <b-container>
    <h1>Healthcare Specialists Management</h1>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/specialists/create">Create New</b-button>
    <br><br>
    <b-table striped over :items="specialist" :fields="fields" ref="table">
      <template #cell(blocked)="data">
        <div v-if="data.item.blocked == 0">
          <b-button variant="danger" @click.prevent="blockSpecialist(`${data.item.email}`, `${data.index}`)">Block</b-button>
        </div>
        <div v-else>
          <b-button variant="success"  @click.prevent="blockSpecialist(`${data.item.email}`, `${data.index}`)">Unblock</b-button>
        </div>
      </template>
      <template #cell(deleted)="data">
        <div v-if="data.item.deleted_at == null">
          <b-button variant="danger" @click.prevent="deleteSpecialist(`${data.item.email}`, `${data.index}`)">delete</b-button>
        </div>
        <div v-else>
          <b-button variant="success"  @click.prevent="deleteSpecialist(`${data.item.email}`, `${data.index}`)">undo</b-button>
        </div>
      </template>
      <template #cell(type)="data">
        {{data.item.tipoProfissional == null ? "" : data.item.tipoProfissional.name}}
      </template>
      <template v-slot:cell(actions)="row">
        <nuxt-link
          tag="img" style="cursor: pointer"
          :src="require('~/img/view-details.png')"
          :to="`specialists/${row.item.email}`">
        </nuxt-link>
      </template>
    </b-table>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      fields: ['name', 'email', 'type', 'blocked', 'deleted', 'actions'],
      specialist: [],
    }
  },
  created () {
    this.$axios.$get('/api/profissionalsaude')
      .then((specialist) => {
        this.specialist = specialist
      })
  },
  methods: {
    deleteSpecialist(email, index) {
      this.$axios.$delete(`/api/profissionalsaude/${email}`)
        .then(msg => {
          this.$toast.success("Specialist deleted with success").goAway(1500)
          this.specialist[index].deleted_at = msg.deleted_at;
          this.$refs.table.refresh();
        })
        .catch(error => {
          this.$toast.error('error while deleting').goAway(3000)
        })
    },
    blockSpecialist(email, index) {
      this.$axios.put(`/api/profissionalsaude/block/${email}`)
        .then(msg => {
          this.$toast.success("Specialist deleted with success").goAway(1500)
          this.specialist[index].blocked = msg.data.blocked
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
