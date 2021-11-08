<template>
  <div>
    <el-card style="width: 490px;height: 100%">
      <!--表头-->
      <el-row>
        <el-table :data="qaAuthor" style="margin-top: 20px">
          <el-table-column
              prop="empName"
              width="130px">
          </el-table-column>
          <el-table-column>
            <template #default="scope">
              <el-button size="mini" @click="findOld(scope.row.empName)">转出</el-button>
            </template>
          </el-table-column>
          <el-table-column>
            <template #default="scope">
              <el-button size="mini" @click="findNew(scope.row.empName)">转入</el-button>
            </template>
          </el-table-column>

        </el-table>
      </el-row>
    </el-card>


    <el-card style="width: 60%;height: 100%;margin-left: 500px;margin-top: -160px">
      <!--表头-->
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column
            prop="client.clientName"
            label="客户">
        </el-table-column>
        <el-table-column
            prop="customerTransferOld"
            label="原所有者">
        </el-table-column>
        <el-table-column
            prop="customerTransferNew"
            label="新所有者">
        </el-table-column>
        <el-table-column
            prop="customerTransferHandlers"
            label="操作人">
        </el-table-column>
        <el-table-column
            prop="customerTransferTime"
            :formatter="dateformat"
            label="操作时间">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: 'CustomerTransferRecord',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      tableData: [],
      multipleSelection: [],

      qaAuthor: [],
      emp:{
        empId:'',
        empName:''
      },
      value: '',

    }
  },
  methods: {
    initData1(){
      this.axios.get("/find_by_empName").then((v)=>{
        this.qaAuthor=v.data
      })
    },
    findOld(row){
      this.axios.get("/find_old",{params:{customerTransferOld:row}})
          .then((v)=>{
            this.tableData = v.data
          })
    },
    findNew(row){
      this.axios.get("/find_new",{params:{customerTransferNew:row}})
          .then((v)=>{
            this.tableData = v.data
          })
    },
    dateformat(row , column){
      const data = row[column.property]
      if (data == undefined){
        return
      }
      return moment(data).format("yyyy-MM-DD HH:mm:ss")
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
    this.initData1()
    this.findOld()
    this.findNew()
    // user:JSON.parse(localStorage.setItem("loginuser"))
  },
}
</script>

<style scoped>

</style>
