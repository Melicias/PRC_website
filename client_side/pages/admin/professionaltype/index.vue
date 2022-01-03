<template>
  <b-container>
    <h2>Healthcare Specialists type data Management</h2>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/professionaltype/create">Create New</b-button>
    <br><br>
    <b-table striped over :items="tipoprofissional" :fields="fields" ref="table">

      <template v-slot:cell(actions)="row">
          <div v-if="row.item.deleted_at == null">
            <b-button variant="danger" @click.prevent="deleteTipo(`${row.item.id}`, `${row.index}`)">delete</b-button>
          </div>
          <div v-else>
            <b-button variant="success"  @click.prevent="deleteTipo(`${row.item.id}`, `${row.index}`)">undo</b-button>
          </div>
      </template>
    </b-table>
  </b-container>


</template>
<script>
export default {
  data () {
    return {
      fields: [{key:'id'}, {key: 'name'},{key:'actions'}],
      tipoprofissional: [],
      form: {
        name: ''
      },
      show: true
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('nuxt-error')
    }
    this.$axios.$get('/api/tipoprofissional')
      .then((tipoprofissional) => {
        this.tipoprofissional = tipoprofissional
      })
  },
  methods: {
    deleteTipo(id, index) {
      this.$axios.$delete(`/api/tipoprofissional/${id}`)
        .then(msg => {
          this.$toast.success("Professional type deleted with success").goAway(1500)
          if(msg === ''){
            location.reload();
          }
          this.tipoprofissional[index].deleted_at = msg.deleted_at;
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
