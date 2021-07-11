import axios from 'axios'

const apiService = axios.create({
    baseURL: `/api`,
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
})

export default {
    fetchFanfic,
    saveFanfic,
    fetchPage,
    login,
    register,
    getCurrentUser
}

function fetchPage(username = null, page = 0, limit = 12) {
    return apiService.get('fanfics', {
        params: {
            username: username,
            page: page,
            limit: limit
        }
    })
}

function fetchFanfic(id) {
    return apiService.get('fanfics/'+id)
}

function saveFanfic(fanfic) {
    return apiService.post('fanfics', fanfic)
}

function login(user) {
    console.log(user)
    return apiService.post('auth/signin', user)
}

function register(user) {
    console.log(user)
    return apiService.post('auth/signup', user)
}

function getCurrentUser() {
    return apiService.get('auth/current')
}