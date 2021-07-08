import axios from 'axios'

const apiService = axios.create({
    baseURL: `/api`,
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
})

export default {
    fetchPage,
    login
}

function fetchPage(limit = 12) {
    return apiService.get('fanfics', {
        params: {
            limit
        }
    }).then(respone => respone).catch(error => error)
}

function login(user) {
    console.log(user)
    return apiService.post('auth/signin', user)
}