import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _01017896 = () => interopDefault(import('../pages/admin/index.vue' /* webpackChunkName: "pages/admin/index" */))
const _14f5a3a4 = () => interopDefault(import('../pages/healthcareProfessional/index.vue' /* webpackChunkName: "pages/healthcareProfessional/index" */))
const _38f3a92b = () => interopDefault(import('../pages/patient/index.vue' /* webpackChunkName: "pages/patient/index" */))
const _7b685732 = () => interopDefault(import('../pages/tipoProfissional/index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _05503d74 = () => interopDefault(import('../pages/admin/admins/index.vue' /* webpackChunkName: "pages/admin/admins/index" */))
const _b0c991f0 = () => interopDefault(import('../pages/admin/biometricdata/index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _b7807284 = () => interopDefault(import('../pages/admin/patients/index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _1343c1d1 = () => interopDefault(import('../pages/admin/professionaltype/index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _68ec52c8 = () => interopDefault(import('../pages/admin/specialists/index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _50aad86f = () => interopDefault(import('../pages/auth/login.vue' /* webpackChunkName: "pages/auth/login" */))
const _b890d09e = () => interopDefault(import('../pages/healthcareProfessional/addPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/addPacient" */))
const _246facaa = () => interopDefault(import('../pages/healthcareProfessional/createPacient.vue' /* webpackChunkName: "pages/healthcareProfessional/createPacient" */))
const _00e4a457 = () => interopDefault(import('../pages/healthcareProfessional/createPrc.vue' /* webpackChunkName: "pages/healthcareProfessional/createPrc" */))
const _7ec32af4 = () => interopDefault(import('../pages/healthcareProfessional/managePrescriptions.vue' /* webpackChunkName: "pages/healthcareProfessional/managePrescriptions" */))
const _154c55fa = () => interopDefault(import('../pages/healthcareProfessional/patientManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientManagement" */))
const _9b489fe2 = () => interopDefault(import('../pages/healthcareProfessional/patientsManagement.vue' /* webpackChunkName: "pages/healthcareProfessional/patientsManagement" */))
const _421dd5fe = () => interopDefault(import('../pages/patient/biometricdata/index.vue' /* webpackChunkName: "pages/patient/biometricdata/index" */))
const _526827a2 = () => interopDefault(import('../pages/patient/prescritions/index.vue' /* webpackChunkName: "pages/patient/prescritions/index" */))
const _ae5b3176 = () => interopDefault(import('../pages/patient/profile/index.vue' /* webpackChunkName: "pages/patient/profile/index" */))
const _dd6738ce = () => interopDefault(import('../pages/admin/admins/changepassword.vue' /* webpackChunkName: "pages/admin/admins/changepassword" */))
const _6728886c = () => interopDefault(import('../pages/admin/admins/create.vue' /* webpackChunkName: "pages/admin/admins/create" */))
const _737ff4b6 = () => interopDefault(import('../pages/admin/biometricdata/create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _7ce8c30d = () => interopDefault(import('../pages/admin/professionaltype/create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _475b5e14 = () => interopDefault(import('../pages/admin/specialists/create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _29513480 = () => interopDefault(import('../pages/patient/biometricdata/create.vue' /* webpackChunkName: "pages/patient/biometricdata/create" */))
const _4ad735a0 = () => interopDefault(import('../pages/admin/biometricdata/_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _41169e3e = () => interopDefault(import('../pages/admin/patients/_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _9f4cb3d2 = () => interopDefault(import('../pages/admin/specialists/_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
const _a115a352 = () => interopDefault(import('../pages/patient/prescritions/_id.vue' /* webpackChunkName: "pages/patient/prescritions/_id" */))
const _319db6b5 = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

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
    component: _01017896,
    name: "admin"
  }, {
    path: "/healthcareProfessional",
    component: _14f5a3a4,
    name: "healthcareProfessional"
  }, {
    path: "/patient",
    component: _38f3a92b,
    name: "patient"
  }, {
    path: "/tipoProfissional",
    component: _7b685732,
    name: "tipoProfissional"
  }, {
    path: "/admin/admins",
    component: _05503d74,
    name: "admin-admins"
  }, {
    path: "/admin/biometricdata",
    component: _b0c991f0,
    name: "admin-biometricdata"
  }, {
    path: "/admin/patients",
    component: _b7807284,
    name: "admin-patients"
  }, {
    path: "/admin/professionaltype",
    component: _1343c1d1,
    name: "admin-professionaltype"
  }, {
    path: "/admin/specialists",
    component: _68ec52c8,
    name: "admin-specialists"
  }, {
    path: "/auth/login",
    component: _50aad86f,
    name: "auth-login"
  }, {
    path: "/healthcareProfessional/addPacient",
    component: _b890d09e,
    name: "healthcareProfessional-addPacient"
  }, {
    path: "/healthcareProfessional/createPacient",
    component: _246facaa,
    name: "healthcareProfessional-createPacient"
  }, {
    path: "/healthcareProfessional/createPrc",
    component: _00e4a457,
    name: "healthcareProfessional-createPrc"
  }, {
    path: "/healthcareProfessional/managePrescriptions",
    component: _7ec32af4,
    name: "healthcareProfessional-managePrescriptions"
  }, {
    path: "/healthcareProfessional/patientManagement",
    component: _154c55fa,
    name: "healthcareProfessional-patientManagement"
  }, {
    path: "/healthcareProfessional/patientsManagement",
    component: _9b489fe2,
    name: "healthcareProfessional-patientsManagement"
  }, {
    path: "/patient/biometricdata",
    component: _421dd5fe,
    name: "patient-biometricdata"
  }, {
    path: "/patient/prescritions",
    component: _526827a2,
    name: "patient-prescritions"
  }, {
    path: "/patient/profile",
    component: _ae5b3176,
    name: "patient-profile"
  }, {
    path: "/admin/admins/changepassword",
    component: _dd6738ce,
    name: "admin-admins-changepassword"
  }, {
    path: "/admin/admins/create",
    component: _6728886c,
    name: "admin-admins-create"
  }, {
    path: "/admin/biometricdata/create",
    component: _737ff4b6,
    name: "admin-biometricdata-create"
  }, {
    path: "/admin/professionaltype/create",
    component: _7ce8c30d,
    name: "admin-professionaltype-create"
  }, {
    path: "/admin/specialists/create",
    component: _475b5e14,
    name: "admin-specialists-create"
  }, {
    path: "/patient/biometricdata/create",
    component: _29513480,
    name: "patient-biometricdata-create"
  }, {
    path: "/admin/biometricdata/:name",
    component: _4ad735a0,
    name: "admin-biometricdata-name"
  }, {
    path: "/admin/patients/:email",
    component: _41169e3e,
    name: "admin-patients-email"
  }, {
    path: "/admin/specialists/:email",
    component: _9f4cb3d2,
    name: "admin-specialists-email"
  }, {
    path: "/patient/prescritions/:id",
    component: _a115a352,
    name: "patient-prescritions-id"
  }, {
    path: "/",
    component: _319db6b5,
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
