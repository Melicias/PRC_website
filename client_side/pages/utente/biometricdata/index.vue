
<template>
  <b-container>
      <h1>My Biometric Data</h1>
    <br>
    <br>
    <b-card header="My Biometric Data">
      <b-table striped over sticky-header :items="biometricData" :fields="fields" ref="tableCom">
          <template v-slot:cell(TypeOfBiometricData)="row">
          {{row.item.tipoDadosBiometricos.name}}
        </template>
           <template v-slot:cell(Value)="row">
          {{row.item.valor}}
        </template>
        <template v-slot:cell(DateOfObservation)="row">
          {{row.item.data_observacao.split('T')[0]}}
        </template>
        <template v-slot:cell(appraisal)="row">
          {{row.item.avaliacao}}
        </template>
      </b-table>
    </b-card>
    <br>
     <b-button v-b-toggle.collapse-1 variant="primary" href="/utente/biometricdata/create">Add My Biometric Data</b-button>
    <br>
    <br>

    <b-card header="Biometric types">
      <b-table striped over :items="tipoDadosBiometricos" :fields="fields2" ref="table">
      <template #cell(min)="data">
        {{data.item.type == 2 ? data.item.min : ""}}
      </template>
      <template #cell(max)="data">
        {{data.item.type == 2 ? data.item.max : ""}}
      </template>
      <template #cell(quantitativo)="data">
        <p v-for="tipoDado in data.item.tipoDadosBiometricosQuantitativo">
          {{tipoDado.name + " - min: " + tipoDado.min + " max: " + tipoDado.max }}
        </p>
      </template>
    </b-table>
    </b-card>
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
       tipoDadosBiometricos: [],
      fields: ['TypeOfBiometricData', 'Value','appraisal','DateOfObservation'],
      fields2: ['name', 'min', 'max', 'quantitativo'],
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
          this.biometricData=dadosBiometricos
         console.log(this.biometricDataMax)
      });
        });
      this.$axios.$get('/api/tipoDadosBiometricos')
      .then((tipoDadosBiometricos) => {
        this.tipoDadosBiometricos = tipoDadosBiometricos
        console.log(this.tipoDadosBiometricos)
      })
     
      }
    }
</script>