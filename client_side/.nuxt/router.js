import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _01017896 = () => interopDefault(import('../pages/admin/index.vue' /* webpackChunkName: "pages/admin/index" */))
const _160839aa = () => interopDefault(import('../pages/profissionalSaude/index.vue' /* webpackChunkName: "pages/profissionalSaude/index" */))
const _7b685732 = () => interopDefault(import('../pages/tipoProfissional/index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _46015dae = () => interopDefault(import('../pages/utente/index.vue' /* webpackChunkName: "pages/utente/index" */))
const _b0c991f0 = () => interopDefault(import('../pages/admin/biometricdata/index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _b7807284 = () => interopDefault(import('../pages/admin/patients/index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _1343c1d1 = () => interopDefault(import('../pages/admin/professionaltype/index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _68ec52c8 = () => interopDefault(import('../pages/admin/specialists/index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _50aad86f = () => interopDefault(import('../pages/auth/login.vue' /* webpackChunkName: "pages/auth/login" */))
const _3f1ef48a = () => interopDefault(import('../pages/profissionalSaude/addPacient.vue' /* webpackChunkName: "pages/profissionalSaude/addPacient" */))
const _b445159e = () => interopDefault(import('../pages/profissionalSaude/createPacient.vue' /* webpackChunkName: "pages/profissionalSaude/createPacient" */))
const _25bc6f44 = () => interopDefault(import('../pages/profissionalSaude/createPrc.vue' /* webpackChunkName: "pages/profissionalSaude/createPrc" */))
const _7fff71cd = () => interopDefault(import('../pages/profissionalSaude/managePrescriptions.vue' /* webpackChunkName: "pages/profissionalSaude/managePrescriptions" */))
const _79b40530 = () => interopDefault(import('../pages/profissionalSaude/pacientManagement.vue' /* webpackChunkName: "pages/profissionalSaude/pacientManagement" */))
const _4a2c30ce = () => interopDefault(import('../pages/profissionalSaude/pacientsManagement.vue' /* webpackChunkName: "pages/profissionalSaude/pacientsManagement" */))
const _7e26ad08 = () => interopDefault(import('../pages/utente/biometricdata/index.vue' /* webpackChunkName: "pages/utente/biometricdata/index" */))
const _2535b0f1 = () => interopDefault(import('../pages/utente/prescritions/index.vue' /* webpackChunkName: "pages/utente/prescritions/index" */))
const _d8308a80 = () => interopDefault(import('../pages/utente/profile.vue' /* webpackChunkName: "pages/utente/profile" */))
const _737ff4b6 = () => interopDefault(import('../pages/admin/biometricdata/create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _7ce8c30d = () => interopDefault(import('../pages/admin/professionaltype/create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _475b5e14 = () => interopDefault(import('../pages/admin/specialists/create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _4ad735a0 = () => interopDefault(import('../pages/admin/biometricdata/_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _41169e3e = () => interopDefault(import('../pages/admin/patients/_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _9f4cb3d2 = () => interopDefault(import('../pages/admin/specialists/_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
const _2bad3b99 = () => interopDefault(import('../pages/utente/prescritions/_id.vue' /* webpackChunkName: "pages/utente/prescritions/_id" */))
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
    path: "/profissionalSaude",
    component: _160839aa,
    name: "profissionalSaude"
  }, {
    path: "/tipoProfissional",
    component: _7b685732,
    name: "tipoProfissional"
  }, {
    path: "/utente",
    component: _46015dae,
    name: "utente"
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
    path: "/profissionalSaude/addPacient",
    component: _3f1ef48a,
    name: "profissionalSaude-addPacient"
  }, {
    path: "/profissionalSaude/createPacient",
    component: _b445159e,
    name: "profissionalSaude-createPacient"
  }, {
    path: "/profissionalSaude/createPrc",
    component: _25bc6f44,
    name: "profissionalSaude-createPrc"
  }, {
    path: "/profissionalSaude/managePrescriptions",
    component: _7fff71cd,
    name: "profissionalSaude-managePrescriptions"
  }, {
    path: "/profissionalSaude/pacientManagement",
    component: _79b40530,
    name: "profissionalSaude-pacientManagement"
  }, {
    path: "/profissionalSaude/pacientsManagement",
    component: _4a2c30ce,
    name: "profissionalSaude-pacientsManagement"
  }, {
    path: "/utente/biometricdata",
    component: _7e26ad08,
    name: "utente-biometricdata"
  }, {
    path: "/utente/prescritions",
    component: _2535b0f1,
    name: "utente-prescritions"
  }, {
    path: "/utente/profile",
    component: _d8308a80,
    name: "utente-profile"
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
    path: "/utente/prescritions/:id",
    component: _2bad3b99,
    name: "utente-prescritions-id"
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
