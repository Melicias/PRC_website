<template>
    <div>
        <b-container>
            <h1>Create Patient Account</h1>
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
                <b-button variant="primary" to="/healthcareProfessional/">Back</b-button>
                <b-button type="submit" variant="primary">Create</b-button>
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
            emailProfissionalSaude: this.$auth.user.sub
          })
            .then(msg => {
              this.$toast.success("User created with success").goAway(1500)
              this.sendEmail()
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
      },
      sendEmail(){
        this.$axios.$post(`/api/utente/${this.form.email}/send`, {
          subject: "Welcome to PRC! Your account was created by the Healthcare: " + this.$auth.user.sub,
          message: "Dear " + this.form.name + " keep active on our platform in order to achieve the proper follow-up by us and to achieve our goals!"
        })
          .then(msg => {
            this.$toast.success(msg).goAway(1500)
          })
          .catch(error => {
            this.$toast.error('error sending the e-mail').goAway(3000)
          })
      }
    },
    created(){
      if (!this.$auth.user.groups.includes('ProfissionalSaude')) {
        this.$router.push('not-found')
      }
    }
}
</script>
<style scoped>
.margin{
  margin-top: 30px;
}
</style>
