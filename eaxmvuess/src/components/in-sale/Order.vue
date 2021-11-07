<template>

  <!--新增订单弹框-->
  <el-dialog width="55%" title="新建订单信息" @close="closeOrder" v-model="isShowOrder">
      <el-form ref="orderRef" :rules="orderRules" :model="orderObj"  >
        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item prop="odrName" label="订单主题：" label-width="120px" >
              <el-input size="medium" v-model="orderObj.odrName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="customerName" label="对应客户：" label-width="120px" >
              <el-input size="medium" disabled v-model="orderObj.customerName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="10">
            <el-form-item prop="medium" label="查找客户：" label-width="120px" >
              <el-select @change="clientObjChange" filterable
                  v-model="clientObj"
                  clearable value-key="clientId"
                  placeholder="客户名称"
              >
                <el-option
                    v-for="cli in clientArr"
                    :key="cli.clientId"
                    :label="cli.clientName"
                    :value="cli"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="name" label="订单号：" label-width="120px" >
              <el-input size="medium" disabled v-model="orderObj.odrOn"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="odrUsId" label="我方签约人：" label-width="120px" >
              <el-select v-model="orderObj.odrUsId" @change="changeOdrUs" placeholder="请选择">
                <el-option v-for="ar in empArr"
                           :label="ar.empName"
                           :value="ar.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="odrOutDate" label="最晚发货日期:" label-width="120px" >
              <el-date-picker  v-model="orderObj.odrOutDate"
                              type="date"
                              size="small"
                              value-format="YYYY-MM-DD"
                              placeholder="日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="medium" label="执行状态：" label-width="120px" >
              <el-radio-group v-model="orderObj.odrState" >
                <el-radio :label="1">执行</el-radio>
                <el-radio :label="2">结束</el-radio>
                <el-radio :label="3">意外终止</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>


        <el-divider>
          地址信息
        </el-divider>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="odrAddressee" label="收货人姓名：" label-width="120px" >
              <el-input size="medium" v-model="orderObj.odrAddressee"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="odrAddresseePhone" label="收货人电话：" label-width="120px" >
              <el-input size="medium" v-model="orderObj.odrAddresseePhone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
        <el-col :offset="1" :span="22">
          <el-form-item prop="odrProvince" label="收货地址：" label-width="120px" >
            <el-cascader style="width: 100%;" size="large"  :options="citys" v-model="selectedOptions"
                         @change="cityChange" >
            </el-cascader>
          </el-form-item>
        </el-col>
      </el-row>

        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item prop="odrCity" label="详细地址：" label-width="120px" >
              <el-input v-model="orderObj.odrCity" property="详情地址"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item label="备注：" label-width="120px" >
              <el-input type="textarea" v-model="orderObj.odrRemark" property="订单备注"/>
            </el-form-item>
          </el-col>
        </el-row>



      </el-form>
      <template #footer>
        <el-row>
          <el-col :span="18"></el-col>
          <el-col :span="2">
            <el-button @click="addOrder()" type="primary">确定</el-button>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="2">
            <el-button @click="" type="danger">取消</el-button>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

      </template>

    </el-dialog>

  <el-row style="padding: 10px 0px">
    <el-col :offset="12" :span="4"><span style="font-weight: 900;font-size: 18px">订单管理</span></el-col>
  </el-row>

  <el-row style="padding: 15px 0px">

    <el-col :offset="2" :span="4">
      <el-input @blur="initChangeBedRecord" size="mini" v-model="SelectWhere.searchLike"  placeholder="根据客户名称或者负责人搜索"></el-input>
    </el-col>
    <el-col :span="1" >
      <el-button  size="mini" @click="initOrder"  type="primary" >搜索</el-button>
    </el-col>


    <el-col :offset="1"  :span="10">
      &nbsp;<span style="font-size: 12px;">最晚发货日期：</span>&nbsp;

      <el-date-picker @change="initOrder" style="width: 160px"
                      type="date"
                      size="mini"
                      value-format="YYYY-MM-DD"
                      v-model="SelectWhere.startDate"
                      placeholder="日期">
      </el-date-picker>
      &nbsp;<span style="font-size: 12px;">至</span>&nbsp;
      <el-date-picker @change="initChangeBedRecord" style="width: 160px"
                      type="date"
                      size="mini"
                      value-format="YYYY-MM-DD"
                      v-model="SelectWhere.endDate"
                      placeholder="日期">
      </el-date-picker>
    </el-col>

    <el-col :offset="4" :span="2">
        <el-button @click="openOrder" size="mini" >创建订单</el-button>
    </el-col>
  </el-row>




  <!--查看病历表格-->
  <el-row>
    <el-col>
      <el-table :data="orderArr.slice((orderCurrent-1)*orderSize,orderCurrent*orderSize)" height="460" size="mini">
        <el-table-column prop="odrOn" width="100px"  label="订单号" />
        <el-table-column prop="odrName" label="订单主题" />
        <el-table-column prop="odrCount" label="产品数量" />
        <el-table-column prop="customerName" label="对应客户" />
        <el-table-column prop="odrPrice" label="订单总额" />
        <el-table-column prop="odrOutPrice"  label="发货金额" />
        <el-table-column prop="odrReturnPrice" label="回款金额"/> >
        <el-table-column label="订单状态" >
          <template #default="obj">
            {{obj.row.odrState == 1 ? '执行中' : obj.row.odrState == 2 ? '结束' : '意外终止'}}
          </template>
        </el-table-column>
        <el-table-column prop="odrShipmentsState" label="发货状态" />
        <el-table-column prop="emp.empName" label="所有者" />
        <el-table-column prop="odrDate" width="150px" label="生成时间" />
        <el-table-column prop="odrOutDate" width="100px" label="最晚发货日期" />
        <el-table-column width="150px" label="操作">
          <template #default="obj">
            <el-button size="mini" @click="addProduct(obj.row)" type="text">添加产品</el-button>
            <el-button type="text" size="mini">取消订单</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页插件-->
      <el-pagination
          style="text-align: center;"
          @size-change="orderSizeChange"
          @current-change="orderCurrentChange"
          :current-page="orderCurrent"
          :page-sizes="[2,4,6,8,10]"
          :page-size="orderSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="orderArr.length">
      </el-pagination>
    </el-col>
  </el-row>
</template>
<script>

import {
  regionData,
  CodeToText,
  TextToCode
} from 'element-china-area-data'
export default {

  data() {
    return {
      orderRules: {//非空校验
        odrName:[{required:true,message:"主题不能为空",trigger:'change'}],//主题名称
        customerName:[{required:true,message:"客户名称不能空！",trigger:'change'}],//对应客户名称
        odrOutDate:[{required:true,message:"请选择最晚发货日期",trigger:'change'}],//订单最晚发货时间
        odrAddressee:[{required:true,message:"收件人姓名不能为空",trigger:'change'}],//收件人姓名
        odrAddresseePhone:[{required:true,message:"收货人电话不能为空",trigger:'change'}],//收件人电话
        odrProvince:[{required:true,message:"地址不能为空",trigger:'change'}],//省区
        odrCity:[{required:true,message:"详细地址不能为空",trigger:'change'}],//城市
      },

      //======================查询条件数据
      SelectWhere:{
        startDate:'',//开始日期
        endDate:'',//结束日期
        searchLike:'',//模糊搜索
        doctorType:2,//类型
      },

      //显示添加订单弹框
      isShowOrder:false,

      //订单实体类
      orderObj:{
        odrId:'',//订单编号
        odrOn:'',//订单号
        odrName:'',//主题名称
        odrPrice:'',//订单总额
        odrOutPrice:'',//订单发货总额
        customerName:'',//对应客户名称
        chanceName:'',//机会名称
        odrDate:'',//订单生成日期
        odrOutDate:'',//订单最晚发货时间
        odrAddressee:'',//收件人姓名
        odrAddresseeTelephone:'',//收件人电话
        odrAddresseePhone:'',//收件人移动电话
        odrProvince:'',//省区
        odrShipmentsState:'',//订单发货状态
        odrCity:'',//城市
        odrAddressType:'',//地址类型
        odrPostcode:'',//邮编
        odrState:1,//状态(实行中、结束、意外终止)
        odrFreight:'',//运费
        odrRemark:'',//订单备注
        clientId:'',//外连接 连接客户表
        opId:'',//外连接  连接会机编号
        linkmanId:'',//外连接 连接联系人编号
        odrUsId:'',//订单签约人
      },
      clientArr:[],//客户数组
      clientObj:{},//客户对象
      clientName:'',//查询客户名称

      orderArr:[],//订单数组
      addtions: {},//地址
      citys:regionData,
      selectedOptions: ["", "", ""],

      empArr:[],//员工集合


      //分页
      orderCurrent:1,//住院登记分页当前页
      orderSize:8,//住院登记分页页大小
    }
  },
  methods: {
    //初始化
    initOrder(){
      this.axios.post("/orderFrom/selectOrderByTj",this.SelectWhere).then((v)=>{
          console.log(v.data.data)
          this.orderArr = v.data.data;
      })
    },
    addProduct(obj){
        sessionStorage.setItem('oederObj',JSON.stringify(obj))
        // sessionStorage.setItem('odrOn',obj);
        this.$router.push('/addProduct');
    },

    //打开添加订单弹框
    openOrder(){
      this.isShowOrder = true;
      this.orderObj.odrOn = Math.round(Math.random()*99999999999);//随机生成数
      this.clientArrFun();
      this.empArrFun();
    },
    //关闭添加订单弹框
    closeOrder(){
      this.isShowOrder = false;
      this.selectedOptions =  ["", "", ""];
      this.orderObj = {
        odrId:'',//订单编号
            odrOn:'',//订单号
            odrName:'',//主题名称
            odrPrice:'',//订单总额
            odrOutPrice:'',//订单发货总额
            customerName:'',//对应客户名称
            chanceName:'',//机会名称
            odrDate:'',//订单生成日期
            odrOutDate:'',//订单最晚发货时间
            odrAddressee:'',//收件人姓名
            odrAddresseeTelephone:'',//收件人电话
            odrAddresseePhone:'',//收件人移动电话
            odrProvince:'',//省区
            odrShipmentsState:'',//订单发货状态
            odrCity:'',//城市
            odrAddressType:'',//地址类型
            odrPostcode:'',//邮编
            odrState:1,//状态(实行中、结束、意外终止)
            odrFreight:'',//运费
            odrRemark:'',//订单备注
            clientId:'',//外连接 连接客户表
            opId:'',//外连接  连接会机编号
            linkmanId:'',//外连接 连接联系人编号
            odrUsId:'',//订单签约人
      };
    },
    //添加订单弹框
    addOrder(){
      this.$refs['orderRef'].validate((valid) => {
        if(valid){
          this.axios.post("/orderFrom/addOrder",this.orderObj).then((v)=>{
            if(v.data){
              this.closeOrder();//关闭弹框
              this.initOrder();
            }
          })
        }
      });
    },

    cityChange(placedz) { //地址联动
      console.log("传过来的value:", placedz);
      //我们选择地址后，selectedOptions 会保存对应的区域码，例如北京的区域码为'110000'
      //我们要取出区域码对应的汉字，就需要用CodeToText(区域码)进行输出
      this.addtions.selectedcity = placedz;
      var homeaddress = '';
      // empmess.selectedcity
      this.selectedOptions.map(item => homeaddress += CodeToText[item]+' ');
      this.orderObj.odrProvince =homeaddress;
      console.log(this.orderObj)
      // if(homeaddress && homeaddress!=''){
      //   this.empmess.homeaddress=homeaddress.substring(0, homeaddress.length - 1)
      // }
    },

    clientArrFun(){
      // this.orderObj.clientId = '';
      this.axios({url:'orderFrom/selectByCliName',params:{name:''}}).then((v)=>{
        console.log(v.data)
        this.clientArr = v.data.data;
      })
    },

    //客户改变是调用
    clientObjChange(){
        console.log(this.clientObj)
        this.orderObj.customerName = this.clientObj.clientName;
    },

    empArrFun(){
      this.axios({url:'/emp/selectEmpAll'}).then((v)=>{
        this.empArr = v.data.data;
      });
    },



    //========================分页方法
    // 住院登记size变了调用
    orderSizeChange: function(size) {
      this.orderSize = size;
    },
    //住院登记Current变了调用
    orderCurrentChange: function(currentPage) {
      this.orderCurrent = currentPage;
    },
  },


  created() {
    this.initOrder();//初始化
  }
}
</script>

<style scoped>

</style>
