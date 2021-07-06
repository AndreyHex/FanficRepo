import { createApp } from 'vue'
import App from './App.vue'
import installElementPlus from './plugins/element'
import router from './router/router'

const app = createApp(App).use(router)
installElementPlus(app)
app.use(router).mount('#app')