<template>
  <div style="width: 80%;text-align: center">
    <div>
      <img src="@/assets/addAdmin.png" alt="" style="width: 65px;position: relative;top: 12px">
      <h2 style="margin-bottom: 30px">新增管理员</h2>
    </div>

    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
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

    <div style="margin-top: 30px;text-align: center">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'AddAdmin',
  data() {
    //校验phone
    const checkPhone = (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error('请输入合法的手机号'));
      }
      callback()
    };
    return{
      form:{},           //form上面进行了数据绑定,会把输入的值放入此对象中
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},   //required:必填项,trigger:blur(失焦时触发)
          {min: 3, max:10, message: '长度在3-10个字符',trigger: 'blur'}
        ],
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    save(){
      this.$refs['ruleForm'].validate((valid) => {    //根据ref找到对应的表单,调用validate方法
        if(valid){
          request.post('/admin/save',this.form).then(res => {
            if(res.code === '200'){
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields();
            }else{
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>
