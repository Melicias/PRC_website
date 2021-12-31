<template>
    <div>
        <b-container>
            <h1>My Pacients</h1>
            <div class="btnStyle">
              <b-button variant="primary" to="/profissionalSaude/">Back</b-button>
            </div>
            <b-table striped hover selectable :items="utentes" :fields="fields" @row-clicked="userManagement" caption-top>
              <template #table-caption>Click in pacient row to manage a pacient individually </template>
              <template v-slot:cell(Birthdate)="data">
                <p>
                  {{data.item.dataNasc.split('T')[0]}}
                </p>
              </template>
              <template v-slot:cell(BiometricData(total))="data">
                <p>
                  {{data.item.dadosBiometricos.length}}
                </p>
              </template>
              <template v-slot:cell(PRC(total))="data">
                <p>
                  {{data.item.prescricoes.length}}
                </p>
              </template>
              <template v-slot:cell(specialists)="data">
                <p>
                  {{data.item.profissionalSaude.length}}
                </p>
              </template>
              <template #cell(blocked)="data">
                <div v-if="data.item.blocked == 0">
                  <p>No</p>
                </div>
                <div v-else>
                  <p>Yes</p>
                </div>
              </template> 
              <template #cell(Actions)="data">
                <b-button variant="danger" @click="blockUtente(data.item.email)">{{data.item.blocked == 0 ? "Block" : "Unblock"}}</b-button>
              </template> 
            </b-table>  
        </b-container>
        
    </div>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'email', 'Birthdate', 'BiometricData(total)', 'PRC(total)', 'specialists', 'blocked', 'Actions'],
      utentes: []
    }
  },
  methods: {
    userManagement(pacient){
      if(pacient != null){
        this.$store.commit('getPacientEmail', pacient.email)
      }
      this.$router.push({path: 'pacientManagement'});
    },
    blockUtente(email) {
      this.$axios.put(`/api/utente/block/${email}`)
        .then(msg => {
          this.$toast.success("Patient blocked with success").goAway(1500)
          this.fetchUtente()
        })
        .catch(error => {
          this.$toast.error('error while deleting').goAway(3000)
        })
    },
    fetchUtente(){
      this.$axios.$get('/api/utente')
      .then((utentes) => {
        console.log(utentes)
        this.utentes = utentes
      })
    }
  },
  created () {
    this.fetchUtente()
  },
}
</script>
<style scoped>
.btnStyle{
  margin-top: 15px;
}
h2{
  text-align: center;
}
</style>