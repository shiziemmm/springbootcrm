<template>
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
          <el-input v-model="inputs" placeholder="请根据服务主题查询" style="width: 200px"></el-input>
          <el-button icon="el-icon-search" circle style="margin-left: 10px"></el-button>
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
        <el-table-column type="selection" width="40"> </el-table-column>

        <el-table-column prop="callCentenId" label="序号" sortable/>

        <el-table-column prop="callCentenTheme" label="主题"/>

        <el-table-column prop="clientId" label="对应客户"/>

        <el-table-column prop="callCentenType" label="服务类型"/>

        <el-table-column prop="callCentenWay" label="服务方式"/>

        <el-table-column prop="callCentenStartTime" label="开始日期" :formatter="dateformat" sortable/>

        <el-table-column prop="callCentenExecutor" label="执行人"/>

        <el-table-column prop="callCentenTime" label="花费时间"/>

        <el-table-column prop="callCentenState" label="状态">
          <template #default="scope">
            <template v-if="scope.row.callCentenState =='0'">
              无需处理
            </template>
            <template v-if="scope.row.callCentenState =='1'">
              未处理
            </template>
            <template v-if="scope.row.callCentenState =='2'">
              处理中
            </template>
            <template v-if="scope.row.callCentenState =='3'">
              处理完成
            </template>
          </template>
        </el-table-column>

        <el-table-column  label="操作">
          <template  #default="scope">

            <el-tooltip content="编辑" placement="top">
              <el-button @click="editTherapy(scope.row)" size="mini">编辑</el-button>
            </el-tooltip>

            <el-tooltip content="删除" placement="top">
              <el-button size="mini">删除</el-button>
            </el-tooltip>

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
        title="客户服务"
        v-model="dialogVisible"
        width="60%">
      <el-form :model="callCenten" status-icon  ref="callCenten" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="callCentenTheme" >
              <el-input v-model="callCenten.callCentenTheme" ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="对应客户" prop="registrationNumber">
              <el-input  v-model="callCenten.clientId"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="服务类型" prop="callCentenType">
              <el-input  v-model="callCenten.callCentenType"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="服务方式" prop="callCentenWay">
              <el-input v-model="callCenten.callCentenWay" ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="开始时间" prop="callCentenStartTime">
              <el-date-picker
                  v-model="callCenten.callCentenStartTime"
                  type="datetime"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="花费时间" prop="callCentenTime">
              <el-select v-model="callCenten.callCentenTime" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="状态" prop="callCentenState">
              <el-radio-group v-model="callCenten.callCentenState">
                <el-radio label="0">无需处理</el-radio>
                <el-radio label="1">未处理</el-radio>
                <el-radio label="2">处理中</el-radio>
                <el-radio label="3">处理完成</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="执行人" prop="callCentenExecutor">
              <el-select v-model="callCenten.callCentenExecutor" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="服务内容" prop="callCentenContent">
              <el-input  v-model="callCenten.callCentenContent"></el-input>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="客户反馈" prop="callCentenFeedback">
              <el-input  v-model="callCenten.callCentenFeedback"></el-input>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="备注" prop="callCentenRemark">
              <el-input v-model="callCenten.callCentenRemark"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="ClearFrom">取 消</el-button>
      <el-button @click="saveCustomer" type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: 'CustomerService',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',
      tableData: [],
      multipleSelection: [],
      options: [
        {
          label: '服务类型',
          options: [
            {
              value: '答疑',
              label: '答疑'
            },
            {
              value: '故障排除',
              label: '故障排除'
            },
            {
              value: '培训',
              label: '培训'
            },
            {
              value: '升级',
              label: '升级'
            }
          ]
        },{
          label: '服务方式',
          options: [
            {
              value: '电话',
              label: '电话'
            },
            {
              value: '传真',
              label: '传真'
            },
            {
              value: '邮寄',
              label: '邮寄'
            },
            {
              value: '上门',
              label: '上门'
            }
          ]
        },{
          label: '花费时间',
          options: [
            {
              value: '1个小时',
              label: '1个小时'
            },
            {
              value: '2个小时',
              label: '2个小时'
            },
            {
              value: '半个工作日',
              label: '半个工作日'
            }
            ,
            {
              value: '一个工作日',
              label: '一个工作日'
            }
          ]
        },{
          label: '状态',
          options: [
            {
              value: 0,
              label: '无需处理'
            },
            {
              value: 1,
              label: '未处理'
            },
            {
              value: 2,
              label: '处理中'
            },
            {
              value: 3,
              label: '处理完成'
            }
          ]
        },
      ],
      value: '',
      callCenten:{
        callCentenId:'',
        clientId:'',
        callCentenTheme:'',
        callCentenType:'',
        callCentenWay:'',
        callCentenStartTime:'',
        callCentenTime:'',
        callCentenState:'',
        callCentenExecutor:'',
        callCentenContent:'',
        callCentenFeedback:'',
        callCentenRemark:'',
        client:{
          clientId:'',
          clientName:'',
        }
      }
    }
  },
  methods: {
    initData(){
      this.axios.get("/find_call_centen").then((v)=>{
        this.tableData=v.data
        console.log(v.data)
      })
    },
    //回显弹出框
    editTherapy(row){
      this.callCenten = Object.assign({}, row)
      this.dialogVisible=true;
    },
    //清空弹框
    ClearFrom(){
      this.$refs['callCenten'].resetFields()
      this.callCenten = this.$options.data().callCenten
      this.dialogVisible=false;
    },
    saveCustomer(){
      this.axios.post("/save_call_centen",this.callCenten)
          .then((v)=>{
            this.dialogVisible = false
            console.log(v.data)
            this.initData()
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
    this.initData();
    // user:JSON.parse(localStorage.setItem("loginuser"))
  }
}
</script>

<style scoped>
.fy_div{
  margin-top:20px;
  margin-left: 450px;
}
</style>
