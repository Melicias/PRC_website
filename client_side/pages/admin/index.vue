<template>
  <b-container>
    <h3>Welcome {{email}}</h3>
    <br><br>
    <div>
      <nuxt-link :to="'/admin/patients'">
        <div class="img-with-text">
          <img width="100" height="100" src="../../img/user.png" alt="Image not loaded" />
          <p>Patients management</p>
        </div>
      </nuxt-link>
      <nuxt-link :to="'/admin/specialists'">
        <div class="img-with-text">
          <img width="100" height="100" src="../../img/doctor.png" alt="Image not loaded" />
          <p>Healthcare specialists management</p>
        </div>
      </nuxt-link>
      <nuxt-link :to="'/admin/biometricdata'">
        <div class="img-with-text">
          <img width="100" height="100" src="../../img/blood-test.png" alt="Image not loaded" />
          <p>Biometric data management</p>
        </div>
      </nuxt-link>
      <nuxt-link :to="'/admin/professionaltype'">
        <div class="img-with-text">
          <img width="100" height="100" src="../../img/hematologist.png" alt="Image not loaded" />
          <p>Healthcare specialists type management</p>
        </div>
      </nuxt-link>
    </div>
    <br><br><br>

    <div class="content" v-if="this.statistics != null">
      <div>
        <b-card class="mt-6" header="Healthcare Specialists">
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/group.png" alt="Image not loaded" />
            <p>Total:</p>
            <p>{{statistics.nrPS}}</p>
          </div>
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/block-user.png" alt="Image not loaded" />
            <p>Blocked:</p>
            <p>{{statistics.PSBloqueados}}</p>
          </div>
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/delete-user.png" alt="Image not loaded" />
            <p>Deleted:</p>
            <p>{{statistics.PSApagados}}</p>
          </div>
        </b-card>
      </div>
      <div>
        <b-card class="mt-6" header="Patients">
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/group.png" alt="Image not loaded" />
            <p>Total:</p>
            <p>{{statistics.nrUtentes}}</p>
          </div>
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/block-user.png" alt="Image not loaded" />
            <p>Blocked:</p>
            <p>{{statistics.utentesBloqueados}}</p>
          </div>
          <div class="img-with-text">
            <img width="80" height="80" src="../../img/delete-user.png" alt="Image not loaded" />
            <p>Deleted:</p>
            <p>{{statistics.utentesApagados}}</p>
          </div>
        </b-card>
      </div>
    </div>
  </b-container>
</template>

<style scoped>
.img-with-text {
  width:200px;
  text-align:center;
  display:block;
  background-color: transparent;
  border: 1px solid transparent;
  margin-right: 10px;
  margin-bottom: 1px;
  float:left;
}
.content{
  clear:both;
  display: block !important;
  margin-left: auto !important;
  margin-right: auto !important;
}
</style>

<script>
export default {
  data() {
    return {
      email: null,
      statistics: null
    }
  },
  created() {
    this.email = this.$auth.user.sub
    this.$axios.$get('/api/admin/statistics')
      .then((statistics) => {
        this.statistics = statistics
      })
      .catch(error => {
      })
  },
}
</script>
