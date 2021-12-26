<template>
    <div>
        <b-container>
            <h2>Users Management</h2>
            <b-table striped over :items="utentes" :fields="fields">
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
      fields: ['name', 'email', 'dataNasc', 'dadosBiometricos', 'prescricoes', 'profissionalSaude', 'blocked'],
      utentes: []
    }
  },
  created () {
    this.$axios.$get('/api/utente')
      .then((utentes) => {
        console.log(utentes)
        this.utentes = utentes
      })
} }
</script>
<style></style>