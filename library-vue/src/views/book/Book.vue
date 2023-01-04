<template>
  <div>
    <!-- 搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width:240px" placeholder="请输入图书名称" v-model="params.name"></el-input>
      <el-input style="width:240px;margin-left: 5px" placeholder="请输入图书标准码" v-model="params.bookNo"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search">搜索</i></el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh">重置</i></el-button>
    </div>


    <!-- 表单头部-->
    <el-table :data="tableData" stripe row-key="id" default-expand-all>     <!-- prop写属性，label写名字-->
      <el-table-column prop="id" label="编号" width="80px"></el-table-column>
      <el-table-column prop="name" label="图书名称"></el-table-column>
      <el-table-column prop="bookNo" label="标准码"></el-table-column>
      <el-table-column prop="description" width="200" label="描述"></el-table-column>
      <el-table-column prop="publishDate" label="出版日期"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column prop="category" label="分类"></el-table-column>
      <el-table-column prop="score" label="借书积分"></el-table-column>
      <el-table-column prop="nums" label="数量"></el-table-column>
      <el-table-column prop="cover" label="封面">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>

      <el-table-column label="操作" width="150">
        <template v-slot="scope">
          <!--scope.row 就是当前的行数据-->
          <el-button type="primary" @click="$router.push('/editBook?id=' + scope.row.id)">编辑</el-button>
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
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'BookList',
  data(){
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData:[],
      total: 0,
      params:{               //创建一个params对象封装属性
        pageNum:1,
        pageSize:10,
        name:'',
        bookNo: ''
      },
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/book/page',{
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
        bookNo:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/book/delete/" + id).then(res =>{
        if(res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>

