<template>
  <b-container>
    <p>PRC</p>
  </b-container>
</template>


<script>
export default{
  created(){
    if (this.$auth.user.groups.includes('Utente')) {
      this.$router.push('patient')
    } else if(this.$auth.user.groups.includes('ProfissionalSaude')){
        this.$router.push('healthcareProfessional')
    } else if(this.$auth.user.groups.includes('Admin')){
      this.$router.push('admin')
    }
  }
}
</script>
