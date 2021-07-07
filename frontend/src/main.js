import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router'
import { Button, Layout, Col, Row, Menu, Space, Typography, Card, Tag, Form, Input }  from "ant-design-vue"


const app = createApp(App)

app.use(Layout)
    .use(Button)
    .use(Col)
    .use(Row)
    .use(Menu)
    .use(Space)
    .use(Typography)
    .use(Card)
    .use(Tag)
    .use(Form)
    .use(Input)

app.use(router).mount('#app')