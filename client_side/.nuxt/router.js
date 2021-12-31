import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _059fd822 = () => interopDefault(import('..\\pages\\admin\\index.vue' /* webpackChunkName: "pages/admin/index" */))
const _2d55ccb9 = () => interopDefault(import('..\\pages\\profissionalSaude\\index.vue' /* webpackChunkName: "pages/profissionalSaude/index" */))
const _3bae8b5f = () => interopDefault(import('..\\pages\\tipoProfissional\\index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _134a57c6 = () => interopDefault(import('..\\pages\\utente\\index.vue' /* webpackChunkName: "pages/utente/index" */))
const _73fb2923 = () => interopDefault(import('..\\pages\\admin\\biometricdata\\index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _a1f7293e = () => interopDefault(import('..\\pages\\admin\\patients\\index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _290d0ea4 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _718ceee3 = () => interopDefault(import('..\\pages\\admin\\specialists\\index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _15cc8525 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _3c425888 = () => interopDefault(import('..\\pages\\profissionalSaude\\addPacient.vue' /* webpackChunkName: "pages/profissionalSaude/addPacient" */))
const _07981e82 = () => interopDefault(import('..\\pages\\profissionalSaude\\createPacient.vue' /* webpackChunkName: "pages/profissionalSaude/createPacient" */))
const _2c303aec = () => interopDefault(import('..\\pages\\profissionalSaude\\createPrc.vue' /* webpackChunkName: "pages/profissionalSaude/createPrc" */))
const _34028adb = () => interopDefault(import('..\\pages\\profissionalSaude\\managePrescriptions.vue' /* webpackChunkName: "pages/profissionalSaude/managePrescriptions" */))
const _fbb3ec84 = () => interopDefault(import('..\\pages\\profissionalSaude\\pacientManagement.vue' /* webpackChunkName: "pages/profissionalSaude/pacientManagement" */))
const _3e8f166a = () => interopDefault(import('..\\pages\\profissionalSaude\\pacientsManagement.vue' /* webpackChunkName: "pages/profissionalSaude/pacientsManagement" */))
const _77235e98 = () => interopDefault(import('..\\pages\\utente\\profile.vue' /* webpackChunkName: "pages/utente/profile" */))
const _331e45fb = () => interopDefault(import('..\\pages\\admin\\biometricdata\\create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _a92c3060 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _30758f8a = () => interopDefault(import('..\\pages\\admin\\specialists\\create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _173727bb = () => interopDefault(import('..\\pages\\admin\\biometricdata\\_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _2d44a09e = () => interopDefault(import('..\\pages\\admin\\patients\\_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _8866e548 = () => interopDefault(import('..\\pages\\admin\\specialists\\_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
const _0369bd36 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/admin",
    component: _059fd822,
    name: "admin"
  }, {
    path: "/profissionalSaude",
    component: _2d55ccb9,
    name: "profissionalSaude"
  }, {
    path: "/tipoProfissional",
    component: _3bae8b5f,
    name: "tipoProfissional"
  }, {
    path: "/utente",
    component: _134a57c6,
    name: "utente"
  }, {
    path: "/admin/biometricdata",
    component: _73fb2923,
    name: "admin-biometricdata"
  }, {
    path: "/admin/patients",
    component: _a1f7293e,
    name: "admin-patients"
  }, {
    path: "/admin/professionaltype",
    component: _290d0ea4,
    name: "admin-professionaltype"
  }, {
    path: "/admin/specialists",
    component: _718ceee3,
    name: "admin-specialists"
  }, {
    path: "/auth/login",
    component: _15cc8525,
    name: "auth-login"
  }, {
    path: "/profissionalSaude/addPacient",
    component: _3c425888,
    name: "profissionalSaude-addPacient"
  }, {
    path: "/profissionalSaude/createPacient",
    component: _07981e82,
    name: "profissionalSaude-createPacient"
  }, {
    path: "/profissionalSaude/createPrc",
    component: _2c303aec,
    name: "profissionalSaude-createPrc"
  }, {
    path: "/profissionalSaude/managePrescriptions",
    component: _34028adb,
    name: "profissionalSaude-managePrescriptions"
  }, {
    path: "/profissionalSaude/pacientManagement",
    component: _fbb3ec84,
    name: "profissionalSaude-pacientManagement"
  }, {
    path: "/profissionalSaude/pacientsManagement",
    component: _3e8f166a,
    name: "profissionalSaude-pacientsManagement"
  }, {
    path: "/utente/profile",
    component: _77235e98,
    name: "utente-profile"
  }, {
    path: "/admin/biometricdata/create",
    component: _331e45fb,
    name: "admin-biometricdata-create"
  }, {
    path: "/admin/professionaltype/create",
    component: _a92c3060,
    name: "admin-professionaltype-create"
  }, {
    path: "/admin/specialists/create",
    component: _30758f8a,
    name: "admin-specialists-create"
  }, {
    path: "/admin/biometricdata/:name",
    component: _173727bb,
    name: "admin-biometricdata-name"
  }, {
    path: "/admin/patients/:email",
    component: _2d44a09e,
    name: "admin-patients-email"
  }, {
    path: "/admin/specialists/:email",
    component: _8866e548,
    name: "admin-specialists-email"
  }, {
    path: "/",
    component: _0369bd36,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
