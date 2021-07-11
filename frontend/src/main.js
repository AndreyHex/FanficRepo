import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store'
import { Button, Layout, Col, Row, Menu, Space,
    Typography, Card, Tag, Form, Input, Upload,
    Pagination, Descriptions, Collapse, Empty }  from "ant-design-vue"


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
    .use(Upload)
    .use(Pagination)
    .use(Descriptions)
    .use(Collapse)
    .use(Empty)

app.use(router)
    .use(store)
    .mount('#app')