<template>
  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">客户关怀</span></el-col>
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
      <el-input placeholder="请输入执行人" style="width: 150px;margin-left: 100px" v-model="content"></el-input>
      <el-button class="el-button el-button--default is-circle"  @click="cs()">
        <!--        <i class="el-icon-search"></i>-->
        查找
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
<!--        <span style="font-size: 12px">客户关怀 共{{tableData.length}}条</span>-->
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
              prop="conId"
              header-align="center"
              label="编号">
          </el-table-column>
          <el-table-column
              prop="conTheme"
              label="服务主题">
          </el-table-column>
          <el-table-column
              prop="customerName"
              label="对应客户">
          </el-table-column>
          <el-table-column
              prop="conLinkman"
              label="联系人">
          </el-table-column>

          <el-table-column
              prop="conTconTime"
              label="日期">
          </el-table-column>
          <el-table-column
              prop="conExecutor"
              label="执行人">
          </el-table-column>
          <el-table-column
              prop="conType"
              label="类型">
          </el-table-column>
          <el-table-column label="操作" width="220px">
            <template v-slot:default="r">
              <!--        <el-button type="danger" @click="open(r.row)">删除</el-button>-->
              <el-button type="primary" size="small" @click="opens(r.row)">视图</el-button>
              <el-button type="primary" size="small" @click="edit(r.row)">编辑</el-button>
              <el-button type="primary" size="small" @click="deletes(r.row.conId)">删除</el-button>
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
        title="新建关怀信息"
        v-model="dialogVisible"
        width="60%"
    >
      <el-divider></el-divider>
      <el-form >
        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item  label="对应客户：" style="width: 200px" >
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
          <el-col :offset="1" :span="8">
          <el-form-item label="对应客户：" style="width: 200px" >
            <el-input v-model="plan.conLinkman" disabled ></el-input>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10" :offset="1">
            <el-form-item  label="主题：" style="width: 200%">
              <el-input size="medium"  v-model="plan.conTheme"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="时间：" label-width="100px" >
              <el-date-picker v-model="plan.conTconTime" type="date" placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="10">
            <el-form-item label="执行人：" label-width="110px" >
              <el-select v-model="plan.conExecutor" placeholder="请选择">
                                <el-option v-for="ks in opstion"
                                           :label="ks.empName"
                                           :value="ks.empName">
                                </el-option>
                              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="类型：" label-width="100px" style="width: 60%" >
              <el-select v-model="plan.conType" placeholder="请选择" style="width: 200px" >
                <el-option
                    v-for="item in lei"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="22">
            <el-form-item  label="关怀内容：" label-width="100px" >
              <el-input type="textarea" v-model="plan.conContent" property="订单备注"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="22">
            <el-form-item  label="客户反馈：" label-width="100px" >
              <el-input type="textarea" v-model="plan.conFeedback" property="订单备注"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="22">
            <el-form-item  label="备注：" label-width="100px" >
              <el-input type="textarea" v-model="plan.conRemark" property="订单备注"/>
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
        <el-form-item label="关系主题：">
          {{shitu.conTheme}}
        </el-form-item>
        <el-form-item label="对应客户：" style="margin-top: -20px">
          {{shitu.customerName}}
        </el-form-item>
        <el-row>
          <el-col span="8">
            <el-form-item label="日期：" style="margin-top: -20px">
              {{shitu.conTconTime}}
            </el-form-item>
          </el-col>
          <!--          <el-col span="8" :offset="11">-->
          <!--            <el-form-item label="期次：" style="margin-top: -20px">-->
          <!--              {{shitu.isuId}}-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
        </el-row>
        <el-row>
          <el-col span="8">
            <el-form-item label="联系人：" style="margin-top: -20px">
              {{shitu.conLinkman}}
            </el-form-item>
          </el-col>
          <el-col span="8" :offset="13">
            <el-form-item label="执行人：" style="margin-top: -20px">
              {{shitu.conExecutor}}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="8">
            <el-form-item label="关怀内容：" style="margin-top: -20px">
              {{shitu.conContent}}
            </el-form-item>
          </el-col>
          <el-col span="8" :offset="13">
            <el-form-item label="客服反馈：" style="margin-top: -20px">
              {{shitu.conFeedback}}
            </el-form-item>
          </el-col>
          <el-col >
            <el-form-item label="备注：" style="margin-top: -20px">
              {{shitu.conRemark}}
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
      }, {
        options: [ {
          value: '分类一',
          label: '分类一',
        },
          {
            value: '分类二',
            label: '分类二',
          },
          {
            value: '分类三',
            label: '分类三',
          }]
      },
        ],
      value: '全部数据',
      content:'',
      relieve:false,
      tableData: [],
      size:4,
      page:1,
      plan:{
        conId:0,//回款编号
        conTheme:'',//主题
        customerName:'',//客户
        conLinkman:'',//联系人
        conTconTime:'',//日期
        conExecutor:'',//执行人
        conType:'',//类型
        conContent:'',//关怀内容
        conFeedback:'',//客户反馈
        conRemark:''//备注
      },
      //期次下拉框
      option:[],
      //第几期
      opstion:[],
      order:[],
      shitu:[],
      lei: [
        {
          value: '分类一',
          label: '分类一',
        },
        {
          value: '分类二',
          label: '分类二',
        },
        {
          value: '分类三',
          label: '分类三',
        },

      ],
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
      this.axios.get("/concerned/myResult").then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].plaTime)
          const date = new Date(this.tableData[i].conTconTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].conTconTime=y + '-' + MM + '-' + d
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
          url:"/concerned/myResult1",
          params:{value:this.value}
        }).then((res)=>{
          this.tableData=res.data.data
          for (let i = 0; i < this.tableData.length ; i++) {
            console.log(this.tableData[i].conTconTime)
            const date = new Date(this.tableData[i].conTconTime)
            const y = date.getFullYear()// 年
            let MM = date.getMonth() + 1 // 月
            MM = MM < 10 ? ('0' + MM) : MM
            let d = date.getDate() // 日
            d = d < 10 ? ('0' + d) : d
            this.tableData[i].conTconTime=y + '-' + MM + '-' + d
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
        url:"/concerned/likeName",
        params:{name:this.content}
      }).then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].conTconTime)
          const date = new Date(this.tableData[i].conTconTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].conTconTime=y + '-' + MM + '-' + d
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
      console.log(this.plan.customerName)
      this.plan.conLinkman=this.plan.customerName
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
      console.log(this.plan.conId)
      console.log(this.plan)
      this.axios.post("/concerned/addCon",this.plan).then((v)=>{
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
      this.plan.conId=row.conId
      this.plan.conTheme=row.conTheme
      this.plan.customerName=row.customerName
      this.plan.conLinkman=row.conLinkman
      this.plan.conTconTime=row.conTconTime
      this.plan.isuId=row.isuId
      this.plan.conExecutor=row.conExecutor
      this.plan.conRemark=row.conRemark
      this.plan.conContent=row.conContent
      this.plan.conFeedback=row.conFeedback
    },
    deletes(plaId){
      console.log(plaId)
      // this.axios.get('/returned/deleteid',reId).then().catch()
      this.axios({
        url:"/concerned/deleteId",
        params:{plaId:plaId}
      }).then((v)=>{
        if(v.data>0){
          this.getData()
        }
      }).catch();
    }
  },
  created() {
    this.getData()
  }
}
</script>

<style scoped>

</style>
