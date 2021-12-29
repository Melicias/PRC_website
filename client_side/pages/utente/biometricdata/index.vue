
<template>
  <b-container>
      <h1>My Biometric Data</h1>
    <br>
    <br>
    <b-card header="My Biometric Data">
      <b-table striped over sticky-header :items="biometricDataMax" :fields="fields" ref="tableCom">
          <template v-slot:cell(TypeOfBiometricData)="row">
          {{row.item.tipoDadosBiometricos.name}}
        </template>
           <template v-slot:cell(Value)="row">
          {{row.item.valor}}
        </template>
        <template v-slot:cell(DateOfObservation)="row">
          {{row.item.data_observacao.split('T')[0]}}
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
      biometricData: [],
      biometricDataMax:[],
      fields: ['TypeOfBiometricData', 'Value', 'DateOfObservation'],
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
          this.utente=utente
         this.$axios.$get(`/api/biometricdata/${this.email}`)
      .then((dadosBiometricos) => {
          this.biometricDataMax=dadosBiometricos
         console.log(this.biometricDataMax)
      });
        });
     
      }
    }
</script>