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
          <el-button  @click="handAdd()" size="small">新建</el-button>
      </div>
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="50" label="序号"></el-table-column>
          <el-table-column prop="weTheme" label="主题"  sortable></el-table-column>
          <el-table-column prop="weEntertime" label="入库日期"  sortable></el-table-column>
          <el-table-column prop="weName" label="填单人"  sortable></el-table-column>
          <el-table-column prop="weState" label="状态"  sortable>
            <template #default="scope">
               <el-tag :type="scope.row.weState == 0? 'success':'danger'">{{scope.row.weState == 0? '未入库':'已入库'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" align="center" width="150">
            <template #default="scope">
              <el-button @click="enter(scope.row)" v-show="scope.row.weState == 0? true:false" size="small" type="text">入库</el-button>
               <el-button @click="handUpdate(scope.row.weId)" size="small" type="text">查看</el-button>
               <el-button @click="handDelete(scope.row.weId)" size="small" type="text">删除</el-button>
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
    <el-dialog title="入库单" @close="closeck()" :close-on-click-modal="false" :close-on-press-escape="false" v-model="dialogFormVisible" width="600px">
    <el-divider></el-divider>
    <el-row :gutter="15">
    <el-form ref="elForm" :model="formData" size="medium" label-width="80px">
       <el-col :span="24">
            <el-form-item label="主题" prop="weTheme">
              <el-input v-model="formData.weTheme"  show-word-limit
                        clearable :style="{width: '450px'}"></el-input>
            </el-form-item>
       </el-col>
        <el-col :span="12">
            <el-form-item label="仓库">
            <el-select v-model="formData.weWaId" filterable :style="{width: '100%'}" placeholder="请选择">
             <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
            </el-form-item>
        </el-col>
       <el-col :span="12">
            <el-form-item label="状态" prop="weState">
               <el-radio-group v-model="formData.weState" :disabled="true">
                  <el-radio :label= 0 >未入库</el-radio>
                  <el-radio :label= 1 >已入库</el-radio>
                </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="填单人" prop="weName">
            <el-select v-model="formData.weName" filterable :style="{width: '150px'}" placeholder="请选择">
             <el-option v-for="item in options2" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
            </el-form-item>
        </el-col>
       <el-col :span="12">
            <el-form-item label="入库时间" prop="weEntertime">
              <el-input v-model="formData.weEntertime" :disabled="true" show-word-limit
                        clearable :style="{width: '100%'}"></el-input>
            </el-form-item>
       </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="weRemark">
              <el-input type="textarea" :style="{width: '450px'}"  placeholder="请输入内容" v-model="formData.weRemark">
              </el-input>
            </el-form-item>  
          </el-col>
          <el-table  ref="table" border :data="formData.enterGoods">
        <el-table-column label="产品名称">
          <template slot-scope="scope">
            <el-form-item>
             <el-select v-model="scope.row.wegPrId" @change="selectck(scope.$index)" filterable placeholder="品名型号">
              <el-option v-for="item in prData" :key="item.prId" :label="item.prName+'/'+item.prModel" :value="item.prId"></el-option>
             </el-select>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column label="数量">
          <template slot-scope="scope">
            <el-form-item >
              <el-input v-model="scope.row.wegCount"  placeholder="数量" ></el-input>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column label="备注">
          <template slot-scope="scope">
            <el-form-item >
              <el-input v-model="scope.row.wegRemark" :style="{height: '50px'}" placeholder="备注" type="textarea"></el-input>
            </el-form-item>
          </template>
        </el-table-column>
        <el-table-column  label="操作">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="del(scope.$index)" icon='el-icon-delete'></el-button>
            <el-button type="primary" size="mini" @click="add()" icon='el-icon-plus'></el-button>
          </template>
        </el-table-column>
          </el-table>            
        <el-col :span="6" :offset="18">
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
            prData:[],
            selectParams:{},
            dialogFormVisible:false,
            formData:{
              weState:0,
              enterGoods:[]
            },
            options:[
              {id:1,name:'全国仓库'}
            ],
            options2:[
              {id:1,name:'admin'}
            ]
        }
    },
    methods: {
      init(){
          this.axios.post('http://localhost:8188/warehouse-enter/findAll',this.selectParams).then(res=>{
                this.tableData=res.data.data.records
          });
          this.axios.get('http://localhost:8188/product/finds').then(res=>{
                this.prData=res.data.data
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
        // 新建入库单
        handAdd(){
          this.dialogFormVisible=true;
        },
        // 查看产品
        handUpdate(id){
          this.axios.get('http://localhost:8188/warehouse-enter/find/'+id).then(res=>{
                this.formData=res.data.obj
                this.dialogFormVisible=true;
            })
        },
        //删除产品
        handDelete(id){
          this.axios.delete('http://localhost:8188/warehouse-enter/delete/'+id).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
        },
        enter(row){
          this.axios.put('http://localhost:8188/warehouse-enter/updateState',row).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
        },
        //保存
        save(){
          if(this.formData.WeId == null){
            this.axios.post('http://localhost:8188/warehouse-enter/add',this.formData).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
          }else{
            this.axios.put('http://localhost:8188/warehouse-enter/update/',this.formData).then(res=>{
                this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.init();
            })
          }
             this.dialogFormVisible=false;  
        },
        add(){
        this.formData.tableData.push({
          
        })
      },
      del(index){
        this.formData.enterGoods.splice(index,1);
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

