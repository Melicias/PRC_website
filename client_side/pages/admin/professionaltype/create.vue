<template>
  <b-container>
    <h1>Professional type data Management</h1>
    <br><br>
      <b-card class="mt-3" header="Create new Professional type data Management">
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
          <b-form-group id="input-group-1" label="Name: " label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.name"
              type="text"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Create</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/professionaltype">Back</b-button>
  </b-container>


</template>
<script>
export default {
  data () {
    return {
      form: {
        name: ''
      },
      show: true
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.$axios.$post('/api/tipoprofissional', {
        name: this.form.name
      })
        .then(msg => {
          this.$toast.success("Professional type created with success").goAway(1500)
          this.form.name = ''
          // Trick to reset/clear native browser form validation state
          this.show = false
          this.$nextTick(() => {
            this.show = true
          })
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.name = ''
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
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
<style></style>
