import api from "@/api/axios"

const state = () => ({
    page: {},
    loading: false
})

const getters = {
    getPage(state) {
        return state.page
    },
    getFanficByIndex: (state) => (index) => {
        return state.page.content[index]
    },
    isLoading(state) {
        return state.loading
    }
}

const actions = {
    loadPage({commit}, payload) {
        commit('setPageLoading')
        api.fetchPage(payload.username, payload.page).then(response => commit('setPageSuccess', response.data))
            .catch(error => commit('setPageError', error))
    }
}

const mutations = {
    setPageLoading(state) {
        state.loading = true
    },
    setPageSuccess(state, payload) {
        state.page = payload
        state.loading = false
    },
    setPageError(state, error) {
        state.page = { error }
        state.loading = false
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}