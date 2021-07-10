import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'

import Home from '@/views/Home.vue'
import MyFanfics from "@/views/MyFanfics";
import FanficGrid from "@/components/FanficGrid";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/myfanfics',
    name: 'MyFanfics',
    component: MyFanfics,
    beforeEnter: (to, from, next) => {
      if (store.state.user.status) next()
      else next("/")
    },
    children: [
      {
        path: '/create',
        component: () => import('@/components/Editor.vue')
      },
      {
        path: '',
        props: true,
        component: FanficGrid
      },
    ]

  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: () => import('@/views/SignUp.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
