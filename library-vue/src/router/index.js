import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/Layout.vue'
import Cookies from "js-cookie";

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

    //若上面的路由都没有时,则跳转404
  {
    path: "*",
    component: () => import('@/views/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,form,next) => {
  //如果将要到login路由直接放行
  if(to.path === 'login') next()
  //放行后获取到admin的数据
  const admin = Cookies.get('admin')
  //若没有admin的数据并且跳转路径不是login,则强行返回到login
  if(!admin && to.path !== '/login') return next("login")
  //若访问 /home 的时候,并且cookie有缓存数据,则直接放行
  next()
})

export default router
