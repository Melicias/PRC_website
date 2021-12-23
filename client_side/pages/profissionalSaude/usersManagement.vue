<template>
    <div>
        <b-container>
            <h2>Users Management</h2>
            <b-table striped over :items="tiposProfissionais" :fields="fields">
                <template v-slot:cell(actions)="row">
                    <nuxt-link
                        class="btn btn-link"
                        :to="`/tipoprofissional/${row.item.name}`">Details</nuxt-link>
                </template>
            </b-table>
            <b-button type="reset" class="btn-default" to="/profissionalSaude/main">Back</b-button>
        </b-container>
        
    </div>
</template>
<script>
export default {
  data () {
    return {
      fields: ['id', 'name'],
      tiposProfissionais: []
    }
  },
  created () {
    this.$axios.$get('/api/tipoprofissional')
      .then((tiposProfissionais) => {
        this.tiposProfissionais = tiposProfissionais
      })
} }
</script>
<style></style>