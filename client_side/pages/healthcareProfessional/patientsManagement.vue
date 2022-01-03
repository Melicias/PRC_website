<template>
    <div>
        <b-container>
          <h1>My Patients (total {{ utentes.length }})</h1>
          <div class="btnStyle">
            <b-button variant="primary" to="/healthcareProfessional/">Back</b-button>
          </div>
          <b-card class="mt-3 margin" header="Find for a Patient">
              <b-form-group
                  id="input-group-1"
                  label="Patient email address:"
                  label-for="input-1">
                  <b-form-input
                  id="input-1"
                  v-model="pacient.email"
                  type="email"
                  placeholder="Enter email"
                  required
                  ></b-form-input>
              </b-form-group>
              <b-button @click="pacientManagement(pacient)" variant="primary">Manage</b-button>
          </b-card>
          <b-table striped hover selectable :items="utentes" :fields="fields" @row-clicked="pacientManagement" caption-top>
            <template #table-caption>Click in pacient row to manage a patient individually </template>
            <template v-slot:cell(BiometricData(total))="data">
              <p>
                {{data.item.dadosBiometricos.length}}
              </p>
            </template>
            <template v-slot:cell(PRC(total))="data">
              <p>
                {{data.item.prcs.length}}
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
              <b-button :variant="data.item.blocked == 0 ? 'danger' : 'success'" @click="blockUtente(data.item.email)">{{data.item.blocked == 0 ? "Block" : "Unblock"}}</b-button>
            </template> 
          </b-table>  
      </b-container>
    </div>
</template>
<script>
export default {
  data () {
    return {
      fields: ['name', 'email', 'BiometricData(total)', 'PRC(total)', 'specialists', 'blocked', 'Actions'],
      utentes: [],
      pacient: {
        email: ''
      }
    }
  },
  methods: {
    pacientManagement(pacient){
      if(pacient != null){
        this.$store.commit('getPacientEmail', pacient.email)
      }
      this.$router.push({path: 'patientManagement'});
    },
    blockUtente(email) {
      this.$axios.put(`/api/utente/block/${email}`)
        .then(msg => {
          this.$toast.success("Patient block state changed with success").goAway(1500)
          this.fetchUtente()
        })
        .catch(error => {
          this.$toast.error('error while deleting').goAway(3000)
        })
    },
    fetchUtente(){
      this.$axios.$get('/api/utente/comprofissional/'+this.$auth.user.sub)
      .then((utentes) => {
        console.log(utentes)
        this.utentes = utentes
      })
    }
  },
  created () {
    if (!this.$auth.user.groups.includes('ProfissionalSaude')) {
      this.$router.push('nuxt-error')
    }
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