<template>
  <div>
    <b-container>
      <h3>Change password for: {{ this.$auth.user.sub }}</h3>
      <b-button v-b-toggle.collapse-1 variant="primary" href="/admin">Back</b-button>
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
      this.$axios.$put('/api/admin/' + this.$auth.user.sub, {
        password: this.oldPassword,
        newPassword: this.newPassword,
        confirmNewPassword: this.confirmPassword,
      })
        .then(msg => {
          this.$toast.success("Password updated with success").goAway(1500)
          this.changePassword = false
          this.oldPassword = ''
          this.newPassword = ''
          this.confirmPassword = ''
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    }
  },
  created(){
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('not-found')
    }
  }
}
</script>
<style scoped>
.width{
  width: 150px;
}
</style>
