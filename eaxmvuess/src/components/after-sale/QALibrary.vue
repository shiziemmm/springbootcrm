<template>
  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">QA库</span></el-col>
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
          <el-input v-model="inputs" placeholder="请根据问题查询" style="width: 200px"></el-input>
          <el-button style="margin-left: 10px" @click="findQaProblem(inputs)">搜索</el-button>
        </el-col>
        <el-col>
          <el-button @click="openDog()" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>新建</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%">
        <el-table-column type="selection" width="55"> </el-table-column>

        <el-table-column prop="qaId" label="序号" sortable/>

        <el-table-column prop="qaProblem" label="问题" sortable/>

        <el-table-column prop="qaClassify" label="分类" sortable/>

        <el-table-column prop="qaTime" label="创建日期" :formatter="dateformat" sortable/>

        <el-table-column prop="qaAuthor" label="作者" sortable/>

        <el-table-column prop="qaState" label="状态" sortable/>

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
        title="QA库"
        v-model="dialogVisible"
        width="60%">
      <el-form :model="qa" status-icon ref="qa" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="问题" prop="qaProblem">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaProblem"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="答案" prop="qaExplain">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaExplain"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="内部提示" prop="qaInternalTip">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaInternalTip"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="作者" prop="qaAuthor">
              <el-select v-model="qa.qaAuthor" placeholder="请选择" disabled>
                <el-option
                    v-for="item in qaAuthor"
                    :key="item.empId"
                    :label="item.empName"
                    :value="item.empName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="创建时间" prop="qaTime" >
              <el-date-picker
                  v-model="qa.qaTime"
                  type="datetime"
                  placeholder="选择日期" disabled>
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="分类" prop="qaClassify">
              <el-select v-model="qa.qaClassify" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="状态" prop="qaState">
              <el-select v-model="qa.qaState" placeholder="请选择">
                <el-option
                    v-for="item in qaState"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="ClearFrom">取 消</el-button>
      <el-button @click="saveQa" type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import moment from "moment";
export default {
  name: 'QALibrary',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',
      qa:{
        qaId:'',
        callCentenId:'',
        qaProblem:'',
        qaExplain:'',
        qaInternalTip:'',
        qaAuthor:'',
        qaTime:'',
        qaClassify:'',
        qaState:'',
      },
      tableData: [],
      multipleSelection: [],
      options: [
        {
          value: 'A',
          label: 'A'
        },{
          value: 'B',
          label: 'B'
        },{
          value: 'C',
          label: 'C'
        },{
          value: 'D',
          label: 'D'
        }
      ],
      qaState:[
        {
          value: '正常',
          label: '正常'
        },{
          value: '关闭',
          label: '关闭'
        }
      ],
      qaAuthor: [],
      value: '',
      inputs:'',
      rules: {
        qaProblem: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          {
            min: 3,
            max: 5,
            message: '长度在 3 到 20 个字符',
            trigger: 'blur',
          },
        ],
        qaExplain: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          {
            min: 3,
            max: 5,
            message: '长度在 3 到 20 个字符',
            trigger: 'blur',
          },
        ],
        qaInternalTip: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          {
            min: 3,
            max: 5,
            message: '长度在 3 到 20 个字符',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    initData(){
      this.axios.get("/find_qa").then((v)=>{
        this.tableData=v.data
      })
    },
    initData1(){
      this.axios.get("/find_by_empName").then((v)=>{
        this.qaAuthor=v.data
      })
    },
    //打开弹出框
    openDog(){
      this.token = JSON.parse(localStorage.getItem("loginuser"))
      this.qa.qaAuthor = this.token.empName
      this.dialogVisible = true
    },
    //回显弹出框
    editTherapy(row){
      this.qa = Object.assign({}, row)
      this.dialogVisible=true;
    },
    //清空弹框
    ClearFrom(){
      this.$refs['qa'].resetFields()
      this.qa = this.$options.data().qa
      this.dialogVisible=false;
    },
    //模糊查询
    findQaProblem(inputs){
      this.axios.get("/find_qa_problem",{params:{qaProblem:inputs}})
          .then((v)=>{
            this.tableData=v.data
          })
    },
    saveQa(){
      this.$refs["qa"].validate((v)=>{
        if(v){
          this.axios.post("/save_qa",this.qa)
              .then((v)=>{
                this.dialogVisible = false
                this.$message("操作成功")
                this.initData()
              })
        }else{
          return false
        }
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
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    pageChange(p) {
      this.initData(p, this.pageSize)
    },
    //默认选中当前时间
    getDataTime(){
      this.qa.qaTime = new Date();
    },
  },
  mounted() {
    let that = this;
    that.getDataTime()
  },
  created() {
    this.initData();
    this.initData1()
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
