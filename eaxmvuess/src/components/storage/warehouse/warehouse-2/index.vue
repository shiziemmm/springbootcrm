<template>
     <div>
      <div class="lable-titi">
            <el-input
                placeholder="搜索一下"
                suffix-icon="el-icon-search"
                v-model="input"
                style="width:200px;top: 10px;left: 20px"
                @change="checkChange"
              ></el-input>
      </div>
    <div class="app-container">
      <div class="app-button">
          <el-button icon="el-icon-circle-plus" @click="handDelete()" size="small">数据整理</el-button>
      </div>
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="100" label="序号"></el-table-column>
          <el-table-column prop="prname" label="产品名称"  sortable></el-table-column>
          <el-table-column prop="wicount" label="数量"  sortable></el-table-column>
          <el-table-column prop="waname" label="仓库"  sortable></el-table-column>
        </el-table>
        <div class="page-list">
          <el-pagination
            @size-change="onPageSizeChange"
            @current-change="onPageChange"
            background
            :total="Number(total)"
            :page-sizes="[10,20,30]"
            layout="sizes, prev, pager, next, jumper"
          ></el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            input:'',
            tableData:[],
            selectParams:{},
        }
    },
    methods: {
      init(){
          this.axios.post('http://localhost:8188/warehouse-inventory/findAll',this.selectParams).then(res=>{
                this.tableData=res.data.data.records
          });
      },
      checkChange(keyword){
        this.selectParams.keyword=keyword;
        this.init()
      },
      onPageSizeChange(pageSize){
        this.selectParams.pageSize=pageSize;
        this.init()
      },
      onPageChange(page){
        this.selectParams.pageNum=page
        this.init()
      },
        //删除产品
        handDelete(){
          this.axios.delete('http://localhost:8188/product/delete/').then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
        },
    },
    created(){
      this.selectParams.pageSize = 10
      this.selectParams.pageNum = 1
      this.init()
    }
}
</script>


<style>
.cont-bod-box {
  padding: 20px;
  background: #fff;
  border-radius: 3px;
  margin-top: 15px;
  margin-bottom: 15px;
  border: 1px solid #ebeef5;
}
.page-list {
  text-align: center;
  margin-top: 10px;
}
.app-button{
  position: absolute;
  right: 20px;
  top: 50px;
}
.dashboard-container{
  background: #efefef;
}
</style>
