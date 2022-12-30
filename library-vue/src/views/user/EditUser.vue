<template>
<div style="width: 80%">
<div style="text-align: center">
  <img src="@/assets/editUser.png" alt="" style="width: 65px;position: relative;top: 12px">
  <h2 style="margin-bottom: 30px">编辑用户</h2>
</div>
<el-form :inline="true" :model="form" label-width="100px">
  <el-form-item label="姓名 :">
    <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
  </el-form-item>
  <el-form-item label="学号 :">
    <el-input v-model="form.uuid" placeholder="请输入姓名"></el-input>
  </el-form-item>
  <el-form-item label="年龄 :">
    <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
  </el-form-item>
  <el-form-item label="联系方式 :">
    <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
  </el-form-item>
  <el-form-item label="地址 :">
    <el-input v-model="form.address" placeholder="请输入地址"></el-input>
  </el-form-item>
  <el-form-item label="性别 :">
    <el-radio v-model="form.sex" label="男">男</el-radio>
    <el-radio v-model="form.sex" label="女">女</el-radio>
  </el-form-item>
</el-form>

<div style="margin-top: 30px;text-align: center">
  <el-button type="primary" @click="save" size="medium">提交</el-button>
</div>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'EditUser',
  data() {
    return{
      form:{}           //form上面进行了数据绑定,会把输入的值放入此对象中
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/user/" + id).then(res => {
      this.form = res.data
    })
  },
  methods:{
    save(){
      request.put('/user/update',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/user")
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
