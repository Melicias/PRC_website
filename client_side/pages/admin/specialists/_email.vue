<template>
  <b-container>
    <b-card class="mt-3" header="Update Healthcare specialist">
      <b-form-group id="input-group-1" label="Name: " label-for="input-1">
        <b-form-input
          :disabled=true
          id="input-1"
          v-model="this.profissionalsaude.email"
          type="text"
          placeholder="Enter email"
          required
        ></b-form-input>
      </b-form-group>
      <p>{{ profissionalsaude.deleted_at == null ? "" : "Deleted!" }}</p>
      <p>{{ profissionalsaude.blocked == 0 ? "" : "Deleted!" }}</p>
      <b-form-group id="input-group-2" label="Type:" label-for="input-2">
        <b-form-select
          id="input-4"
          v-model="form.tipoProfissional"
          :disabled=false
          :options="types"
          required
        ></b-form-select>
      </b-form-group>
      <b-form @submit="onSubmit">
        <b-form-group id="input-group-3" label="Name:" label-for="input-3">
          <b-form-input
            id="input-2"
            v-model="form.name"
            type="text"
            placeholder="Enter Name"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-4" label="Password:" label-for="input-4">
          <b-form-input
            id="input-3"
            v-model="form.password"
            type="text"
            placeholder="Enter new password"
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">Update</b-button>
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
      profissionalsaude: {},
      form: {
        name: '',
        password: '',
        tipoProfissional: null
      },
      types: [],
      allTiposProfissionais: []
    }
  },
  computed: {
    email() {
      return this.$route.params.email
    },
  },
  created() {
    console.log(this.email);
    this.$axios.$get('/api/tipoprofissional')
      .then((tipoprofissional) => {
        for (let i = 0; i < tipoprofissional.length; i++) {
          this.types.push({text: tipoprofissional[i].name, value: tipoprofissional[i].id})
        }
        this.allTiposProfissionais = tipoprofissional;
        if(!(Object.keys(this.profissionalsaude).length === 0)){
          this.form.tipoProfissional = this.profissionalsaude.tipoProfissional.id
        }
      })
    this.$axios.$get(`/api/profissionalsaude/${this.email}`)
      .then((profissionalsaude) => {
        this.profissionalsaude = profissionalsaude || {}
        console.log(this.profissionalsaude)
        this.form.name = this.profissionalsaude.name
        this.form.tipoProfissional = this.profissionalsaude.tipoProfissional.id
      })
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      var tipo = null;
      for (let i = 0; i < this.allTiposProfissionais.length; i++) {
        if(this.allTiposProfissionais[i].id == this.form.tipoProfissional){
          tipo = this.allTiposProfissionais[i];
          break;
        }
      }
      this.$axios.$put(`/api/profissionalsaude`, {
        email: this.profissionalsaude.email,
        tipoProfissional: tipo,
        name: this.form.name,
        password: this.form.password.length == 0 ? null : this.form.password,
      })
        .then(msg => {
          this.$toast.success("Specialist updated with success").goAway(1500)
          console.log(msg)
          this.$router.push({path: "/admin/specialists"});
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    }
  }
}
</script>
