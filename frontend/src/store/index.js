import { createStore } from 'vuex'
import page from './modules/page'
import user from './modules/user'
import fanfics from './modules/fanfics'

export default createStore({
    modules: {
        page,
        user,
        fanfics
    }
})