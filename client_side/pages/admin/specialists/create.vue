<template>
  <b-container>
    <b-card class="mt-3" header="Create Healthcare specialist">
      <b-form @submit="onSubmit">
        <b-form-group id="input-group-1" label="Email: " label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="form.email"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-2" label="Type:" label-for="input-2">
          <b-form-select
            id="input-2"
            v-model="form.tipoProfissional"
            :disabled=false
            :options="types"
            required
          ></b-form-select>
        </b-form-group>
        <b-form-group id="input-group-3" label="Name:" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="form.name"
            type="text"
            placeholder="Enter Name"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-4" label="Password:" label-for="input-4">
          <b-form-input
            id="input-4"
            v-model="form.password"
            type="password"
            placeholder="Enter password"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-5" label="Confirm Password:" label-for="input-5">
          <b-form-input
            id="input-5"
            v-model="form.confirmPassword"
            type="password"
            placeholder="Enter password confirmation"
            required
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">Create</b-button>
      </b-form>

    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/specialists">Back</b-button>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        email: '',
        password: '',
        confirmPassword: '',
        tipoProfissional: null
      },
      types: [{ text: 'Select One', value: null }],
      allTiposProfissionais: []
    }
  },
  created() {
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('not-found')
    }
    this.$axios.$get('/api/tipoprofissional')
      .then((tipoprofissional) => {
        for (let i = 0; i < tipoprofissional.length; i++) {
          if(tipoprofissional[i].deleted_at == null)
            this.types.push({text: tipoprofissional[i].name, value: tipoprofissional[i].id})
        }
        this.allTiposProfissionais = tipoprofissional;
      })
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      if(this.form.password != this.form.confirmPassword){
        this.$toast.error("The passwords must match").goAway(3000)
        return;
      }
      var tipo = null;
      for (let i = 0; i < this.allTiposProfissionais.length; i++) {
        if(this.allTiposProfissionais[i].id == this.form.tipoProfissional){
          tipo = this.allTiposProfissionais[i];
          break;
        }
      }
      this.$axios.post(`/api/profissionalsaude`, {
        email: this.form.email,
        tipoProfissional: tipo,
        name: this.form.name,
        password: this.form.password,
      })
        .then(msg => {
          this.$toast.success("Specialist created with success").goAway(1500)
          this.$router.push({path: "/admin/specialists"});
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    }
  }
}
</script>
