export const state = () => ({
  pacientEmail: null,
  pacientName: null
})

export const mutations = {
  getPacientEmail(state, pacientEmail) {
    state.pacientEmail = pacientEmail;
  },
  getPacientName(state, pacientName) {
    state.pacientName = pacientName;
  }
}

export const actions = {
  
}
