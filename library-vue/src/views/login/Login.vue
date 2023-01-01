<template>
  <div style="height: 100vh;overflow: hidden">
    <!--    margin:150px auto上下间隔150px,左右居中-->
    <div style="width: 500px;height: 400px;background-color: white;border-radius: 20px;
    margin: 150px auto;padding:50px">
      <div style="margin: 30px;text-align: center;font-size: 30px;font-weight: bold;color: deepskyblue">图书借阅管理系统</div>

      <el-form :model="admin" ref="loginForm" :rules="rules">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="admin.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock" size="medium" v-model="admin.password"></el-input>
        </el-form-item>

        <el-form-item style="text-align: center;margin-top: 45px">
          <el-button type="primary" style="width: 100%;font-size: medium" @click="login">登 录</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name:'Login',
  data(){
    return{
      admin:{}, //我们登录的对象
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},   //required:必填项,trigger:blur(失焦时触发)
          {min: 3, max:10, message: '长度在3-10个字符',trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max:10, message: '长度在3-10个字符',trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
    login(){
      this.$refs['loginForm'].validate((valid) => {  //根据ref找到对应的表单,调用validate方法
        if(valid){
          request.post('/admin/login',this.admin).then(res => {
            if(res.code == '200'){
              this.$notify.success("登录成功")
              this.$router.push('/')
            }else{
              this.$notify.error(res.msg)
              this.admin = admin
            }
          })
        }
      })
    }
  }
}
</script>
