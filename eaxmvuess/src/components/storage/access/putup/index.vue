
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
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="50" label="序号"></el-table-column>
          <el-table-column prop="eTheme" label="主题"  sortable></el-table-column>
          <el-table-column prop="tName" label="仓库" sortable></el-table-column>
          <el-table-column prop="lName" label="客户" sortable></el-table-column>
          <el-table-column prop="lOn" label="对应单号"  sortable></el-table-column>
          <el-table-column prop="eState" label="状态"  sortable>
            <template #default="scope">
               <el-tag :type="scope.row.eState == 0? '':'success'">{{scope.row.eState == 0? '未出库':'已出库'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="eExecute" label="执行人"  sortable></el-table-column>
          <el-table-column prop="eLeavetime" width="180px" label="出库日期" sortable>
            <template #default="scope">
              {{scope.row.eLeavetime == null ? '' : formatDate(scope.row.eLeavetime,'yyyy-MM-dd hh:mm:ss')}}
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" align="center" width="100">
            <template #default="scope">
               <el-button @click="handFind(scope.row.eId)" size="small" type="text">查看</el-button>
               <el-button @click="handDelete(scope.row.eId)" size="small" type="text">删除</el-button>
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
    <el-dialog title="出库单" @close="closeck()" :close-on-click-modal="false" :close-on-press-escape="false" v-model="dialogFormVisible" width="800px">
    <el-divider></el-divider>
    <el-row :gutter="15">
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
       <el-col :span="12">
            <el-form-item label="标题" prop="wlTheme">
              <el-input v-model="formData.wlTheme"  show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="状态" prop="wlState">
               <el-radio-group v-model="formData.wlState" :disabled="true">
                  <el-radio :label= 0 >未出库</el-radio>
                  <el-radio :label= 1 >已出库</el-radio>
                </el-radio-group>
            </el-form-item>
          </el-col>
       <el-col :span="12">
            <el-form-item label="对应订单号" prop="lOn">
              <el-input v-model="formData.odrOn" :disabled="true" show-word-limit
                        clearable :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="仓库" prop="prPrice">
              <el-input v-model="tes" show-word-limit :disabled="true"
                        clearable :style="{width: '220px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="执行日期" prop="prCoding">
              <el-input show-word-limit v-model="formData.wlLeavetime"
                        clearable placeholder="当前时间" :disabled="true" :style="{width: '260px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="执行人" prop="prCoding">
              <el-input show-word-limit v-model="formData.wlExecute"
                        clearable  :style="{width: '220px'}"></el-input>
            </el-form-item>
       </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="prRemark">
              <el-input type="textarea" :style="{width: '600px'}"  placeholder="请输入内容" v-model="formData.wlRemark">
              </el-input>
            </el-form-item>  
          </el-col>
      <el-table :data="formData.warehouseLeaveGoods" style="width: 600px;margin-left: 80px">
        <el-table-column prop="wlgPrname" label="产品名称"> </el-table-column>
        <el-table-column prop="product.prModel" label="型号"> </el-table-column>
        <el-table-column prop="product.prUnit" label="单位"> </el-table-column>
        <el-table-column prop="wlgCount" label="出库量"> </el-table-column>
      </el-table>            
        <el-col :span="6" :offset="20">
        <el-button type="primary" v-show="showOrHide" plain size="medium" @click="update()">出库</el-button>
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
            formData:{},
            tes:'全国仓库',
            options:[],
            showOrHide:true,
            token:{},
        }
    },
    methods: {
      init(){
          this.axios.post('http://localhost:8188/warehouse-leave/findAll',this.selectParams).then(res=>{
                this.tableData=res.data.data.records
          });
          this.token=JSON.parse(localStorage.getItem("loginuser"));
      },
       formatDate (thistime, fmt) {
      let $this = new Date(thistime)
      let o = {
        'M+': $this.getMonth() + 1,
        'd+': $this.getDate(),
        'h+': $this.getHours(),
        'm+': $this.getMinutes(),
        's+': $this.getSeconds(),
        'q+': Math.floor(($this.getMonth() + 3) / 3),
        'S': $this.getMilliseconds()
      }
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ($this.getFullYear() + '').substr(4 - RegExp.$1.length))
      }
      for (var k in o) {
        if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
      return fmt;
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
        // 查看出库单
        handFind(id){
          this.axios.get('http://localhost:8188/warehouse-leave/find/'+id).then(res=>{
                this.formData=res.data.data
                if(this.formatDate.wlExecute== null){
                  this.formData.wlExecute=this.token.empName
                }
                if(this.formData.wlState ==1){
                  this.showOrHide=false;
                }else{
                  this.showOrHide=true;
                }                
            })
        this.dialogFormVisible=true;
        },
        //删除出库单
        handDelete(id){
           this.$confirm('此操作将永久删除该出库单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
        .then(()=>{
          this.axios.delete('http://localhost:8188/warehouse-leave/delete/'+id).then(res=>{
                this.$message({
                    message: res.data.message,
                    type: res.data.success?'success':'error'
               });
               this.init();
            })
            })
        .catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除',
            })
          })
        },
        //出库
        update(){
            this.axios.put('http://localhost:8188/warehouse-leave/update',this.formData).then(res=>{
                this.$message({
                    message: res.data.message,
                    type: res.data.success?'success':'error'
               });
            })
             this.dialogFormVisible=false;
        },
        closeck(){
          this.formData = this.$options.data.call(this).formData
          this.init();  
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

