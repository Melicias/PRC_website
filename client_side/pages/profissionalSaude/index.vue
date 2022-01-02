<template>
    <div>
        <b-container>
            <h2>Healthcare Professional: {{ this.$auth.user.sub }}</h2>
            <b-button to="/profissionalSaude/createPacient">Create Pacient</b-button>
            <b-button to="/profissionalSaude/pacientsManagement">Manage Pacients</b-button>
            <b-button to="/profissionalSaude/managePrescriptions">Manage Prescriptions</b-button>
            <b-button to="/profissionalSaude/addPacient">Add Pacient To My Responsability</b-button>
            <b-button to="/">Logout</b-button>
        </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {

    }
  },
  methods: {
    
  },
  created () {
    
} }
</script>
<style></style>