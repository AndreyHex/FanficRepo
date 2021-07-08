import api from "@/api/axios"

const state = () => ({
    user: {},
    status: false,
    message: {}
})

const getters = {
    getUsername(state) {
        return state.username
    },
    isLogined(state) {
        return state.status
    },
    getRoles(state) {
        return state.user.authorities
    }
}

const actions = {
    login({commit}, username, password) {
        api.login(username, password).then(response => commit('setStatusSuccess', response.data))
            .catch(error => commit('setStatusError', error))
    },
    logout({commit}) {
        commit('setStatusLogout')
    }
}

const mutations = {
    setStatusSuccess(state, data) {
        state.status = true
        state.user = data.user
        state.message = data.message
        localStorage.setItem('token', data.token)
    },
    setStatusError(state, error) {
        state.message = error
    },
    setStatusLogout(state) {
        state.user = {}
        state.status = ''
        localStorage.removeItem('token')
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}