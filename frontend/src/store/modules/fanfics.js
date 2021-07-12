import api from '@/api/axios'

const state = () => ({
    fanfic: {},
    status: ''
})

const getters = {
    getFanfic(state) {
        return state.fanfic
    },
    isUploading(state) {
        return state.status === 'uploading'
    },
    isLoading(state) {
      return state.status === 'loading'
    },
    getStatus(state) {
        return state.status
    }
}

const actions = {
    loadFanfic({dispatch, commit}, id) {
        commit('setLoading')
        api.fetchFanfic(id)
            .then(response => {
                commit('setSuccess', response.data)
                dispatch('loadChapters')
            })
            .catch(error => commit('setError', error))
    },
    loadChapters({commit, state}) {
        api.fetchFanficChapters(state.fanfic.id)
            .then(response => commit('setChapters', response.data))

    },
    saveFanfic({commit}, fanfic) {
        commit('setUploading')
        return new Promise((resolve, reject) => {
            api.saveFanfic(fanfic)
                .then(response => {
                    commit('setSuccess', response.data)
                    resolve()
                    })
                .catch(err => {
                    commit('setError', err)
                    reject()
                })
        })
    },
    addChapter({commit}) {
        commit('addChapter')
    },
    saveChapters({commit, state}) {
        commit('setUploading')
        api.saveChapters(state.fanfic)
            .then(response => {
                commit('setChapters', response.data)
            })
    }
}

const mutations = {
    setLoading(state) {
        state.status = 'loading'
    },
    setUploading(state) {
        state.status = 'uploading'
    },
    setSuccess(state, data) {
        state.status = 'done'
        state.fanfic = data
    },
    setError(state, data) {
        state.status = data
    },
    setChapters(state, data) {
        state.fanfic.chapters = data
        state.status = 'done'
    },
    addChapter(state) {
        state.fanfic.chapters.push({
            number: state.fanfic.chapters?.length+1,
            fanficId: state.fanfic.id,
            title: '',
            text: ''
        })
    },
    setTitle(state, data) {
        state.fanfic.chapters[data.index].title = data.title
    },
    setText(state, data) {
        state.fanfic.chapters[data.index].text = data.text
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}