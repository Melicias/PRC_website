import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _01017896 = () => interopDefault(import('../pages/admin/index.vue' /* webpackChunkName: "pages/admin/index" */))
const _7b685732 = () => interopDefault(import('../pages/tipoProfissional/index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _46015dae = () => interopDefault(import('../pages/utente/index.vue' /* webpackChunkName: "pages/utente/index" */))
const _b0c991f0 = () => interopDefault(import('../pages/admin/biometricdata/index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _b7807284 = () => interopDefault(import('../pages/admin/patients/index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _1343c1d1 = () => interopDefault(import('../pages/admin/professionaltype/index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _68ec52c8 = () => interopDefault(import('../pages/admin/specialists/index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _50aad86f = () => interopDefault(import('../pages/auth/login.vue' /* webpackChunkName: "pages/auth/login" */))
const _25bc6f44 = () => interopDefault(import('../pages/profissionalSaude/createPrc.vue' /* webpackChunkName: "pages/profissionalSaude/createPrc" */))
const _4ea167ad = () => interopDefault(import('../pages/profissionalSaude/createPrescription.vue' /* webpackChunkName: "pages/profissionalSaude/createPrescription" */))
const _0b2acc44 = () => interopDefault(import('../pages/profissionalSaude/createUser.vue' /* webpackChunkName: "pages/profissionalSaude/createUser" */))
const _4af3d6f0 = () => interopDefault(import('../pages/profissionalSaude/main.vue' /* webpackChunkName: "pages/profissionalSaude/main" */))
const _5e66db36 = () => interopDefault(import('../pages/profissionalSaude/userManagement.vue' /* webpackChunkName: "pages/profissionalSaude/userManagement" */))
const _66e30104 = () => interopDefault(import('../pages/profissionalSaude/usersManagement.vue' /* webpackChunkName: "pages/profissionalSaude/usersManagement" */))
const _d8308a80 = () => interopDefault(import('../pages/utente/profile.vue' /* webpackChunkName: "pages/utente/profile" */))
const _737ff4b6 = () => interopDefault(import('../pages/admin/biometricdata/create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _7ce8c30d = () => interopDefault(import('../pages/admin/professionaltype/create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _475b5e14 = () => interopDefault(import('../pages/admin/specialists/create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _4ad735a0 = () => interopDefault(import('../pages/admin/biometricdata/_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _41169e3e = () => interopDefault(import('../pages/admin/patients/_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _9f4cb3d2 = () => interopDefault(import('../pages/admin/specialists/_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
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
    component: _059fd822,
    name: "admin"
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
    path: "/profissionalSaude/createPrc",

    component: _1d903b08,
    name: "profissionalSaude-createPrc"
  }, {
    path: "/profissionalSaude/createPrescription",
    component: _cd386162,

    name: "profissionalSaude-createPrescription"
  }, {
    path: "/profissionalSaude/createUser",
    component: _c5ab0de0,
    name: "profissionalSaude-createUser"
  }, {
    path: "/profissionalSaude/main",
    component: _760f83a2,
    name: "profissionalSaude-main"
  }, {
    path: "/profissionalSaude/userManagement",
    component: _5e66db36,
    name: "profissionalSaude-userManagement"
  }, {
    path: "/profissionalSaude/usersManagement",
    component: _fcee5ddc,
    name: "profissionalSaude-usersManagement"
  }, {
    path: "/utente/profile",
    component: _d8308a80,
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
