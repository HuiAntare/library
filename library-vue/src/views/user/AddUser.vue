<template>
  <div style="width: 80%">
    <div style="text-align: center">
      <img src="@/assets/addUser.png" alt="" style="width: 65px;position: relative;top: 12px">
      <h2 style="margin-bottom: 30px">新增用户</h2>
    </div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="姓名 :" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="学号 :" prop="uuid">
        <el-input v-model="form.uuid" placeholder="请输入学号"></el-input>
      </el-form-item>
      <el-form-item label="年龄 :" prop="age">
        <el-input v-model.number="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="联系方式 :" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址 :" prop="address">
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
  name: 'AddUser',
  data() {
    //年龄校验
    const checkAge = (rule, value, callback) => {
      if (!value && value != 0) {
        return callback(new Error('年龄不能为空'));
      }
      if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'));
      }
      if(value <= 0 || value >= 120){
        callback(new Error('请输入合法年龄'));
      }
      callback()
    };
    //校验uuid
    const checkUuid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('学号不能为空'));
      }
      if (!/^[1-9][0-9]{5}[(A-Z)|(0-9)][0-9]{3}$/.test(value)) {
        callback(new Error('请输入数字值'));
      }
      if(value.length != 10){
        callback(new Error('请输入合法学号'));
      }
      callback()
    };
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
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},   //required:必填项,trigger:blur(失焦时触发)
        ],
        uuid: [
          { validator: checkUuid, trigger: 'blur' }
        ],
        age: [
          { validator: checkAge, trigger: 'blur' }
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
          request.post('/user/save',this.form).then(res => {
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
