<template>
    <div>
        <b-container>
            <h2>Profissional de Saúde</h2>
            <b-button to="/profissionalSaude/createUser">Create new user</b-button>
            <b-button to="/profissionalSaude/usersManagement">Manage users</b-button>
            <b-button to="/">Logout</b-button>
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