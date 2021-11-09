<template>
  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">投诉管理</span></el-col>
  </el-row>
  <el-card style="height: 600px">
    <el-row :span="8">
      <el-select v-model="value"  placeholder="请选择" style="width: 150px" @change="dome()">
        <el-option-group
            v-for="group in options"
            :key="group.label"
            :label="group.label">
          <el-option
              v-for="item in group.options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-option-group>
      </el-select>
      <el-input placeholder="请输入接待人" style="width: 150px;margin-left: 100px" v-model="content"></el-input>
      <el-button class="el-button el-button--default is-circle"  @click="cs()">
        <!--        <i class="el-icon-search"></i>-->
        查找
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
<!--        <span style="font-size: 12px">投诉信息 共{{tableData.length}}条</span>-->
        <el-button  size="small" type="danger" v-show="relieve" @click="cs1()">
          解除搜索
        </el-button>
        <el-button size="small" style="margin-left: 1100px" @click="dialogVisible = true">新增</el-button>
      </el-col>

    </el-row>
    <el-row style="margin-top: 20px">
      <el-col>
        <el-table
            :data="tableData.slice((page-1)*size,page*size)"
            border
            :header-cell-style="{textAlign: 'center'}"
            :cell-style="{ textAlign: 'center' }"
            style="width: 100%">
          <el-table-column
              prop="comId"
              header-align="center"
              label="编号">
          </el-table-column>
          <el-table-column
              prop="comThemes"
              label="投诉主题">
          </el-table-column>
          <el-table-column
              prop="customerName"
              label="对应客户">
          </el-table-column>
          <el-table-column
              prop="comType"
              label="分类">
          </el-table-column>

          <el-table-column
              prop="comTime"
              label="日期">
          </el-table-column>
          <el-table-column
              prop="comUrgency"
              label="紧急程度">
          </el-table-column>
          <el-table-column
              prop="comLinkman"
              label="首问接待人">
          </el-table-column>
          <el-table-column
              prop="comTreating"
              label="处理结果">
          </el-table-column>
          <el-table-column
              prop="comComplainant"
              label="投诉人">
          </el-table-column>
          <el-table-column label="操作" width="220px">
            <template v-slot:default="r">
              <!--        <el-button type="danger" @click="open(r.row)">删除</el-button>-->
              <el-button type="primary" size="small" @click="opens(r.row)">视图</el-button>
              <el-button type="primary" size="small" @click="edit(r.row)">编辑</el-button>
              <el-button type="primary" size="small" @click="deletes(r.row.comId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页插件 -->
        <el-pagination
            style="text-align: center;margin-top: 10px"
            @size-change="HandleSizeChange"
            @current-change="HandleCurrentChange"
            :current-page="page"
            :page-sizes="[2,4,6,8,10]"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="tableData.length">
        </el-pagination>
      </el-col>
    </el-row>
    <el-dialog
        title="投诉"
        v-model="dialogVisible"
        width="60%">
      <el-form  status-icon  label-width="100px">
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="callCentenTheme" >
              <el-input v-model="plan.comThemes" ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="对应客户" prop="registrationNumber">
              <el-select v-model="plan.customerName" placeholder="请选择" @change="rets()">
                <el-option
                    v-for="item in order"
                    :label="item.clientName"
                    :value="item.clientName"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">

            <el-form-item label="首问接待人：" label-width="110px" >
              <el-select v-model="plan.comLinkman" placeholder="请选择">
                <el-option v-for="ks in opstion"
                           :label="ks.empName"
                           :value="ks.empName">
                </el-option>
              </el-select>
            </el-form-item>

          </el-col>

          <el-col :span="10">
            <el-form-item label="分类" prop="callCentenWay">
              <el-select v-model="plan.comType" placeholder="请选择" style="width: 200px" >
                <el-option
                    v-for="item in lei"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="投诉日期" prop="callCentenStartTime">
              <el-date-picker
                  v-model="plan.comTime"
                  type="datetime"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="花费时间" prop="callCentenTime">
              <el-select v-model="plan.comSpendTime" placeholder="请选择" @change="lll()">
                <el-option
                    v-for="item in domes"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

<!--          <el-col>-->
<!--            <el-form-item label="状态" prop="callCentenState">-->
<!--              <el-radio-group v-model="callCenten.callCentenState">-->
<!--                <el-radio label="0">无需处理</el-radio>-->
<!--                <el-radio label="1">未处理</el-radio>-->
<!--                <el-radio label="2">处理中</el-radio>-->
<!--                <el-radio label="3">处理完成</el-radio>-->
<!--              </el-radio-group>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

          <el-col :span="10">
            <el-form-item label="描述" >
              <el-input  v-model="plan.comDescribe"></el-input>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="服务内容" prop="callCentenContent">
              <el-input  v-model="plan.comTreating"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="回访确认" prop="callCentenContent">
              <el-input  v-model="plan.comReturn"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="客户反馈" prop="callCentenFeedback">
              <el-input  v-model="plan.comFeedback"></el-input>
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="备注" prop="callCentenRemark">
              <el-input v-model="plan.comRemark"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="紧急程度" prop="callCentenRemark">
                <el-radio-group v-model="plan.comUrgency"  >
                  <el-radio label="非常急">非常急</el-radio>
                  <el-radio label="急">急</el-radio>
                  <el-radio label="普通">普通</el-radio>
                </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="处理结果" prop="callCentenRemark">
              <el-radio-group v-model="plan.comTreating"  >
                <el-radio label="未处理">未处理</el-radio>
                <el-radio label="处理中">处理中</el-radio>
                <el-radio label="已完成">已完成</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
        <el-row>
          <el-col :span="18"></el-col>
          <el-col :span="2">
            <el-button @click="onSubmit('form')" type="primary">确定</el-button>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="2">
            <el-button @click="dialogVisible = false" type="danger">取消</el-button>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

      </template>
    </el-dialog>

    <el-dialog
        title="视图"
        v-model="dialogVisible1"
        width="60%"
    >
      <el-divider></el-divider>

      <el-form>
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="callCentenTheme" >
                {{shitu.comThemes}}
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="对应客户" prop="registrationNumber">
              {{shitu.customerName}}
            </el-form-item>
          </el-col>

          <el-col :span="10">

            <el-form-item label="首问接待人：" label-width="110px" >
              {{shitu.comLinkman}}
            </el-form-item>

          </el-col>

          <el-col :span="10">
            <el-form-item label="分类" prop="callCentenWay">
              {{shitu.comType}}
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="投诉日期" prop="callCentenStartTime">
              {{shitu.comTime}}
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="花费时间" prop="callCentenTime">
              {{shitu.comSpendTime}}
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="描述" >
              {{shitu.comDescribe}}
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="服务内容" prop="callCentenContent">
              {{shitu.comTreating}}
            </el-form-item>
          </el-col>
          <el-col>
          <el-form-item label="回访确认" prop="callCentenContent">
            {{shitu.comReturn}}
          </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="客户反馈" prop="callCentenFeedback">
              {{shitu.comFeedback}}
            </el-form-item>
          </el-col>

          <el-col>
            <el-form-item label="备注" prop="callCentenRemark">
              {{shitu.comRemark}}
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="紧急程度" prop="callCentenRemark">
              {{shitu.comUrgency}}
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="处理结果" prop="callCentenRemark">
              {{shitu.comTreating}}
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible1= false">取 消</el-button>
      <el-button type="primary" @click="dialogVisible1 = false">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible1:false,
      dialogVisible:false,
      options: [{
        options: [{
          value: '全部数据',
          label: '全部数据'
        }]
      },
        {
          options: [{
            value: '未处理',
            label: '未处理'
          }, {
            value: '处理中',
            label: '处理中'
          },
            {
              value: '已完成',
              label: '已完成'
            }]
        }],
      value: '全部数据',
      content:'',
      relieve:false,
      tableData: [],
      plan:{
        comId:0,//回款编号
        comThemes:'',//投诉主题
        customerName:'',//对应客户
        comLinkman:'',//首问接待人
        comType:'',//分类
        comDescribe:'',//描述
        comTime:'',//回款日期
        comSpendTime:'',//花费时间
        comUrgency:'',//紧急程度
        comComplainant:'',//投诉人
        comTreating:'',//处理过程
        comFeedback:'',//客户反馈
        comReturn:'',//回访确认
        comRemark:'',//备注
      },
      //期次下拉框
      option:[],
      //第几期
      opstion:[],
      order:[],
      shitu:[],
      lei: [
        {
          value: '产品投诉',
          label: '产品投诉',
        },
        {
          value: '服务投诉',
          label: '服务投诉',
        },
        {
          value: '客户意见',
          label: '客户意见',
        },
        {
          value: '其他',
          label: '其他',
        },
      ],
      domes: [
        {
          value: '一小时',
          label: '一小时',
        },
        {
          value: '二小时',
          label: '二小时',
        },
        {
          value: '半个工作日',
          label: '半个工作日',
        },
        {
          value: '一个工作日',
          label: '一个工作日',
        },
      ],
      size:4,
      page:1
    }
  },
  methods:{
    HandleSizeChange: function(size) {
      this.size = size;
    },
    //初始页page
    HandleCurrentChange: function(currentPage) {
      this.page = currentPage;
    },
    getData(){
      this.axios.get("/complaint/myResult").then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].comTime)
          const date = new Date(this.tableData[i].comTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].comTime=y + '-' + MM + '-' + d
        }
        console.log(this.tableData)
      }).catch()
      this.axios.get('/issue/issueList').then((v)=>{
        this.option=v.data.data
      }).catch()
      this.axios.get("/returned/principal").then((v)=>{
        this.opstion=v.data
      }).catch()
      this.axios.get("/concerned/clients").then((v)=>{

        this.order=v.data
        console.log(this.order)

      }).catch()
    },
    dome(){
      if(this.value=="全部数据"){
        this.getData()
      }else{
        this.axios({
          url:"/complaint/myResults",
          params:{value:this.value}
        }).then((res)=>{
          this.tableData=res.data.data
          for (let i = 0; i < this.tableData.length ; i++) {
            console.log(this.tableData[i].comTime)
            const date = new Date(this.tableData[i].comTime)
            const y = date.getFullYear()// 年
            let MM = date.getMonth() + 1 // 月
            MM = MM < 10 ? ('0' + MM) : MM
            let d = date.getDate() // 日
            d = d < 10 ? ('0' + d) : d
            this.tableData[i].comTime=y + '-' + MM + '-' + d
          }
        }).catch()
        console.log(this.value,"1111")
        this.relieve=true
      }
    },
    cs(){
      if(this.content==null||this.content==undefined){
        this.getData()
      }
      this.axios({
        url:"/complaint/likeName",
        params:{name:this.content}
      }).then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].comTime)
          const date = new Date(this.tableData[i].comTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].comTime=y + '-' + MM + '-' + d
        }
      }).catch();
      this.relieve=true
    },
    cs1(){
      this.relieve=false
      this.value='全部数据'
      this.content=''
      this.getData()
    },
    rets(){
      console.log(this.plan.odrId)
      // console.log(this.order)
      if(this.plan.odrId!=null || this.plan.odrId!=undefined){
        for (let i = 0; i < this.order.length; i++) {
          if(this.plan.odrId == this.order[i].odrOn){
            this.plan.customerName=this.order[i].customerName
            this.plan.plaPrice=this.order[i].odrPrice
          }
        }
      }
    },
    onSubmit(formName){
      console.log(this.plan)
      this.axios.post("/complaint/add",this.plan).then((v)=>{
        if(v.data===1){
          this.dialogVisible=false
          this.getData()
        }else{
          alert("新增失败")
        }
      }).catch()
    },
    opens(row){
      this.dialogVisible1=true
      this.shitu=row
      console.log(this.shitu)
    },
    edit(row){
      this.dialogVisible=true
      // this.ret=[]
      console.log(row)
      this.plan.comId=row.comId
      this.plan.comThemes=row.comThemes
      this.plan.customerName=row.customerName
      this.plan.comLinkman=row.comLinkman
      this.plan.comType=row.comType
      this.plan.comDescribe=row.comDescribe
      this.plan.comTime=row.comTime
      this.plan.comSpendTime=row.comSpendTime
      this.plan.comUrgency=row.comUrgency
      this.plan.comComplainant=row.comComplainant
      this.plan.comTreating=row.comTreating
      this.plan.comFeedback=row.comFeedback
      this.plan.comReturn=row.comReturn
      this.plan.comRemark=row.comRemark
    },
    deletes(plaId){
      console.log(plaId)
      // this.axios.get('/returned/deleteid',reId).then().catch()
      this.axios({
        url:"/complaint/shanChu",
        params:{plaId:plaId}
      }).then((v)=>{
        if(v.data>0){
          this.getData()
        }
      }).catch();
    },
    lll(){
      this.plan.comComplainant=this.plan.customerName
    }
  },
  created() {
    this.getData()
  }
}
</script>

<style scoped>

</style>
