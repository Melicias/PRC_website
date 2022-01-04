<template>
  <b-container>
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
    <b-button v-b-toggle.collapse-1 variant="primary" href="/utente/prescritions ">Back</b-button>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      prescritions:[],
      fields: ['Description', 'TypeOfPrescritions'],
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
    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        this.utente = utente || {}
        this.prescritions = this.utente.prcs[this.id-1].prescricoes
        console.log(this.prescritions)
      })
  },
}
</script>