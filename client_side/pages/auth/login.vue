<template>
  <b-container>
    <h3>Login into PRC</h3>
    <b-form @submit.prevent="onSubmit" @reset="onReset">
      <b-form-group label="Email" description="Enter your Email">
        <b-input
          name="username"
          placeholder="Your email"
          v-model.trim="username"
          required />
      </b-form-group>
      <b-form-group label="Password" description="Enter your password">
        <b-input
          name="password"
          type="password"
          placeholder="Your password"
          v-model="password"
          required />
      </b-form-group>
      <b-button type="reset" class="btn-warning">Reset</b-button>
      <b-button type="submit" class="btn-success">Submit</b-button>
    </b-form>
  </b-container>
</template>

<script>
export default {
  auth: false,
  data() {
    return {
      username: null,
      password: null
    }
  },
  methods: {
    onSubmit() {
      let promise = this.$auth.loginWith('local', {
        data: {
          username: this.username,
          password: this.password
        }
      })
      promise.then(() => {
        this.$toast.success('You are logged in!').goAway(3000)
        // check if the user $auth.user object is set
        // TODO redirect based on the user role
        // eg:
        if (this.$auth.user.groups.includes('Utente')) {
          this.$router.push('/patient')
        } else if (this.$auth.user.groups.includes('Admin')) {
          this.$router.push('/admin')
        } if (this.$auth.user.groups.includes('ProfissionalSaude')) {
          this.$router.push('/healthcareProfessional/')
        }
      })
      promise.catch((error) => {
        this.$toast.error(error.response.data).goAway(3000)
      })
    },
    onReset() {
      this.username = null
      this.password = null
    }
  }
}
</script>
