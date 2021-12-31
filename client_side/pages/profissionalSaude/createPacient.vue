<template>
    <div> 
        <b-container>
            <h1>Create a User</h1>
            <b-form class="margin" @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group
                    id="input-group-1"
                    label="Email address:"
                    label-for="input-1"
                >
                    <b-form-input
                    id="input-1"
                    v-model="form.email"
                    type="email"
                    placeholder="Enter email"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="Name:" label-for="input-2">
                    <b-form-input
                    id="input-2"
                    v-model="form.name"
                    placeholder="Enter name"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-3" label="Birth date:" label-for="input-3">
                    <b-form-input
                    id="input-2"
                    type="date"
                    v-model="form.date"
                    required
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-3" label="Password:" label-for="input-3">
                    <b-form-input
                    id="input-2"
                    type="password"
                    v-model="form.password"
                    required
                    placeholder="Enter password"
                    ></b-form-input>
                </b-form-group>       
                <b-button variant="primary" to="/profissionalSaude/">Back</b-button> 
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
            
        </b-container>
        
    </div>
</template>
<script>
import moment from 'moment';
export default {
  data() {
      return {
        form: {
          email: '',
          name: '',
          password: '',
          date: '',
        },
        show: true
      }
    },
    methods: {
      /* format (value) {
        return moment(value).format('YYYY-MM-DDThh:mm:ss')
      }, */
        onSubmit(event) {
          event.preventDefault()
          this.$axios.$post('/api/utente', {
            email: this.form.email,
            name: this.form.name,
            password: this.form.password,
            dataNasc: new Date(this.form.date).toISOString(),
            emailProfissionalSaude: "profissional@profissional.com"
          })
            .then(msg => {
              this.$toast.success("User created with success").goAway(1500)
              this.form.name = ''
              this.form.email = ''
              this.form.password = ''
              this.form.date = ''
              // Trick to reset/clear native browser form validation state
              this.show = false
              this.$nextTick(() => {
                this.show = true
              })
            })
            .catch(error => {
              console.log(error.response.data)
              this.$toast.error(error.response.data).goAway(3000)
            })
        },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.password = ''
        this.form.date = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    } 
}
</script>
<style scoped>
.margin{
  margin-top: 30px;
}
</style>