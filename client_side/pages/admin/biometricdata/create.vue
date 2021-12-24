<template>
  <b-container>
    <b-card class="mt-3" header="Create new Type of Biometric data Management">
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

        <b-form-group id="input-group-4" label="Method:" label-for="input-4">
          <b-form-select
            id="input-4"
            v-model="form.type"
            :options="types"
            required
          ></b-form-select>
        </b-form-group>
        <div v-if="form.type == 2">
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
        <div v-if="form.type == 1">
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
        <b-button type="submit" variant="primary">Create</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </b-form>
    </b-card>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/admin/biometricdata">Back</b-button>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      form: {
        name: '',
        min: 0,
        max: 1,
        type: null,
        quantitativo: ''
      },
      types: [{ text: 'Select One', value: null }, { text: 'Quantitativa', value: 1 }, { text: 'Qualitativo', value: 2 }],
      show: true
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      if(this.form.type == 2 && this.form.max <= this.form.min){
        this.$toast.error("the maximum value should be higher than minimum)").goAway(3000)
        return;
      }
      this.$axios.$post('/api/tipoDadosBiometricos', {
        name: this.form.name,
        min: this.form.min,
        max: this.form.max,
        type: this.form.type,
        quantitativo: this.form.quantitativo
      })
        .then(msg => {
          this.$toast.success("Biometric data created with success").goAway(1500)
          this.form.name = ''
          this.form.min = 0
          this.form.max = 1
          this.form.type = null
          this.form.quantitativo = '';
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
      this.form.min = 0
      this.form.max = 1
      this.form.type = null
      this.form.quantitativo = '';
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>
<style></style>
