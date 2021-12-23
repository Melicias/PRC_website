import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _3bae8b5f = () => interopDefault(import('..\\pages\\tipoProfissional\\index.vue' /* webpackChunkName: "pages/tipoProfissional/index" */))
const _15cc8525 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _c5ab0de0 = () => interopDefault(import('..\\pages\\profissionalSaude\\createUser.vue' /* webpackChunkName: "pages/profissionalSaude/createUser" */))
const _760f83a2 = () => interopDefault(import('..\\pages\\profissionalSaude\\main.vue' /* webpackChunkName: "pages/profissionalSaude/main" */))
const _fcee5ddc = () => interopDefault(import('..\\pages\\profissionalSaude\\usersManagement.vue' /* webpackChunkName: "pages/profissionalSaude/usersManagement" */))
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
    path: "/tipoProfissional",
    component: _3bae8b5f,
    name: "tipoProfissional"
  }, {
    path: "/auth/login",
    component: _15cc8525,
    name: "auth-login"
  }, {
    path: "/profissionalSaude/createUser",
    component: _c5ab0de0,
    name: "profissionalSaude-createUser"
  }, {
    path: "/profissionalSaude/main",
    component: _760f83a2,
    name: "profissionalSaude-main"
  }, {
    path: "/profissionalSaude/usersManagement",
    component: _fcee5ddc,
    name: "profissionalSaude-usersManagement"
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
