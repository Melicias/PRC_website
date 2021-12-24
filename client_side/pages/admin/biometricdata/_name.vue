<template>
  <b-container>
    <b-card class="mt-3" header="Update Type of Biometric data Management">
      <b-form-group id="input-group-1" label="Name: " label-for="input-1">
        <b-form-input
          :disabled=true
          id="input-1"
          v-model="this.tipoDadoBiometrico.name"
          type="text"
          placeholder="Enter name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4" label="Method:" label-for="input-4">
        <b-form-select
          id="input-4"
          v-model="type"
          :disabled=true
          :options="types"
          required
        ></b-form-select>
      </b-form-group>
      <b-form @submit="onSubmit">
        <div v-if="type == 2">
          <b-form-group id="input-group-2" label="Min:" label-for="input-2">
            <b-form-input
              id="input-2"
              v-model="form.min"
              type="number"
              placeholder="Enter Minimun"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-3" label="Max:" label-for="input-3">
            <b-form-input
              id="input-3"
              v-model="form.max"
              type="number"
              placeholder="Enter Maximun"
              required
            ></b-form-input>
          </b-form-group>
        </div>
        <div v-if="type == 1">
          <b-form-group id="input-group-5" label="Quantitativo:" label-for="input-5">
            <b-form-input
              id="input-3"
              v-model="form.quantitativo"
              type="text"
              placeholder=""
              required
            ></b-form-input>
          </b-form-group>
        </div>
        <b-button type="submit" variant="primary">Update</b-button>
      </b-form>
      <p>{{ tipoDadoBiometrico.deleted_at == null ? "" : "Deleted!" }}</p>
    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/biometricdata">Back</b-button>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      tipoDadoBiometrico: {},
      type: null,
      form: {
        min: 0,
        max: 1,
        quantitativo: ''
      },
      types: [{ text: 'Select One', value: null }, { text: 'Quantitativa', value: 1 }, { text: 'Qualitativo', value: 2 }]
    }
  },
  computed: {
    name() {
      return this.$route.params.name
    },
  },
  created() {
    this.$axios.$get(`/api/tipoDadosBiometricos/${this.name}`)
      .then((tipoDadoBiometrico) => {
        this.tipoDadoBiometrico = tipoDadoBiometrico || {}
        console.log(this.tipoDadoBiometrico);
        this.type = this.tipoDadoBiometrico.type;
        if(this.tipoDadoBiometrico.type == 2){
          this.form.min = this.tipoDadoBiometrico.min;
          this.form.max = this.tipoDadoBiometrico.max;
        }else{
          this.form.quantitativo = this.tipoDadoBiometrico.quantitativo;
        }
      })
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      if(this.tipoDadoBiometrico.type == 2 && this.form.max <= this.form.min){
        this.$toast.error("the maximum value should be higher than minimum)").goAway(3000)
        return;
      }
      this.$axios.$put('/api/tipoDadosBiometricos', {
        id: this.tipoDadoBiometrico.id,
        name: this.tipoDadoBiometrico.name,
        min: this.form.min,
        max: this.form.max,
        type: this.tipoDadoBiometrico.type,
        quantitativo: this.form.quantitativo
      })
        .then(msg => {
          this.$toast.success("Biometric data updated with success").goAway(1500)
          console.log(msg)
          this.$router.push({path: "/admin/biometricdata"});
        })
        .catch(error => {
          this.$toast.error(error.response.data).goAway(3000)
        })
    }
  }
}
</script>
