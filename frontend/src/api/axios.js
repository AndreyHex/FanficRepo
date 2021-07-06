import axios from 'axios'

export const apiService = axios.create({
    baseURL: `/api`,
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
})