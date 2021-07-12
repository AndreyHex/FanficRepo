import api from "@/api/axios"

const state = () => ({
    user: {},
    status: false,
    message: {}
})

const getters = {
    getUsername(state) {
        return state.user.username ?? ''
    },
    isLogined(state) {
        return state.status
    },
    getRoles(state) {
        return state.user.authorities
    }
}

const actions = {
    login({commit}, user) {
        api.login(user)
            .then(response => commit('setStatusSuccess', response.data))
            .catch(error => commit('setStatusError', error))
    },
    logout({commit}) {
        commit('setStatusLogout')
    },
    register({commit}, user) {
        api.register(user)
            .then(response => commit('setStatusSuccess', response.data))
            .catch(error => commit('setStatusError', error))
    },
    checkIsLogined({commit}) {
        if(localStorage.getItem('token') != null) {
            api.getCurrentUser()
                .then(response => commit('setStatusLogined', response.data))
                .catch(() => localStorage.removeItem('token'))
        }
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
    },
    setStatusLogined(state, data) {
        state.status = true
        state.user = data
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}