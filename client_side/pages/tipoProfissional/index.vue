<template>
 <div>
   <b-container>
     <b-table striped over :items="tiposProfissionais" :fields="fields">
       <template v-slot:cell(actions)="row">
         <nuxt-link
           class="btn btn-link"
           :to="`/tipoprofissional/${row.item.name}`">Details</nuxt-link>
       </template>
     </b-table>
     <nuxt-link to="/">Back</nuxt-link>
   </b-container>
 <nuxt-link to="/create">Create a New Tipo profissional</nuxt-link>
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
