import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _2718db7f = () => interopDefault(import('..\\pages\\admin\\index.vue' /* webpackChunkName: "pages/admin/index" */))
const _23aa0d1c = () => interopDefault(import('..\\pages\\healthcareProfessional\\index.vue' /* webpackChunkName: "pages/healthcareProfessional/index" */))
const _1a416509 = () => interopDefault(import('..\\pages\\patient\\index.vue' /* webpackChunkName: "pages/patient/index" */))
const _877f2c62 = () => interopDefault(import('..\\pages\\tipoProfissional\\index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _1b57a97b = () => interopDefault(import('..\\pages\\admin\\admins\\index.vue' /* webpackChunkName: "pages/admin/admins/index" */))
const _facf60da = () => interopDefault(import('..\\pages\\admin\\biometricdata\\index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _1e84fef1 = () => interopDefault(import('..\\pages\\admin\\patients\\index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _e5950784 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _1520f153 = () => interopDefault(import('..\\pages\\admin\\specialists\\index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _0ee48b95 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _a1d49aba = () => interopDefault(import('..\\pages\\healthcareProfessional\\addPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/addPacient" */))
const _00915478 = () => interopDefault(import('..\\pages\\healthcareProfessional\\createPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/createPacient" */))
const _1e8339b6 = () => interopDefault(import('..\\pages\\healthcareProfessional\\createPrc.vue' /* webpackChunkName: "pages/healthcareProfessional/createPrc" */))
const _01ea7fd4 = () => interopDefault(import('..\\pages\\healthcareProfessional\\managePrescriptions.vue' /* webpackChunkName: "pages/healthcareProfessional/managePrescriptions" */))
const _359966c8 = () => interopDefault(import('..\\pages\\healthcareProfessional\\patientManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientManagement" */))
const _c89e8dfe = () => interopDefault(import('..\\pages\\healthcareProfessional\\patientsManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientsManagement" */))
const _fa637e6e = () => interopDefault(import('..\\pages\\patient\\biometricdata\\index.vue' /* webpackChunkName: "pages/patient/biometricdata/index" */))
const _2e9f7898 = () => interopDefault(import('..\\pages\\patient\\prescritions\\index.vue' /* webpackChunkName: "pages/patient/prescritions/index" */))
const _5ea56c3c = () => interopDefault(import('..\\pages\\patient\\profile\\index.vue' /* webpackChunkName: "pages/patient/profile/index" */))
const _063ee11c = () => interopDefault(import('..\\pages\\admin\\admins\\changepassword.vue' /* webpackChunkName: "pages/admin/admins/changepassword" */))
const _7751d0a3 = () => interopDefault(import('..\\pages\\admin\\admins\\create.vue' /* webpackChunkName: "pages/admin/admins/create" */))
const _0fb424ea = () => interopDefault(import('..\\pages\\admin\\biometricdata\\create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _412e5640 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _929cf86a = () => interopDefault(import('..\\pages\\admin\\specialists\\create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _02a3b9d6 = () => interopDefault(import('..\\pages\\patient\\biometricdata\\create.vue' /* webpackChunkName: "pages/patient/biometricdata/create" */))
const _25d44e2b = () => interopDefault(import('..\\pages\\admin\\biometricdata\\_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _a45301e4 = () => interopDefault(import('..\\pages\\admin\\patients\\_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _ea8e4e28 = () => interopDefault(import('..\\pages\\admin\\specialists\\_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
const _19b8b9c8 = () => interopDefault(import('..\\pages\\patient\\prescritions\\_id.vue' /* webpackChunkName: "pages/patient/prescritions/_id" */))
const _d23950b4 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

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
    component: _2718db7f,
    name: "admin"
  }, {
    path: "/healthcareProfessional",
    component: _23aa0d1c,
    name: "healthcareProfessional"
  }, {
    path: "/patient",
    component: _1a416509,
    name: "patient"
  }, {
    path: "/tipoProfissional",
    component: _877f2c62,
    name: "tipoProfissional"
  }, {
    path: "/admin/admins",
    component: _1b57a97b,
    name: "admin-admins"
  }, {
    path: "/admin/biometricdata",
    component: _facf60da,
    name: "admin-biometricdata"
  }, {
    path: "/admin/patients",
    component: _1e84fef1,
    name: "admin-patients"
  }, {
    path: "/admin/professionaltype",
    component: _e5950784,
    name: "admin-professionaltype"
  }, {
    path: "/admin/specialists",
    component: _1520f153,
    name: "admin-specialists"
  }, {
    path: "/auth/login",
    component: _0ee48b95,
    name: "auth-login"
  }, {
    path: "/healthcareProfessional/addPacient",
    component: _a1d49aba,
    name: "healthcareProfessional-addPacient"
  }, {
    path: "/healthcareProfessional/createPacient",
    component: _00915478,
    name: "healthcareProfessional-createPacient"
  }, {
    path: "/healthcareProfessional/createPrc",
    component: _1e8339b6,
    name: "healthcareProfessional-createPrc"
  }, {
    path: "/healthcareProfessional/managePrescriptions",
    component: _01ea7fd4,
    name: "healthcareProfessional-managePrescriptions"
  }, {
    path: "/healthcareProfessional/patientManagement",
    component: _359966c8,
    name: "healthcareProfessional-patientManagement"
  }, {
    path: "/healthcareProfessional/patientsManagement",
    component: _c89e8dfe,
    name: "healthcareProfessional-patientsManagement"
  }, {
    path: "/patient/biometricdata",
    component: _fa637e6e,
    name: "patient-biometricdata"
  }, {
    path: "/patient/prescritions",
    component: _2e9f7898,
    name: "patient-prescritions"
  }, {
    path: "/patient/profile",
    component: _5ea56c3c,
    name: "patient-profile"
  }, {
    path: "/admin/admins/changepassword",
    component: _063ee11c,
    name: "admin-admins-changepassword"
  }, {
    path: "/admin/admins/create",
    component: _7751d0a3,
    name: "admin-admins-create"
  }, {
    path: "/admin/biometricdata/create",
    component: _0fb424ea,
    name: "admin-biometricdata-create"
  }, {
    path: "/admin/professionaltype/create",
    component: _412e5640,
    name: "admin-professionaltype-create"
  }, {
    path: "/admin/specialists/create",
    component: _929cf86a,
    name: "admin-specialists-create"
  }, {
    path: "/patient/biometricdata/create",
    component: _02a3b9d6,
    name: "patient-biometricdata-create"
  }, {
    path: "/admin/biometricdata/:name",
    component: _25d44e2b,
    name: "admin-biometricdata-name"
  }, {
    path: "/admin/patients/:email",
    component: _a45301e4,
    name: "admin-patients-email"
  }, {
    path: "/admin/specialists/:email",
    component: _ea8e4e28,
    name: "admin-specialists-email"
  }, {
    path: "/patient/prescritions/:id",
    component: _19b8b9c8,
    name: "patient-prescritions-id"
  }, {
    path: "/",
    component: _d23950b4,
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
