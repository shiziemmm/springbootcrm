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
      <el-input placeholder="请输入经手人" style="width: 150px;margin-left: 100px" v-model="content"></el-input>
      <el-button class="el-button el-button--default is-circle"  @click="cs()">
        <!--        <i class="el-icon-search"></i>-->
        查找
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
        <span style="font-size: 12px">开票信息 共{{tableData.length}}条</span>
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
            show-summary
            :header-cell-style="{textAlign: 'center'}"
            :cell-style="{ textAlign: 'center' }"
            style="width: 100%">
          <el-table-column
              prop="orderNumber"
              header-align="center"
              label="订单编号">
          </el-table-column>
          <el-table-column
              prop="invSum"
              label="开票金额">
          </el-table-column>
          <el-table-column
              prop="customerName"
              label="客户名称">
          </el-table-column>
          <el-table-column
              prop="principal"
              label="经手人">
          </el-table-column>

          <el-table-column
              prop="invTime"
              label="日期">
          </el-table-column>
          <el-table-column
              prop="invWhether"
              label="是否回款">
          </el-table-column>
          <el-table-column
              prop="invType"
              label="票据类型">
          </el-table-column>

          <el-table-column label="操作" width="220px">
            <template v-slot:default="r">
              <!--        <el-button type="danger" @click="open(r.row)">删除</el-button>-->
              <el-button type="primary" size="small" @click="opens(r.row)">视图</el-button>
              <el-button type="primary" size="small" @click="edit(r.row)">编辑</el-button>
              <el-button type="primary" size="small" @click="deletes(r.row.invId)">删除</el-button>
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
        title="新建开票信息"
        v-model="dialogVisible"
        width="60%"
    >
      <el-divider></el-divider>
      <el-form>
        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item  label="订单：" style="width: 200px" >
              <el-select v-model="plan.orderNumber" placeholder="请选择" @change="rets()">
                <el-option
                    v-for="item in order"
                    :label="item.odrId"
                    :value="item.odrId"
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
            <el-form-item  label="开票日期：" label-width="100px" >
              <el-date-picker v-model="plan.invTime" type="date" placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="10">
            <el-form-item label="金额：" label-width="110px" >
              <el-input size="medium" disabled v-model="plan.invSum"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="10">
            <el-form-item  label="开票类型：" label-width="100px" style="width: 60%" >
              <el-select v-model="plan.invType" placeholder="请选择" style="width: 200px" >
                <el-option
                    v-for="item in lei"
                    :key="item.value"
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
              <el-radio-group v-model="plan.invWhether"  >
                <el-radio label="已回">已回</el-radio>
                <el-radio label="未回">未回</el-radio>
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
              <el-input type="textarea" v-model="plan.invRemarkinvRemark" property="订单备注"/>
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
          {{shitu.orderNumber}}
        </el-form-item>
        <el-form-item label="客户：" style="margin-top: -20px">
          {{shitu.customerName}}
        </el-form-item>
        <el-row>
          <el-col span="8">
            <el-form-item label="开票日期：" style="margin-top: -20px">
              {{shitu.invTime}}
            </el-form-item>
          </el-col>
                    <el-col span="8" :offset="11">
                      <el-form-item label="票据类型：" style="margin-top: -20px">
                        {{shitu.invType}}
                      </el-form-item>
                    </el-col>
        </el-row>
        <el-row>
          <el-col span="8">
            <el-form-item label="金额：" style="margin-top: -20px">
              {{shitu.invSum}}
            </el-form-item>
          </el-col>
          <el-col span="8" :offset="13">
            <el-form-item label="是否已回款：" style="margin-top: -20px">
              {{shitu.invWhether}}
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
              {{shitu.invRemarkinvRemark}}
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
        label: '票据类型',
        options: [{
          value: '增值税',
          label: '增值税'
        }, {
          value: '普通国税',
          label: '普通国税'
        },
          {
            value: '普通地税',
            label: '普通地税'
          },
          {
            value: '收据',
            label: '收据'
          }]
      },
       ],
      value: '全部数据',
      content:'',
      relieve:false,
      tableData: [],
      page:1,
      size:4,
      plan:{
        invId:0,//回款编号
        customerName:'',//客户名称
        orderNumber:'',//订单编号
        invType:'',//付款方式
        principal:'',//经办人
        invSum:'',//回款金额
        invTime:'',//回款日期
        invWhether:'已回',//是否回款
        invRemarkinvRemark:''
      },
      //期次下拉框
      option:[],
      //第几期
      opstion:[],
      order:[],
      shitu:[],
      lei: [
        {
          value: '增值税',
          label: '增值税',
        },
        {
          value: '普通国税',
          label: '普通国税',
        },
        {
          value: '普通地税',
          label: '普通地税',
        },
        {
          value: '收据',
          label: '收据',
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
      this.axios.get("/invoice/select").then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].invTime)
          const date = new Date(this.tableData[i].invTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].invTime=y + '-' + MM + '-' + d
        }
        console.log(this.tableData)
      }).catch()
      this.axios.get('/issue/issueList').then((v)=>{
        this.option=v.data.data
      }).catch()
      this.axios.get("/returned/principal").then((v)=>{
        this.opstion=v.data
      }).catch()
      this.axios.get("/plan/planList").then((v)=>{
        this.order=v.data.data
        console.log(this.order,"11111")

      }).catch()
    },
    dome(){
      if(this.value=="全部数据"){
        this.getData()
      }else{
        this.axios({
          url:"/invoice/selectType",
          params:{value:this.value}
        }).then((res)=>{
          this.tableData=res.data.data
          for (let i = 0; i < this.tableData.length ; i++) {
            console.log(this.tableData[i].invTime)
            const date = new Date(this.tableData[i].invTime)
            const y = date.getFullYear()// 年
            let MM = date.getMonth() + 1 // 月
            MM = MM < 10 ? ('0' + MM) : MM
            let d = date.getDate() // 日
            d = d < 10 ? ('0' + d) : d
            this.tableData[i].invTime=y + '-' + MM + '-' + d
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
        url:"/invoice/likeName",
        params:{name:this.content}
      }).then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].invTime)
          const date = new Date(this.tableData[i].invTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          this.tableData[i].invTime=y + '-' + MM + '-' + d
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
      if(this.plan.orderNumber!=null || this.plan.orderNumber!=undefined){
        for (let i = 0; i < this.order.length; i++) {
          if(this.plan.orderNumber == this.order[i].odrId){
            this.plan.customerName=this.order[i].customerName
            this.plan.invSum=this.order[i].plaPrice
          }
        }
      }
    },
    onSubmit(formName){
      console.log(this.plan)
      this.axios.post("/invoice/addInvo",this.plan).then((v)=>{
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
      this.plan.invId=row.invId
      this.plan.orderNumber=row.orderNumber
      this.plan.customerName=row.customerName
      this.plan.invTime=row.invTime
      this.plan.invType=row.invType
      // this.plan.isuId=row.isuId
      this.plan.invSum=row.invSum
      this.plan.invWhether=row.invWhether
      this.plan.principal=row.principal
      this.plan.invRemarkinvRemark=row.invRemarkinvRemark
    },
    deletes(plaId){
      console.log(plaId)
      // this.axios.get('/returned/deleteid',reId).then().catch()
      this.axios({
        url:"/invoice/deleteId",
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
