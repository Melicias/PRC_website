<template>
    <div>
        <b-container>
            <h2>Healthcare Professional: {{ this.$auth.user.sub }}</h2>
            <b-button variant="light" to="/profissionalSaude/createPacient">
              <div class="img-with-text width">
                <img width="100" height="100" src="../../img/patient.png" alt="Image not loaded" />
                <p>Create Patient</p>
              </div>
            </b-button>
            <b-button variant="light" to="/profissionalSaude/pacientsManagement">
              <div class="img-with-text">
                <img width="100" height="100" src="../../img/user.png" alt="Image not loaded" />
                <p>Patients management</p>
              </div>
            </b-button>
            <b-button variant="light" to="/profissionalSaude/managePrescriptions">
              <div class="img-with-text">
                <img width="100" height="100" src="../../img/prescription.png" alt="Image not loaded" />
                <p>Manage Prescriptions</p>
              </div>
            </b-button>
            <b-button variant="light" to="/profissionalSaude/addPacient">
              <div class="img-with-text">
                <img width="100" height="100" src="../../img/addPatient.png" alt="Image not loaded" />
                <p>Add/Remove Patient</p>
              </div>
            </b-button>
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
<style scoped>
.width{
  width: 150px;
}
</style>