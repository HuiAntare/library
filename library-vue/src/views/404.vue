<template>
  <div style="text-align: center">
    <div style="margin-top: 150px;font-size: 100px">404</div>
    <div style="font-size: 40px">未找到页面</div>
    <div>
      <!--若有浏览器缓存就回主页,否则回登录界面-->
      <el-button type="text" style="margin-top: 20px;font-size: 20px" @click="$router.push('/')">返回主页</el-button>
    </div>
  </div>
</template>
