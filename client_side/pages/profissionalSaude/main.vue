<template>
    <div>
        <b-container>
            <nuxt-link to="/profissionalSaude/createUser">Create new user</nuxt-link>
            <nuxt-link to="/profissionalSaude/usersManagement">Manage users</nuxt-link>
            <nuxt-link to="/">Logout</nuxt-link>
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