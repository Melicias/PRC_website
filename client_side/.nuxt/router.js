import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'


const _059fd822 = () => interopDefault(import('..\\pages\\admin\\index.vue' /* webpackChunkName: "pages/admin/index" */))
const _123daf02 = () => interopDefault(import('..\\pages\\healthcareProfessional\\index.vue' /* webpackChunkName: "pages/healthcareProfessional/index" */))
const _71277d0e = () => interopDefault(import('..\\pages\\patient\\index.vue' /* webpackChunkName: "pages/patient/index" */))
const _3bae8b5f = () => interopDefault(import('..\\pages\\tipoProfissional\\index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _7eb11c2a = () => interopDefault(import('..\\pages\\admin\\admins\\index.vue' /* webpackChunkName: "pages/admin/admins/index" */))
const _73fb2923 = () => interopDefault(import('..\\pages\\admin\\biometricdata\\index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _a1f7293e = () => interopDefault(import('..\\pages\\admin\\patients\\index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _290d0ea4 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _718ceee3 = () => interopDefault(import('..\\pages\\admin\\specialists\\index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _15cc8525 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _1925f713 = () => interopDefault(import('..\\pages\\healthcareProfessional\\addPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/addPacient" */))
const _88c50bf0 = () => interopDefault(import('..\\pages\\healthcareProfessional\\createPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/createPacient" */))
const _8b485e96 = () => interopDefault(import('..\\pages\\healthcareProfessional\\createPrc.vue' /* webpackChunkName: "pages/healthcareProfessional/createPrc" */))
const _4c083964 = () => interopDefault(import('..\\pages\\healthcareProfessional\\managePrescriptions.vue' /* webpackChunkName: "pages/healthcareProfessional/managePrescriptions" */))
const _c80f7750 = () => interopDefault(import('..\\pages\\healthcareProfessional\\patientManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientManagement" */))
const _012d8d71 = () => interopDefault(import('..\\pages\\healthcareProfessional\\patientsManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientsManagement" */))
const _b23e134e = () => interopDefault(import('..\\pages\\patient\\biometricdata\\index.vue' /* webpackChunkName: "pages/patient/biometricdata/index" */))
const _23a89c24 = () => interopDefault(import('..\\pages\\patient\\prescritions\\index.vue' /* webpackChunkName: "pages/patient/prescritions/index" */))
const _501b6b72 = () => interopDefault(import('..\\pages\\patient\\profile\\index.vue' /* webpackChunkName: "pages/patient/profile/index" */))
const _20f34502 = () => interopDefault(import('..\\pages\\admin\\admins\\changepassword.vue' /* webpackChunkName: "pages/admin/admins/changepassword" */))
const _7bfb1633 = () => interopDefault(import('..\\pages\\admin\\admins\\create.vue' /* webpackChunkName: "pages/admin/admins/create" */))
const _331e45fb = () => interopDefault(import('..\\pages\\admin\\biometricdata\\create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _a92c3060 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _30758f8a = () => interopDefault(import('..\\pages\\admin\\specialists\\create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _5cf21f85 = () => interopDefault(import('..\\pages\\patient\\biometricdata\\create.vue' /* webpackChunkName: "pages/patient/biometricdata/create" */))
const _173727bb = () => interopDefault(import('..\\pages\\admin\\biometricdata\\_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _2d44a09e = () => interopDefault(import('..\\pages\\admin\\patients\\_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _8866e548 = () => interopDefault(import('..\\pages\\admin\\specialists\\_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
const _2437578c = () => interopDefault(import('..\\pages\\patient\\prescritions\\_id.vue' /* webpackChunkName: "pages/patient/prescritions/_id" */))
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
    path: "/healthcareProfessional",
    component: _123daf02,
    name: "healthcareProfessional"
  }, {
    path: "/patient",
    component: _71277d0e,
    name: "patient"
  }, {
    path: "/tipoProfissional",
    component: _3bae8b5f,
    name: "tipoProfissional"
  }, {
    path: "/admin/admins",
    component: _7eb11c2a,
    name: "admin-admins"
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
    path: "/healthcareProfessional/addPacient",
    component: _1925f713,
    name: "healthcareProfessional-addPacient"
  }, {
    path: "/healthcareProfessional/createPacient",
    component: _88c50bf0,
    name: "healthcareProfessional-createPacient"
  }, {
    path: "/healthcareProfessional/createPrc",
    component: _8b485e96,
    name: "healthcareProfessional-createPrc"
  }, {
    path: "/healthcareProfessional/managePrescriptions",
    component: _4c083964,
    name: "healthcareProfessional-managePrescriptions"
  }, {
    path: "/healthcareProfessional/patientManagement",
    component: _c80f7750,
    name: "healthcareProfessional-patientManagement"
  }, {
    path: "/healthcareProfessional/patientsManagement",
    component: _012d8d71,
    name: "healthcareProfessional-patientsManagement"
  }, {
    path: "/patient/biometricdata",
    component: _b23e134e,
    name: "patient-biometricdata"
  }, {
    path: "/patient/prescritions",
    component: _23a89c24,
    name: "patient-prescritions"
  }, {
    path: "/patient/profile",
    component: _501b6b72,
    name: "patient-profile"
  }, {
    path: "/admin/admins/changepassword",
    component: _20f34502,
    name: "admin-admins-changepassword"
  }, {
    path: "/admin/admins/create",
    component: _7bfb1633,
    name: "admin-admins-create"
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
    path: "/patient/biometricdata/create",
    component: _5cf21f85,
    name: "patient-biometricdata-create"
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
    path: "/patient/prescritions/:id",
    component: _2437578c,
    name: "patient-prescritions-id"
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
