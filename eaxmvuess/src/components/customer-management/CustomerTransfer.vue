<template>


  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">客户转移</span></el-col>
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
          <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
          <el-button style="margin-left: 10px" @click="findByClientName(inputs)">搜索</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"> </el-table-column>

        <el-table-column prop="clientId" label="ID" sortable/>

        <el-table-column prop="clientName" label="名称" sortable/>

        <el-table-column prop="clientTransfer" label="所有者" sortable/>

        <el-table-column prop="clientRemark" label="备注" sortable/>

        <el-table-column  label="操作">
          <template  #default="scope">
              <el-button @click="editTherapy(scope.row)" size="mini">转移</el-button>
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
        title="客户"
        v-model="dialogVisible"
        width="60%">
      <el-row :model="client">
        <el-col v-if="client.emp.empName != undefined">
          <el-radio-group v-model="client.emp.empName">
            <el-radio v-for="item in empTableData" :label="item.empName"></el-radio>
          </el-radio-group>
        </el-col>
      </el-row>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="ClearFrom">取 消</el-button>
      <el-button @click="saveClient" type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CustomerTransfer',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',
      tableData: [],
      empTableData:[],
      client:{
        clientId:'',
        clientName:'',
        clientPhone:'',
        clientTime:'',
        clientSeas:'',
        clientKind:'',
        clientPeriod:'',
        clientQualitative:'',
        clientRank:'',
        clientSigningDate:'',
        clientContractAmount:'',
        clientSource:'',
        clientSite:'',
        clientRemark:'',
        emp:{
          empId:'',
          empName:''
        }
      },
      multipleSelection: [],
      options: [
        {
          label: '客户种类',
          options: [
            {
              value: '客户',
              label: '客户'
            },
            {
              value: '供应商',
              label: '供应商'
            },
            {
              value: '合作伙伴',
              label: '合作伙伴'
            },
            {
              value: '媒体',
              label: '媒体'
            }
          ]
        },
        {
          label: '生命周期',
          options: [
            {
              value: '潜在',
              label: '潜在'
            },
            {
              value: '签约',
              label: '签约'
            },
            {
              value: '重复购买',
              label: '重复购买'
            },
            {
              value: '失效',
              label: '失效'
            }
          ]
        },
        {
          label: '定性',
          options: [
            {
              value: '有价值',
              label: '有价值'
            },
            {
              value: '没价值',
              label: '没价值'
            },
            {
              value: '不确定',
              label: '不确定'
            }
          ]
        },
        {
          label: '定级',
          options: [
            {
              value: '大单',
              label: '大单'
            },
            {
              value: '小单',
              label: '小单'
            },
            {
              value: '正常',
              label: '正常'
            }
          ]
        },
        {
          label: '预定签约时间',
          options: [
            {
              value: '本周',
              label: '本周'
            },
            {
              value: '下周',
              label: '下周'
            },
            {
              value: '本月',
              label: '本月'
            },
            {
              value: '下月',
              label: '下月'
            }
          ]
        },
        {
          label: '客户来源',
          options: [
            {
              value: '客户介绍',
              label: '客户介绍'
            },
            {
              value: '电话来访',
              label: '电话来访'
            },
            {
              value: '媒体宣传',
              label: '媒体宣传'
            },
            {
              value: '网站填表',
              label: '网站填表'
            },
            {
              value: '其他',
              label: '其他'
            }
          ]
        },
      ],
      value: '',
      inputs:''
    }
  },
  methods: {
    initData(){
      this.axios.get("/find_by_client").then((v)=>{
        this.tableData=v.data
      })
    },
    initData1(){
      this.axios.get("/find_by_empName").then((v)=>{
        this.empTableData=v.data
      })
    },
    //模糊查询
    findByClientName(inputs){
      this.axios.get("/find_by_client_name",{params:{clientName:inputs}})
          .then((v)=>{
            this.tableData=v.data
          })
    },
    //回显弹出框
    editTherapy(row){
      this.client = Object.assign({}, row)
      this.dialogVisible=true;
    },
    //清空弹框
    ClearFrom(){
      this.client.emp.empName = ''
      this.dialogVisible=false;
    },
    saveClient(){
      console.log(this.client)
      this.axios.post("/update_old",this.client)
          .then((v)=>{
            this.dialogVisible = false
            this.$message("转移成功")
            this.initData()
          })
    },
    handleSelectionChange(row){
      this.client = row
      console.log(row)
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
    this.initData1();
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
