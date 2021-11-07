<template>
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
      <el-input placeholder="请输入内容" style="width: 150px;margin-left: 100px" v-model="content"></el-input>
      <el-button class="el-button el-button--default is-circle"  @click="cs()">
        <!--        <i class="el-icon-search"></i>-->
        查找
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
        <span style="font-size: 12px">回款 共{{tableData.length}}条</span>
        <el-button  size="small" type="danger" v-show="relieve" @click="cs1()">
          解除搜索
        </el-button>
        <el-button size="small" style="margin-left: 1100px" @click="dialogVisible = true">新增</el-button>
      </el-col>

    </el-row>
    <el-row style="margin-top: 20px">
      <el-col>
        <el-table
            :data="tableData"
            border
            show-summary
            :header-cell-style="{textAlign: 'center'}"
            :cell-style="{ textAlign: 'center' }"
            style="width: 100%">
          <el-table-column
              prop="odrId"
              header-align="center"
              label="订单编号">
          </el-table-column>
          <el-table-column
              prop="plaPrice"
              label="回款金额">
          </el-table-column>
          <el-table-column
              prop="customerName"
              label="客户名称">
          </el-table-column>
          <el-table-column
              prop="principal"
              label="负责人">
          </el-table-column>

          <el-table-column
              prop="plaTime"
              label="回款时间">
          </el-table-column>
          <el-table-column
              prop="plaWhether"
              label="是否回款">
          </el-table-column>
          <el-table-column
              prop="invoice"
              label="是否开发票">
          </el-table-column>
          <el-table-column label="操作" width="220px">
            <template v-slot:default="r">
              <!--        <el-button type="danger" @click="open(r.row)">删除</el-button>-->
              <el-button type="primary" size="small" @click="opens(r.row)">视图</el-button>
              <el-button type="primary" size="small" @click="edit(r.row)">编辑</el-button>
              <el-button type="primary" size="small" @click="deletes(r.row.plaId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-dialog
        title="新建回款信息"
        v-model="dialogVisible"
        width="60%"
    >
      <el-divider></el-divider>
      <el-form :model="ret">
        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item  label="订单：" style="width: 200px" >
              <el-select v-model="plan.odrId" placeholder="请选择" @change="rets()">
                <el-option
                    v-for="item in order"
                    :label="item.odrOn"
                    :value="item.odrOn"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="对应客户：" style="width: 200%">
              <el-input size="medium" disabled v-model="plan.customerName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="计划回款日期：" label-width="100px" >
              <el-date-picker v-model="plan.plaTime" type="date" placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="10">
            <el-form-item label="金额：" label-width="110px" >
              <el-input size="medium" disabled v-model="plan.plaPrice"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="付款方式：" label-width="100px" style="width: 60%" >
              <el-select v-model="plan.payment" placeholder="请选择" style="width: 200px" >
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
          <el-col :span="11">
            <el-form-item  label="是否回款：*" label-width="100px" >
              <el-radio-group v-model="plan.plaWhether"  >
                <el-radio label="已回">已回</el-radio>
                <el-radio label="未回">未回</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row>
          <el-col  :span="11">
            <el-form-item  label="是否开发票：" label-width="100px" >
              <el-radio-group v-model="plan.invoice" >
                <el-radio label="已开">已开</el-radio>
                <el-radio label="未开">未开</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="经办人：" style="width: 200px" >
              <el-select v-model="plan.principal" placeholder="请选择">
                <el-option v-for="ks in opstion"
                           :label="ks.empName"
                           :value="ks.empName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="22">
            <el-form-item  label="备注：" label-width="100px" >
              <el-input type="textarea" v-model="plan.beizhu" property="订单备注"/>
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
        <el-form-item label="订单：">
          {{shitu.odrId}}
        </el-form-item>
        <el-form-item label="客户：" style="margin-top: -20px">
          {{shitu.customerName}}
        </el-form-item>
        <el-row>
          <el-col span="8">
            <el-form-item label="计划回款日期：" style="margin-top: -20px">
              {{shitu.plaTime}}
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
            <el-form-item label="金额：" style="margin-top: -20px">
              {{shitu.plaPrice}}
            </el-form-item>
          </el-col>
          <el-col span="8" :offset="13">
            <el-form-item label="是否已回款：" style="margin-top: -20px">
              {{shitu.plaWhether}}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="8">
            <el-form-item label="员工：" style="margin-top: -20px">
              {{shitu.principal}}
            </el-form-item>
          </el-col>
          <el-col span="8" :offset="13">
            <el-form-item label="备注：" style="margin-top: -20px">
              {{shitu.beizhu}}
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
        label: '是否回款',
        options: [{
          value: '已回',
          label: '已回'
        }, {
          value: '未回',
          label: '未回'
        }]
      },
        {
          label: '是否已开发票',
          options: [{
            value: '已开',
            label: '已开'
          }, {
            value: '未开',
            label: '未开'
          }]
        }],
      value: '全部数据',
      content:'',
      relieve:false,
      tableData: [],
      plan:{
        plaId:0,//回款编号
        customerName:'',//客户名称
        odrId:'',//订单编号
        payment:'',//付款方式
        principal:'',//经办人
        plaPrice:'',//回款金额
        plaTime:'',//回款日期
        plaWhether:'已回',//是否回款
        invoice:'已开',//是否开发票
        beizhu:''
      },
      //期次下拉框
      option:[],
      //第几期
      opstion:[],
      order:[],
      shitu:[],
      lei: [
        {
          value: '老顾客付款',
          label: '老顾客付款',
        },
        {
          value: '新顾客付款',
          label: '新顾客付款',
        },

      ],
    }
  },
  methods:{
    getData(){
      this.axios.get("/plan/planlist").then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].plaTime)
          const date = new Date(this.tableData[i].plaTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].plaTime=y + '-' + MM + '-' + d
        }
        console.log(this.tableData)
      }).catch()
      this.axios.get('/issue/issuelist').then((v)=>{
        this.option=v.data.data
      }).catch()
      this.axios.get("/returned/principal").then((v)=>{
        this.opstion=v.data
      }).catch()
      this.axios.get("/returned/oder").then((v)=>{

        this.order=v.data
        console.log(this.order)

      }).catch()
    },
    dome(){
      if(this.value=="全部数据"){
        this.getData()
      }else{
        this.axios({
          url:"/plan/state",
          params:{value:this.value}
        }).then((res)=>{
          this.tableData=res.data.data
          for (let i = 0; i < this.tableData.length ; i++) {
            console.log(this.tableData[i].retTime)
            const date = new Date(this.tableData[i].retTime)
            const y = date.getFullYear()// 年
            let MM = date.getMonth() + 1 // 月
            MM = MM < 10 ? ('0' + MM) : MM
            let d = date.getDate() // 日
            d = d < 10 ? ('0' + d) : d
            this.tableData[i].retTime=y + '-' + MM + '-' + d
          }
        }).catch()
        console.log(this.value,"1111")
        this.relieve=true
      }
    },
    cs(){
      console.log(this.content,"222")
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
      this.axios.post("/plan/addplan",this.plan).then((v)=>{
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
      this.plan.plaId=row.plaId
      this.plan.odrId=row.orderNumber
      this.plan.customerName=row.customerName
      this.plan.plaTime=row.plaTime
      this.plan.plaPrice=row.plaPrice
      // this.plan.isuId=row.isuId
      this.plan.plaWhether=row.plaWhether
      this.plan.invoice=row.invoice
      this.plan.beizhu=row.beizhu
      this.plan.payment=row.payment
    },
    deletes(plaId){
      console.log(plaId)
      // this.axios.get('/returned/deleteid',reId).then().catch()
      this.axios({
        url:"/plan/deletid",
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
