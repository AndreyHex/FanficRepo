import { createStore } from 'vuex'
import page from './modules/page'
import user from './modules/user'

export default createStore({
    modules: {
        page,
        user
    }
})