<template>
  <div>
    <!-- 搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width:240px" placeholder="请输入姓名" v-model="params.name"></el-input>
      <el-input style="width:240px;margin-left: 5px" placeholder="请输入学号" v-model="params.uuid"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search">搜索</i></el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh">重置</i></el-button>
    </div>


    <!-- 表单头部-->
    <el-table :data="tableData" stripe>     <!-- prop写属性，label写名字-->
      <el-table-column prop="id" label="编号" width="80px"></el-table-column>
      <el-table-column prop="uuid" label="学号"></el-table-column>
      <el-table-column prop="name" label="名称" width="80px"></el-table-column>
      <el-table-column prop="age" label="年龄" width="80px"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>
      <el-table-column prop="sex" label="性别" width="80px"></el-table-column>
      <el-table-column prop="account" label="账户积分"></el-table-column>
      <el-table-column label="状态" width="100">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.status"
              @change="changeStatus(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>

      <el-table-column label="操作" width="240">
        <template v-slot="scope">
<!--       scrop.row 就是当前的行数据-->
          <el-button type="warning" @click="handleAccountAdd(scope.row)">充值</el-button>
          <el-button type="primary" @click="$router.push('/editUser?id=' + scope.row.id)">编辑</el-button>

          <el-popconfirm
              style="margin-left: 3px"
              title="您确定要删除这行数据吗？"
              @confirm="del(scope.row.id)"
              confirm-button-type="primary"
              cancel-button-type="warning"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>


    <!-- 分页-->
    <div style="margin-top: 20px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">               <!--page-size每页个数 -->
      </el-pagination>
    </div>

    <el-dialog title="充值" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px" ref="ruleForm" :rules="rules" style="width: 85%">
        <el-form-item label="当前账户积分" prop="account">
          <el-input disabled v-model="form.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="积分" prop="score">
          <el-input v-model="form.score" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAccount">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'User',
  data(){
    const checkNums = (rule, value, callback) => {
      value = parseInt(value)
      if (value < 10 || value > 200) {
        callback(new Error('请输入大于等于10小于或等于200的整数'));
      }
      callback()
    };
    return {
      tableData:[],
      total: 0,
      dialogFormVisible: false,
      form:{},
      params:{               //创建一个params对象封装属性
        pageNum:1,
        pageSize:10,
        name:'',
        phone:''
      },
      rules: {
        score: [
          { required: true, message: '请输入积分', trigger: 'blur'},
          { validator: checkNums, trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    changeStatus(row) {
      request.put('/user/update', row).then(res => {
        if (res.code === '200') {
          this.$notify.success('操作成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    load(){
      request.get('/user/page',{
        params:this.params                    //用装好的对象传入params,就可以向后台发送数据
      }).then(res => {
        if(res.code === '200'){
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset(){
      this.params = {
        pageNum:1,
        pageSize:10,
        name:'',
        phone:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/user/delete/" + id).then(res =>{
        if(res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    handleAccountAdd(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    addAccount() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/user/account', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('充值成功')
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

