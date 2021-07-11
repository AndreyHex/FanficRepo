import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'

import Home from '@/views/Home.vue'
import MyFanfics from "@/views/MyFanfics";
import FanficGrid from "@/components/FanficGrid";
import Fanfic from "@/components/Fanfic";
import ChapterEditor from "@/components/ChapterEditor";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '',
        component: FanficGrid
      },
      {
        path: 'fanfic/:id',
        component: Fanfic
      }
    ]
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
        path: '',
        props: true,
        component: FanficGrid
      },
      {
        path: 'create',
        component: () => import('@/components/Editor.vue')
      },
      {
        path: ':id/details',
        component: ChapterEditor
      }
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
