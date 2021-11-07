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
          <el-button icon="el-icon-circle-plus" @click="handAdd()" size="small">新建</el-button>
      </div>
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="50" label="序号"></el-table-column>
          <el-table-column prop="prName" label="产品名称"  sortable></el-table-column>
          <el-table-column prop="prCoding" label="编码" width="200px"  sortable></el-table-column>
          <el-table-column prop="prModel" label="型号"  sortable></el-table-column>
          <el-table-column prop="prPrice" label="价格"  sortable></el-table-column>
          <el-table-column prop="prUnit" label="单位"  sortable></el-table-column>
          <el-table-column prop="prState" label="状态"  sortable>
            <template #default="scope">
               <el-tag :type="scope.row.prState == 0? 'success':'danger'">{{scope.row.prState == 0? '正常':'停售'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="prCostPrice" label="成本价格" sortable></el-table-column>
          <el-table-column fixed="right" label="操作" align="center" width="100">
            <template #default="scope">
               <el-button @click="handUpdate(scope.row.prId)" size="small" type="text">查看</el-button>
               <el-button @click="handDelete(scope.row.prId)" size="small" type="text">删除</el-button>
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
    <el-dialog title="产品信息" @close="closeck()" :close-on-click-modal="false" :close-on-press-escape="false" v-model="dialogFormVisible" width="900px">
    <el-divider></el-divider>
    <el-row :gutter="15">
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
       <el-col :span="12">
            <el-form-item label="产品名字" prop="prName">
              <el-input v-model="formData.prName"  show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="型号" prop="prModel">
              <el-input v-model="formData.prModel" show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="编码" prop="prCoding">
              <el-input v-model="formData.prCoding" show-word-limit
                        clearable placeholder="自动生成" :disabled="true" :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="分类" prop="prPcId">
            <el-select v-model="formData.prPcId" filterable :style="{width: '260px'}" placeholder="请选择">
             <el-option v-for="item in options" :key="item.pcId" :label="item.pcName" :value="item.pcId"></el-option>
            </el-select>
            </el-form-item>
        </el-col>
       <el-col :span="12">
            <el-form-item label="价格" prop="prPrice">
              <el-input v-model="formData.prPrice" show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="成本价格" prop="prCostPrice">
              <el-input v-model="formData.prCostPrice" show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量(kg)" prop="prWeight">
              <el-input v-model="formData.prWeight"
                         :style="{width: '260px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="prState">
               <el-radio-group v-model="formData.prState">
                  <el-radio :label= 0 >正常</el-radio>
                  <el-radio :label= 1 >停售</el-radio>
                </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位" prop="prUnit">
              <el-input v-model="formData.prUnit" show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="明细概要" prop="prOutline">
              <el-input type="textarea" :style="{width: '600px'}" placeholder="请输入内容" v-model="formData.prOutline">
              </el-input>
            </el-form-item>  
          </el-col>
          <el-col :span="24">
            <el-form-item label="产品说明" prop="prDescription">
              <el-input type="textarea" :style="{width: '600px'}"  placeholder="请输入内容" v-model="formData.prDescription">
              </el-input>
            </el-form-item>  
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="prRemark">
              <el-input type="textarea" :style="{width: '600px'}"  placeholder="请输入内容" v-model="formData.prRemark">
              </el-input>
            </el-form-item>  
          </el-col>            
        <el-col :span="6" :offset="21">
        <el-button type="primary" plain size="medium" @click="save()">保存</el-button>
        </el-col>
    </el-form>        
    </el-row>  
    </el-dialog>
  </div>

</template>

<script>
export default {
    data(){
        return{
            input:'',
            tableData:[],
            selectParams:{},
            dialogFormVisible:false,
            formData:{
              prState:0
            },
            options:[],
        }
    },
    methods: {
      init(){
          this.axios.post('http://localhost:8188/product/findAll',this.selectParams).then(res=>{
                this.tableData=res.data.data.records
          });
           this.axios.get('http://localhost:8188/product-class/findAll').then(res=>{
                this.options=res.data.data
                
            })
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
        // 新建产品
        handAdd(){
          this.dialogFormVisible=true;
        },
        // 查看产品
        handUpdate(id){
          this.axios.get('http://localhost:8188/product/find/'+id).then(res=>{
                this.formData=res.data.obj
                this.dialogFormVisible=true;
            })
        },
        //删除产品
        handDelete(id){
          this.axios.delete('http://localhost:8188/product/delete/'+id).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
        },
        //保存
        save(){
          if(this.formData.prId == null){
            this.axios.post('http://localhost:8188/product/add',this.formData).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
            })
          }else{
            this.axios.put('http://localhost:8188/product/update/'+this.formData.prId,this.formData).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
            })
          }
             this.dialogFormVisible=false;
             this.init();  
        },
        closeck(){
          this.formData = this.$options.data.call(this).formData
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
