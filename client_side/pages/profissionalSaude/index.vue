<template>
    <div>
        <b-container>
            <h2>Healthcare Professional: {{ this.$auth.user.sub }}</h2>
            <div v-show="!changePassword">
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
              <b-button variant="light" to="/profissionalSaude/addPacient">
                <div class="img-with-text">
                  <img width="100" height="100" src="../../img/addPatient.png" alt="Image not loaded" />
                  <p>Add/Remove Patient</p>
                </div>
              </b-button>
              <b-button variant="light" @click="changePassword = true">Change My Password</b-button>
            </div>
            <div v-show="changePassword">
              <b-button variant="primary" @click="changePassword = false">Back</b-button>
              <b-card class="mt-3 margin" header="Change My Password">
                <b-form-group
                    id="input-group-1"
                    label="Old Password:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="oldPassword"
                    type="password"
                    placeholder="Enter old password"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-form-group
                    id="input-group-1"
                    label="New Password:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="newPassword"
                    type="password"
                    placeholder="Enter new password"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-form-group
                    id="input-group-1"
                    label="Confirm The New Password:"
                    label-for="input-1">
                    <b-form-input
                    id="input-1"
                    v-model="confirmPassword"
                    type="password"
                    placeholder="Enter confirm password"
                    required
                    ></b-form-input>
                </b-form-group>
                <b-button @click="changeMyPassword" variant="primary">Submit</b-button>
            </b-card>
            </div>
        </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {
      changePassword: false,
      oldPassword: '',
      newPassword: '',
      confirmPassword: ''
    }
  },
  methods: {
    changeMyPassword(){
      this.$axios.$put('/api/profissionalsaude/' + this.$auth.user.sub, {
        password: this.oldPassword,
        newPassword: this.newPassword,
        confirmPassword: this.confirmPassword,
      })
        .then(msg => {
          this.$toast.success("Password updated with success").goAway(1500)
          this.changePassword = false
          this.oldPassword = ''
          this.newPassword = ''
          this.confirmPassword = ''
        })
        .catch(error => {
          console.log(error.response.data)
          this.$toast.error(error.response.data).goAway(3000)
        })
    }
  },
  created () {
    
} }
</script>
<style scoped>
.width{
  width: 150px;
}
</style>