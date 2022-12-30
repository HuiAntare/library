<template>
  <div id="app">
     <!-- 头部区域 -->
    <div style="height: 60px;line-height: 60px ; background: white;margin-bottom: 2px">
      <img src="@/assets/logo.png" alt="" style="width: 40px;position: relative;top: 12px;left:15px">
      <span style="margin-left:25px;font-size:24px">图书管理系统</span>
    </div>

    <!-- 侧边栏和主体 -->
    <div style="display: flex"
         background-color="#545c64"
         text-color="#fff"
         active-text-color="#ffd04b">
      <!-- 侧边栏(导航栏) -->
      <div style="width:200px;min-height:calc(100vh - 62px);overflow: hidden;margin-right:2px;background-color: #545c64">       <!-- 给宽200，超出隐藏 -->
        <el-menu :default-active="$route.path === '/'? $route.path : $route.path.substring(1)" :default-openeds="['/']" router class="el-menu-demo"
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
          <el-submenu index="/">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="addUser">用户添加</el-menu-item>
            <el-menu-item index="user">用户列表</el-menu-item>
          </el-submenu>

          <el-submenu index="/">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="addAdmin">管理员添加</el-menu-item>
            <el-menu-item index="admin">管理员列表</el-menu-item>
          </el-submenu>

        </el-menu>
      </div>

      <!-- 主体数据 -->
      <div style="flex:1;background-color: white;padding: 10px">              <!-- 剩下的 -->
        <router-view/>
      </div>
    </div>
  </div>
</template>
