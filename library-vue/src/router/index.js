import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'

Vue.use(VueRouter)

const routes = [
  //  ====== Login  =====
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/Login.vue')
  },
  //  ====== 主页  =====
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/home',   //当为'/'时重定向路由为'/home'
    children:[
        //子路由:/路由名
      //  ====== 首页  =====
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/home/HomeView.vue')
      },
      //  ====== User  =====
      {
        path: 'userList',
        name: 'UserList',
        component: () => import('@/views/user/User.vue')
      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import('@/views/user/AddUser.vue')
      },
      {
        path: 'editUser',
        name: 'EditUser',
        component: () => import('@/views/user/EditUser.vue')
      },
      //  ====== Admin  =====
      {
        path: 'adminList',
        name: 'AdminList',
        component: () => import('@/views/admin/Admin.vue')
      },
      {
        path: 'addAdmin',
        name: 'AddAdmin',
        component: () => import('@/views/admin/Add.vue')
      },
      {
        path: 'editAdmin',
        name: 'EditAdmin',
        component: () => import('@/views/admin/Edit.vue')
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
