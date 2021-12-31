export const state = () => ({
  pacient: [],
  pacientEmail: null
})

export const mutations = {
  getPacientEmail(state, pacientEmail) {
    state.pacientEmail = pacientEmail;
  },
  setPacientData(state, pacientData) {
    state.paciente = pacientData;
  },
}

export const actions = {
  
}
