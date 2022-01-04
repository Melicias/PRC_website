<template>
  <b-container>
    <b-card class="mt-3 margin" header="Prc detail">
      <b-form>
        <b-form-group
          id="input-group-1"
          label="Decease:"
          label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="prc.doenca"
            type="text"
            placeholder="Enter decease"
            required
            :disabled=true
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="input-group-1"
          label="Healthcare Specialists:"
          label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="prc.profissionalSaude.name"
            type="text"
            placeholder="Enter decease"
            required
            :disabled=true
          ></b-form-input>
        </b-form-group>
        <b-form-group
          id="input-group-1"
          label="Type of Healthcare Specialists:"
          label-for="input-1">
          <b-form-input
            id="input-1"
            v-model="prc.profissionalSaude.tipoProfissional.name"
            type="text"
            placeholder="Enter decease"
            required
            :disabled=true
          ></b-form-input>
        </b-form-group>
      </b-form>
    </b-card>
    <br><br>
    <b-card header="Precriptions Details">
      <b-table striped over sticky-header :items="prescritions" :fields="fields" ref="tableCom">
           <template v-slot:cell(Description)="row">
          {{row.item.descricao}}
        </template>
         <template v-slot:cell(TypeOfPrescritions)="row">
          {{row.item.tipoPrescricao.name}}
        </template>
      </b-table>
    </b-card>
    <br>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/patient/prescritions ">Back</b-button>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      prescritions:[],
      fields: ['Description', 'TypeOfPrescritions'],
      prc: {doenca: "", profissionalSaude: {name: "", tipoProfissional: {name:""}}},
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    email(){
       return this.$auth.$state.user.sub
    },
  },
  created() {
    if (!this.$auth.user.groups.includes('Utente')) {
      this.$router.push('not-found')
    }
    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        this.utente = utente || {}
        this.prescritions = this.utente.prcs[this.id-1].prescricoes
        this.prc = this.utente.prcs[this.id-1]
      })
  },
}
</script>
