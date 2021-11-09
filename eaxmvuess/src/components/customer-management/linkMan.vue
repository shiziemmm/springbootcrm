<template>


  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">联系人管理</span></el-col>
  </el-row>

  <div>
    <el-card>
    <el-row>
      <el-col>
        <i class="el-icon-guide" style="font-size: 20px;"></i>
        <el-select v-model="value" placeholder="请选择">
          <el-option label="全部数据" value=""></el-option>
          <el-option-group
              v-for="group in options"
              :key="group.label"
              :label="group.label"
          >
            <el-option
                v-for="item in group.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-option-group>
        </el-select>
        <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
        <el-input v-model="inputs" placeholder="请根据联系人姓名查询" style="width: 200px"></el-input>
        <el-button style="margin-left: 10px" @click="findLinkmanName(inputs)">搜索</el-button>
      </el-col>
      <el-col>
        <el-button @click="dialogVisible = true" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>新建</el-button>
      </el-col>
    </el-row>

    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>

      <el-table-column prop="linkmanId" label="ID" sortable/>

      <el-table-column prop="linkmanName" label="姓名" sortable/>

      <el-table-column prop="client.clientName" label="对应客户" show-overflow-tooltip sortable/>
      <el-table-column prop="linkmanSex" label="性别" sortable/>

      <el-table-column  label="操作">
        <template  #default="scope">
            <el-button @click="editTherapy(scope.row)" size="mini">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div class="fy_div">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tableData.length">
      </el-pagination>
    </div>
  </el-card>

    <el-dialog
        title="联系人"
        v-model="dialogVisible"
        width="60%">
      <el-form :model="linkman" status-icon  ref="linkman" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="联系人名称" prop="linkmanName">
              <el-input v-model="linkman.linkmanName" ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="联系人电话" prop="linkmanPhone">
              <el-input  v-model="linkman.linkmanPhone"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="对应客户" prop="clientId">
              <el-select v-model="linkman.client.clientId" placeholder="请选择" :disabled="disabled">
                <el-option
                    v-for="item in clientTableData"
                    :key="item.clientId"
                    :label="item.clientName"
                    :value="item.clientId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="职位" prop="linkmanPosition">
              <el-input v-model="linkman.linkmanPosition"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="10">
            <el-form-item label="性别" prop="linkmanSex">
              <el-select v-model="linkman.linkmanSex" placeholder="请选择">
                <el-option
                    v-for="item in linkmanSex"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="备注" prop="linkmanRemark">
              <el-input v-model="linkman.linkmanRemark"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="ClearFrom">取 消</el-button>
      <el-button @click="saveLinkman" type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'linkMan',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',
      disabled:false,
      tableData:[],
      clientTableData:[],
      multipleSelection: [],
      linkman:{
        linkmanId:'',
        linkmanName:'',
        linkmanPhone:'',
        linkmanPosition:'',
        linkmanSex:'',
        linkmanRemark:'',
        client:{
          clientId:'',
          clientName:''
        },
      },
      options: [
        {
          label: '性别',
          options: [
            {
              value: '男',
              label: '男'
            },
            {
              value: '女',
              label: '女'
            }
          ]
        },
      ],
      value: '',
      linkmanSex:[
        {
          value: '男',
          label: '男'
        },
        {
          value: '女',
          label: '女'
        }
      ],
      inputs:'',

      rules: {
        linkmanName: [
          { required: true, message: '请输入联系人名称', trigger: 'blur' },
        ],
        linkmanPhone: [
          { required: true, message: '请输入联系人电话', trigger: 'blur' },
          {
            min: 11,
            max: 11,
            message: '长度为11个字符',
            trigger: 'blur',
          },
        ],
        clientId: [
          { required: true, message: '请输入对应客户', trigger: 'blur' },
        ],
        linkmanPosition: [
          { required: true, message: '请输入职位', trigger: 'blur' },
        ],
        linkmanSex: [
          { required: true, message: '请选择性别', trigger: 'change' },
        ]
      },
    }
  },
  methods: {
    initData(){
      this.axios.get("/find_linkman")
          .then((v)=>{
            this.tableData = v.data
          })
    },
    findLinkmanName(inputs){
      this.axios.get("/find_linkman_name",{params:{linkmanName:inputs}})
          .then((v)=>{
            this.tableData = v.data
          })
    },
    selectLinkman(){
      this.axios.get("/select_linkman",this.linkman)
          .then((v)=>{
            console.log(v.data)
          })
    },
    findClient(){
      this.axios.get("client/find_client").then((v)=>{
        this.clientTableData=v.data
      })
    },
    saveLinkman(){
      this.$refs["linkman"].validate((v)=>{
        if(v){
          this.axios.post("/save_linkman",this.linkman)
              .then((v)=>{
                this.initData()
                this.$message("操作成功")
                this.dialogVisible=false;
              })
        }else{
          return false
        }
      })
    },
    //回显弹出框
    editTherapy(row){
      this.linkman = Object.assign({}, row)
      this.disabled=true
      this.dialogVisible=true;
    },
    //清空弹框
    ClearFrom(){
      this.$refs['linkman'].resetFields()
      this.linkman = this.$options.data().linkman
      this.disabled=false
      this.dialogVisible=false;
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    formatter(row, column) {
      return row.address
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize)  //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },
    pageChange(p) {
      this.initData(p, this.pageSize)
    }
  },
  created() {
    this.initData();
    this.findClient();
    // user:JSON.parse(localStorage.setItem("loginuser"))
  },
}
</script>

<style scoped>
.fy_div{
  margin-top:20px;
  margin-left: 450px;
}
</style>
