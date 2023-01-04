<template>
<div style="width: 80%;text-align: center">
<div>
  <img src="@/assets/editAdmin.png" alt="" style="width: 65px;position: relative;top: 12px">
  <h2 style="margin-bottom: 30px">编辑管理员</h2>
</div>
<el-form :inline="true" :model="form" label-width="100px">
  <el-form-item label="用户名 :" prop="username">
    <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
  </el-form-item>
  <el-form-item label="联系方式 :" prop="phone">
    <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
  </el-form-item>
  <el-form-item label="邮箱 :" prop="email">
    <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
  </el-form-item>
</el-form>

<div style="margin-top: 30px">
  <el-button type="primary" @click="save" size="medium">提交</el-button>
</div>

</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'EditAdmin',
  data() {
    return{
      form:{}           //form上面进行了数据绑定,会把输入的值放入此对象中
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/admin/" + id).then(res => {
      this.form = res.data
    })
  },
  methods:{
    save(){
      request.put('/admin/update',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/adminList")
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
