<template>
  <div style="width: 80%;text-align: center">
    <div>
      <img src="@/assets/categoryLogo.png" alt="" style="width: 65px;position: relative;top: 12px">
      <h2 style="margin-bottom: 30px">新增分类</h2>
    </div>

    <el-form :inline="true" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
      <el-form-item label="名称 :" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="备注 :" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
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
  name: 'AddCategory',
  data() {
    return{
      form:{},           //form上面进行了数据绑定,会把输入的值放入此对象中
      rules: {
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},   //required:必填项,trigger:blur(失焦时触发)
        ],
      },
    }
  },
  methods:{
    save(){
      this.$refs['ruleForm'].validate((valid) => {    //根据ref找到对应的表单,调用validate方法
        if(valid){
          request.post('/category/save',this.form).then(res => {
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
