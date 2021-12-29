<template>
  <div id="app">
    <b-navbar toggleable="lg">
      <b-navbar-brand :href="$auth.loggedIn ? (this.$auth.user.groups.includes('Admin') ? '/admin' : (this.$auth.user.groups.includes('Utente') ? '/utente' : '/ProfissionalSaude')) : '/auth/login'">PRC</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <div v-if="$auth.loggedIn">
          <div v-if="this.$auth.user.groups.includes('Admin')">
            <b-navbar-nav>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/admin/patients">Patients</nuxt-link>
              </li>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/admin/specialists">Healthcare Specialists</nuxt-link>
              </li>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/admin/biometricdata">Biometric Data</nuxt-link>
              </li>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/admin/professionaltype">Healthcare Specialists types</nuxt-link>
              </li>
            </b-navbar-nav>
          </div>
          <div v-if="this.$auth.user.groups.includes('Utente')">
            <b-navbar-nav>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/adminpatients">EXEMPLO</nuxt-link>
              </li>

            </b-navbar-nav>
          </div>
          <div v-if="this.$auth.user.groups.includes('ProfissionalSaude')">
            <b-navbar-nav>
              <li class="nav-item">
                <nuxt-link class="nav-link" to="/adminpatients">EXEMPLO</nuxt-link>
              </li>

            </b-navbar-nav>
          </div>
        </div>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown v-if="$auth.loggedIn" right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>{{ $auth.user.sub }}</em>
            </template>
            <b-dropdown-item @click.prevent="signOut">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
          <li class="nav-item" v-else>
            <nuxt-link class="nav-link" to="/auth/login">Sign In</nuxt-link>
          </li>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <main>
      <Nuxt/>
    </main>
  </div>
</template>
<script>
export default {
  methods: {
    signOut() {
      console.log(this.$auth);
      this.$auth.logout()
      this.$router.push('/')
    }
  }
}
</script>
