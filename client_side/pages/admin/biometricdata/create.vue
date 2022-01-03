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
            <p>How to fill this fields?</p>
            <p>The first row only needs the max number, so it goes from n until Max and the last one only needs to have the Min so it goes from Min until N</p>
            <p>Here you have an example: N - 10 = Lower than 10; 10 - 20 = between 10 and 20; 20 - N = Bigger than 20</p>
            <b-form-row>
              1 -
              <b-col><b-form-input min=0 required v-model="quantitativo.max1" id="input-1-max" type="number" placeholder="Max"></b-form-input></b-col>
              <b-col><b-form-input required v-model="quantitativo.name1" id="input-1-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
            <b-form-row>
              2 -
              <b-col><b-form-input min=0 required v-model="quantitativo.min2" id="input-2-min" type="number" placeholder="Min"></b-form-input></b-col>
              <b-col><b-form-input min=0 required v-model="quantitativo.max2" id="input-2-max" type="number" placeholder="Max"></b-form-input></b-col>
              <b-col><b-form-input required v-model="quantitativo.name2" id="input-2-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
            <b-form-row>
              3 -
              <b-col><b-form-input min=0 v-model="quantitativo.min3" id="input-3-min" type="number" placeholder="Min"></b-form-input></b-col>
              <b-col><b-form-input min=0 v-model="quantitativo.max3" id="input-3-max" type="number" placeholder="Max"></b-form-input></b-col>
              <b-col><b-form-input v-model="quantitativo.name3" id="input-3-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
            <b-form-row>
              4 -
              <b-col><b-form-input min=0 v-model="quantitativo.min4" id="input-4-min" type="number" placeholder="Min"></b-form-input></b-col>
              <b-col><b-form-input min=0 v-model="quantitativo.max4" id="input-4-max" type="number" placeholder="Max"></b-form-input></b-col>
              <b-col><b-form-input min=0 v-model="quantitativo.name4" id="input-4-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
            <b-form-row>
              5 -
              <b-col><b-form-input min=0 v-model="quantitativo.min5" id="input-5-min" type="number" placeholder="Min"></b-form-input></b-col>
              <b-col><b-form-input min=0 v-model="quantitativo.max5" id="input-5-max" type="number" placeholder="Max"></b-form-input></b-col>
              <b-col><b-form-input v-model="quantitativo.name5" id="input-5-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
            <b-form-row>
              6 -
              <b-col><b-form-input min=0 required v-model="quantitativo.min6" id="input-6-min" type="number" placeholder="Min"></b-form-input></b-col>
              <b-col><b-form-input min=0 required v-model="quantitativo.name6" id="input-6-name" type="text" placeholder="Name"></b-form-input></b-col>
            </b-form-row>
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
        type: null
      },
      quantitativo: {
        max1: null,
        name1: '',
        max2: null,
        min2: null,
        name2: '',
        max3: null,
        min3: null,
        name3: '',
        max4: null,
        min4: null,
        name4: '',
        max5: null,
        min5: null,
        name5: '',
        min6: null,
        name6: '',
      },
      sendQuant: [],
      types: [{ text: 'Select One', value: null }, { text: 'Quantitativa', value: 1 }, { text: 'Qualitativo', value: 2 }],
      show: true
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      if(this.form.type == 2 && this.form.max <= this.form.min){
        this.$toast.error("the maximum value should be higher than minimum)").goAway(3000)
        return
      }
      if(this.form.type == 1){
        if(!this.isValidQuantitativo()){
          this.$toast.error("the maximum value should be higher than minimum)").goAway(3000)
          return
        }
        this.sendQuant =
          [{min: -1, max: this.quantitativo.max1, name: this.quantitativo.name1},
          {min: this.quantitativo.min2, max: this.quantitativo.max2, name: this.quantitativo.name2},
          {min: this.quantitativo.min3 == null ? 0 : this.quantitativo.min3, max: this.quantitativo.max3 == null ? 0 : this.quantitativo.max3, name: this.quantitativo.name3},
          {min: this.quantitativo.min4 == null ? 0 : this.quantitativo.min4, max: this.quantitativo.max4 == null ? 0 : this.quantitativo.max4, name: this.quantitativo.name4},
          {min: this.quantitativo.min5 == null ? 0 : this.quantitativo.min5, max: this.quantitativo.max5 == null ? 0 : this.quantitativo.max5, name: this.quantitativo.name5},
          {min: this.quantitativo.min6 , max: -1, name: this.quantitativo.name6}]
        console.log(this.sendQuant);
      }
      this.$axios.$post('/api/tipoDadosBiometricos', {
        name: this.form.name,
        min: this.form.min,
        max: this.form.max,
        type: this.form.type,
        tipoDadosBiometricosQuantitativo: this.sendQuant
      })
        .then(msg => {
          this.$toast.success("Biometric data created with success").goAway(1500)
          this.form.name = ''
          this.form.min = 0
          this.form.max = 1
          this.form.type = null
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
    },
    isValidQuantitativo(){
      if(this.quantitativo.max1 > this.quantitativo.max2){
        this.$toast.error("The first value should be less than the second one").goAway(3000)
        return false
      }
      if(this.quantitativo.min2 > this.quantitativo.max2){
        this.$toast.error("Int the 2, the max should be higher than the min").goAway(3000)
        return false
      }
      return true
    }
  },
  created(){
    if (!this.$auth.user.groups.includes('Admin')) {
      this.$router.push('nuxt-error')
    }
  }
}
</script>
<style></style>
