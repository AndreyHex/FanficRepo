import axios from 'axios'

const apiService = axios.create({
    baseURL: `/api`,
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
})

export default {
    fetchPage,
    login,
    register,
    getCurrentUser
}

function fetchPage(username = null, limit = 12) {
    return apiService.get('fanfics', {
        params: {
            username: username,
            limit: limit
        }
    })
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