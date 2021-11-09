<template>
  <div class="div_top">
    <div class="div_top_z">
      客服控制台
    </div>
  </div>

  <div>
    <el-card class="box-card">
      <template #header>
        <span>客户</span>
        <div class="card-header">
          <el-input v-model="clientInputs" placeholder="请输入客户名称" style="width: 200px"></el-input>
          <el-button class="button" type="text" @click="findByClientName(clientInputs)">查询</el-button>
        </div>
      </template>
      <div v-for="o in clientTableData" :key="o" class="text item" @click="clientClick(o)">
        <li>{{ o.clientName }}</li>
      </div>

<!--      客户表单-->
      <el-form :model="callCenten" status-icon  ref="callCenten" label-width="100px" class="demo-ruleForm" v-show="show">
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="callCentenTheme" >
              <el-input v-model="callCenten.callCentenTheme" ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="对应客户" prop="clientName">
              <el-select v-model="callCenten.clientId" placeholder="请选择" disabled>
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
            <el-form-item label="服务类型" prop="callCentenType">
              <el-select v-model="callCenten.callCentenType" placeholder="请选择">
                <el-option
                    v-for="item in callCentenType"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="服务方式" prop="callCentenWay">
              <el-select v-model="callCenten.callCentenWay" placeholder="请选择">
                <el-option
                    v-for="item in callCentenWay"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
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
                    v-for="item in callCentenTime"
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
                    v-for="item in callCentenExecutor"
                    :key="item.empId"
                    :label="item.empName"
                    :value="item.empName">
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

          <el-col>
            <el-form-item label="对应FAQ" prop="qaProblem">
              <el-input v-model="callCenten.qa.qaProblem" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-button @click="saveCustomer" type="primary">确 定</el-button>
        </el-row>
      </el-form>
    </el-card>

    <el-card class="box-card1">
      <template #header>
        <span>QA</span>
        <div class="card-header">
          <el-input v-model="QaInputs" placeholder="请搜索您要问的问题" style="width: 200px"></el-input>
          <el-button class="button" type="text" @click="findQaProblem(QaInputs)">查询</el-button>
        </div>
      </template>
      <div v-for="o in qaTableData" :key="o" class="text item" @click="qaClick(o)">
        <li>{{o.qaProblem }}</li>
      </div>

<!--      QA库表单-->
      <el-form :model="qa" status-icon ref="qa" label-width="100px" class="demo-ruleForm" v-show="qaShow">
        <el-row>
          <el-col>
            <el-form-item label="问题" prop="registrationNumber">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaProblem" disabled></el-input>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="答案" prop="registrationName">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaExplain" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="内部提示" prop="registrationName">
              <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="qa.qaInternalTip" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="作者" prop="registrationType">
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

          <el-col>
            <el-form-item label="分类" prop="registrationName">
              <el-select v-model="qa.qaClassify" placeholder="请选择" disabled>
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
            <el-form-item label="状态" prop="patientDataSex">
              <el-select v-model="qa.qaState" placeholder="请选择" disabled>
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
        <el-button @click="goToCus()">把该问题加入客户服务</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'CustomerConsole',
  data() {
    return {
      show:false,
      qaShow:false,
      dialogVisible: false,
      clientInputs: '',
      QaInputs:'',
      clientTableData:[],
      qaTableData:[],
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
        },
        qa:{
          qaId:'',
          qaProblem:''
        }
      },


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

      callCentenType:[
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
      ],
      callCentenWay:[
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
      ],
      callCentenTime:[
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
      ],
      callCentenState:[
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
      ],
    }
  },
  methods: {
    qaClick(o){
      this.qa = o;
      this.qaShow = true
    },
    clientClick(o){
      this.token = JSON.parse(localStorage.getItem("loginuser"))
      this.callCenten.clientId = o.clientId
      this.callCenten.callCentenExecutor = this.token.empName
      this.show = true
    },
    findByClientName(clientInputs){
      this.axios.get("client/find_client_name",{params:{clientName:clientInputs}})
          .then((v)=>{
            this.clientTableData = v.data
            this.show = false
            console.log(v.data)
          })
    },
    findQaProblem(QaInputs){
      this.axios.get("find_qa_problem",{params:{qaProblem:QaInputs}})
          .then((v)=>{
            this.qaTableData = v.data
            this.qaShow = false
            console.log(v.data)
          })
    },
    goToCus(){
      this.callCenten.callCentenTheme=this.qa.qaProblem
      this.callCenten.qa.qaProblem=this.qa.qaProblem
    },
    saveCustomer(){
      this.axios.post("add_call_centen_qa",this.callCenten)
          .then((v)=>{
            this.$message("操作成功")
            this.show=false
            this.qaShow=false
          })
    },

  },
  created() {
    // user:JSON.parse(localStorage.setItem("loginuser"))
  },
}
</script>

<style scoped>
.div_top{
  height: 50px;
  line-height: 50px;
}
.div_top_z{
  text-align: center;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 600px;
  float: left;
}
.box-card1{
  width: 650px;
  float: left;
}

</style>
