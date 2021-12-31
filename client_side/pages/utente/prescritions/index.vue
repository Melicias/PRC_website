<template>
  <b-container>
      <h1>My Prescriptions</h1>
    <br>
    <br>
    <b-card header="My atual Prescriptions">
      <b-table striped over sticky-header :items="prescritionsValidat" :fields="fields" ref="tableCom">
        <template v-slot:cell(disease)="row">
          {{row.item.doenca}}
        </template>
         <template v-slot:cell(Doctor)="row">
          {{row.item.profissionalSaude.name}}
        </template>
         <template v-slot:cell(validity)="row">
          {{row.item.validade.split('T')[0]}}
        </template>
          <template v-slot:cell(actions)="row">
        <nuxt-link
          tag="img" style="cursor: pointer"
          :src="require('~/img/view-details.png')"
          :to="`prescritions/${row.item.id}`">
        </nuxt-link>
      </template>
      </b-table>
    </b-card>
    <br>
    <br>
    <b-button v-b-toggle.collapse-1 variant="primary" href="/utente">Back</b-button>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
       utente: {},
      prescritions: [],
      prescritionsValidat:[],
      fields: ['disease', 'Doctor', 'validity','actions'],
    }
  },
 computed: {
    email() {
      return this.$auth.user.sub
    },
  },
  created() {
    this.$axios.$get(`/api/utente/${this.email}`)
      .then((utente) => {
        this.utente = utente || {}
        this.prescritions = this.utente.prescricoes;
       this.prescritions.forEach(element => {
         var date= new Date();
         var validade=new Date(element.validade)
         if(date<validade){
           this.prescritionsValidat.push(element)
         }
        });
      })
    }
}
</script>