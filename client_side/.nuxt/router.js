import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _2718db7f = () => interopDefault(import('..\\pages\\admin\\index.vue' /* webpackChunkName: "pages/admin/index" */))
const _877f2c62 = () => interopDefault(import('..\\pages\\tipoProfissional\\index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _098afe8d = () => interopDefault(import('..\\pages\\utente\\index.vue' /* webpackChunkName: "pages/utente/index" */))
const _facf60da = () => interopDefault(import('..\\pages\\admin\\biometricdata\\index.vue' /* webpackChunkName: "pages/admin/biometricdata/index" */))
const _1e84fef1 = () => interopDefault(import('..\\pages\\admin\\patients\\index.vue' /* webpackChunkName: "pages/admin/patients/index" */))
const _e5950784 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\index.vue' /* webpackChunkName: "pages/admin/professionaltype/index" */))
const _1520f153 = () => interopDefault(import('..\\pages\\admin\\specialists\\index.vue' /* webpackChunkName: "pages/admin/specialists/index" */))
const _0ee48b95 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _1d903b08 = () => interopDefault(import('..\\pages\\profissionalSaude\\createPrc.vue' /* webpackChunkName: "pages/profissionalSaude/createPrc" */))
const _cd386162 = () => interopDefault(import('..\\pages\\profissionalSaude\\createPrescription.vue' /* webpackChunkName: "pages/profissionalSaude/createPrescription" */))
const _7917c380 = () => interopDefault(import('..\\pages\\profissionalSaude\\createUser.vue' /* webpackChunkName: "pages/profissionalSaude/createUser" */))
const _12bd3bdc = () => interopDefault(import('..\\pages\\profissionalSaude\\main.vue' /* webpackChunkName: "pages/profissionalSaude/main" */))
const _7ba81b07 = () => interopDefault(import('..\\pages\\profissionalSaude\\userManagement.vue' /* webpackChunkName: "pages/profissionalSaude/userManagement" */))
const _17788ca2 = () => interopDefault(import('..\\pages\\profissionalSaude\\usersManagement.vue' /* webpackChunkName: "pages/profissionalSaude/usersManagement" */))
const _6784c1b8 = () => interopDefault(import('..\\pages\\utente\\profile.vue' /* webpackChunkName: "pages/utente/profile" */))
const _0fb424ea = () => interopDefault(import('..\\pages\\admin\\biometricdata\\create.vue' /* webpackChunkName: "pages/admin/biometricdata/create" */))
const _412e5640 = () => interopDefault(import('..\\pages\\admin\\professionaltype\\create.vue' /* webpackChunkName: "pages/admin/professionaltype/create" */))
const _929cf86a = () => interopDefault(import('..\\pages\\admin\\specialists\\create.vue' /* webpackChunkName: "pages/admin/specialists/create" */))
const _25d44e2b = () => interopDefault(import('..\\pages\\admin\\biometricdata\\_name.vue' /* webpackChunkName: "pages/admin/biometricdata/_name" */))
const _a45301e4 = () => interopDefault(import('..\\pages\\admin\\patients\\_email.vue' /* webpackChunkName: "pages/admin/patients/_email" */))
const _ea8e4e28 = () => interopDefault(import('..\\pages\\admin\\specialists\\_email.vue' /* webpackChunkName: "pages/admin/specialists/_email" */))
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
    path: "/tipoProfissional",
    component: _877f2c62,
    name: "tipoProfissional"
  }, {
    path: "/utente",
    component: _098afe8d,
    name: "utente"
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
    path: "/profissionalSaude/createPrc",
    component: _1d903b08,
    name: "profissionalSaude-createPrc"
  }, {
    path: "/profissionalSaude/createPrescription",
    component: _cd386162,
    name: "profissionalSaude-createPrescription"
  }, {
    path: "/profissionalSaude/createUser",
    component: _7917c380,
    name: "profissionalSaude-createUser"
  }, {
    path: "/profissionalSaude/main",
    component: _12bd3bdc,
    name: "profissionalSaude-main"
  }, {
    path: "/profissionalSaude/userManagement",
    component: _7ba81b07,
    name: "profissionalSaude-userManagement"
  }, {
    path: "/profissionalSaude/usersManagement",
    component: _17788ca2,
    name: "profissionalSaude-usersManagement"
  }, {
    path: "/utente/profile",
    component: _6784c1b8,
    name: "utente-profile"
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
