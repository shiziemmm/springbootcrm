<template>
     <div>
      <!-- <div class="lable-titi">
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
          <el-button icon="el-icon-circle-plus" @click="handAdd()" size="small">新建</el-button>
      </div>
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="50" label="序号"></el-table-column>
          <el-table-column prop="waName" label="仓库名称"  sortable></el-table-column>
          <el-table-column prop="waLocation" label="仓库位置"  sortable></el-table-column>
          <el-table-column prop="waDescribes" label="用途描述"  sortable></el-table-column>
          <el-table-column fixed="right" label="操作" align="center" width="100">
            <template #default="scope">
               <el-button @click="handUpdate(scope.row.waId)" size="small" type="text">查看</el-button>
               <el-button @click="handDelete(scope.row.waId)" size="small" type="text">删除</el-button>
            </template>
          </el-table-column>
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
    <el-dialog title="仓库信息" @close="closeck()" :close-on-click-modal="false" :close-on-press-escape="false" v-model="dialogFormVisible" width="600px">
    <el-divider></el-divider>
    <el-row :gutter="15">
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
       <el-col :span="24">
            <el-form-item label="仓库名称" prop="waName">
              <el-input v-model="formData.waName"  show-word-limit
                        clearable :style="{width: '400px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="24">
            <el-form-item label="仓库位置" prop="waLocation">
              <el-input v-model="formData.waLocation" show-word-limit
                        clearable :style="{width: '400px'}"></el-input>
            </el-form-item>
       </el-col>
          <el-col :span="24">
            <el-form-item label="用途描述" prop="waDescribes">
              <el-input type="textarea" :style="{width: '400px'}"  placeholder="请输入内容" v-model="formData.waDescribes">
              </el-input>
            </el-form-item>  
          </el-col>            
        <el-col :span="6" :offset="20">
        <el-button type="primary" plain size="medium" @click="save()">保存</el-button>
        </el-col>
    </el-form>        
    </el-row>  
    </el-dialog> -->
  </div>

</template>

<script>
export default {
    // data(){
    //     return{
    //         tableData:[],
    //         selectParams:{},
    //         dialogFormVisible:true,
    //         formData:{},
    //         options:[],
    //     }
    // },
    // methods: {
    //   init(){
    //       this.axios.post('http://localhost:8188/product/findAll',this.selectParams).then(res=>{
    //             this.tableData=res.data.data.records
    //       });
    //        this.axios.get('http://localhost:8188/product-class/findAll').then(res=>{
    //             this.options=res.data.data
                
    //         })
    //   },
    //   checkChange(keyword){
    //     this.selectParams.keyword=keyword;
    //     this.init()
    //   },
    //   onPageSizeChange(pageSize){
    //     this.selectParams.pageSize=pageSize;
    //     this.init()
    //   },
    //   onPageChange(page){
    //     this.selectParams.pageNum=page
    //     this.init()
    //   },
    //     // 新建产品
    //     handAdd(){
    //       this.dialogFormVisible=true;
    //     },
    //     // 查看产品
    //     handUpdate(id){
    //       this.axios.get('http://localhost:8188/product/find/'+id).then(res=>{
    //             this.formData=res.data.obj
    //             this.dialogFormVisible=true;
    //         })
    //     },
    //     //删除产品
    //     handDelete(id){
    //       this.axios.delete('http://localhost:8188/product/delete/'+id).then(res=>{
    //             this.$message({
    //                 message: res.data.obj?'成功':'失败',
    //                 type: res.data.obj?'success':'error'
    //            });
    //            this.init();
    //         })
    //     },
    //     //保存
    //     save(){
    //       if(this.formData.prId == null){
    //         this.axios.post('http://localhost:8188/product/add',this.formData).then(res=>{
    //             this.$message({
    //                 message: res.data.obj?'成功':'失败',
    //                 type: res.data.obj?'success':'error'
    //            });
    //         })
    //       }else{
    //         this.axios.put('http://localhost:8188/product/update/'+this.formData.prId,this.formData).then(res=>{
    //             this.$message({
    //                 message: res.data.obj?'成功':'失败',
    //                 type: res.data.obj?'success':'error'
    //            });
    //         })
    //       }
    //          this.dialogFormVisible=false;
    //          this.init();  
    //     },
    //     closeck(){
    //       this.formData = this.$options.data.call(this).formData
    //     },
    // },
    // created(){
    //   this.selectParams.pageSize = 10
    //   this.selectParams.pageNum = 1
    //   this.init()
    // }
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
