<template>
  <div>
    <!-- 头部区域 -->
    <div style="height: 60px;line-height: 60px ; background: white;margin-bottom: 2px;display: flex">
      <div style="width: 300px">
        <img src="@/assets/logo.png" alt="" style="width: 40px;position: relative;top: 10px;left:20px">
        <span style="margin-left:25px;font-size:24px">图书管理系统</span>
      </div>

      <!--flex自动分割剩下的-->
      <div style="flex: 1;text-align: right;padding-right: 30px">
        <el-dropdown size="medium" style="cursor: pointer">
          <span class="el-dropdown-link">
            {{ admin.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
            <el-dropdown-item>
              <div style="width: 50px;text-align: center;" @click="logout">退 出</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

    </div>

    <!-- 侧边栏和主体 -->
    <div style="display: flex" background-color="#545c64">
      <!-- 侧边栏(导航栏) -->
      <div style="width:200px;min-height:calc(100vh - 62px);overflow: hidden;margin-right:2px;background-color: #545c64">       <!-- 给宽200，超出隐藏 -->
        <el-menu :default-active="$router.path" router class="el-menu-demo"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 style="overflow: hidden"
        >
          <!-- []设置默认打开的菜单 -->
          <el-menu-item index="/">                   <!-- index里的path -->
            <i class="el-icon-s-home"></i>
            <span>首页</span>
          </el-menu-item>

          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="addUser">用户添加</el-menu-item>
            <el-menu-item index="userList">用户列表</el-menu-item>
          </el-submenu>

          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="addAdmin">管理员添加</el-menu-item>
            <el-menu-item index="adminList">管理员列表</el-menu-item>
          </el-submenu>

        </el-menu>
      </div>

      <!-- 主体数据 -->
      <div style="flex:1;background-color: white;padding: 10px"> <!-- 剩下的 -->
        <!-- 展示子路由-->
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  name: "Layout",
  data(){
    return{
      //数据可能没有,因此用三元判断一下(有就转化为JSON对象,没有为空)
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  methods:{
    logout(){
      //清除浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>
