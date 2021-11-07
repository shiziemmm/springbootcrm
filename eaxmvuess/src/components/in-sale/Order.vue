<template>

  <!--新增订单弹框-->
  <!--=============================================选择住院申请病人弹框===================================-->
  <el-dialog width="65%" title="新建订单信息" @close="closeHspApply" v-model="isShowOrder">
      <el-form :model="baseInfo"  >
        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item prop="name" label="订单主题：" label-width="100px" >
              <el-input size="medium" v-model="orderObj.odrName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="name" label="对应客户：" label-width="100px" >
              <el-input size="medium" disabled v-model="baseInfo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="9">
            <el-form-item prop="medium" label="查找客户：" label-width="100px" >
<!--              <el-select @click="clientArrFun"-->
<!--                  v-model="orderObj.clientId"-->
<!--                  filterable-->
<!--                  remote-->
<!--                  placeholder="客户名称"-->
<!--                  :remote-method="clientArrFun"-->
<!--                  :loading="loading"-->
<!--              >-->
<!--                <el-option-->
<!--                    v-for="cli in clientArr"-->
<!--                    :key="cli.clientId"-->
<!--                    :label="cli.clientName"-->
<!--                    :value="cli.clientId"-->
<!--                >-->
<!--                </el-option>-->
<!--              </el-select>-->

              <el-autocomplete
                  v-model="orderObj.clientId"
                  :fetch-suggestions="clientArrFun"
                  placeholder="客户名称"
                  @select="clientArrFun"
              />
            </el-form-item>
          </el-col>
          <el-col :span="1" >
            <el-button  size="medium" @click="clientArrFun"  type="primary" >搜索</el-button>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="name" label="订单号：" label-width="100px" >
              <el-input size="medium" disabled v-model="orderObj.odrOn"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="medium" label="我方签约人：" label-width="100px" >
              <el-select v-model="orderObj.odrUsId" @change="changeOdrUs" placeholder="请选择">
                <el-option v-for="ks in ksArr"
                           :label="ks.ksName"
                           :value="ks.ksId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="10">
            <el-form-item prop="name" label="最晚发货日期：" label-width="110px" >
              <el-input size="medium" v-model="baseInfo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="medium" label="执行状态：" label-width="100px" >
              <el-radio-group v-model="orderObj.odrState"  @change="selectPharmacyDoctorKs">
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
            <el-form-item prop="name" label="收货人姓名：" label-width="100px" >
              <el-input size="medium" v-model="orderObj.odrAddressee"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="11">
            <el-form-item prop="name" label="收货人电话：" label-width="100px" >
              <el-input size="medium" v-model="orderObj.odrAddresseePhone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
        <el-col :offset="1" :span="22">
          <el-form-item prop="name" label="收货地址：" label-width="100px" >
            <el-cascader style="width: 100%;" size="large"  :options="citys" v-model="selectedOptions"
                         @change="cityChange" >
            </el-cascader>
          </el-form-item>
        </el-col>
      </el-row>

        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item prop="name" label="详细地址：" label-width="100px" >
              <el-input v-model="orderObj.odrCity" property="详情地址"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :offset="1" :span="22">
            <el-form-item prop="name" label="备注：" label-width="100px" >
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

  <el-row style="padding: 15px 0px">

    <el-col :offset="4" :span="4">
      <el-input @blur="initChangeBedRecord" size="mini"  placeholder="根据客户名称或者负责人搜索"></el-input>
    </el-col>
    <el-col :span="1" >
      <el-button  size="mini"   type="primary" >搜索</el-button>
    </el-col>


    <el-col :offset="1"  :span="8">
      &nbsp;<span style="font-size: 12px;">操作日期：</span>&nbsp;

      <el-date-picker @change="initChangeBedRecord" style="width: 130px"
                      type="date"
                      size="mini"
                      placeholder="日期">
      </el-date-picker>
      &nbsp;<span style="font-size: 12px;">至</span>&nbsp;
      <el-date-picker @change="initChangeBedRecord" style="width: 130px"
                      type="date"
                      size="mini"
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
      <el-table :data="orderArr" height="460" size="mini">
        <el-table-column prop="odrOn" label="订单号" />
        <el-table-column prop="odrName" label="订单主题" />
        <el-table-column prop="odrCount" label="产品数量" />
        <el-table-column prop="customerName" label="对应客户" />
        <el-table-column prop="odrPrice" label="订单总额" />
        <el-table-column prop="odrOutPrice" label="发货金额" />
        <el-table-column prop="hk" label="回款金额" />
        <el-table-column prop="odrState" label="订单状态" />
        <el-table-column prop="odrShipmentsState" label="发货状态" />
        <el-table-column prop="empName" label="所有者" />
        <el-table-column prop="odrDate" label="生成时间" />
        <el-table-column prop="odrOutDate" label="最晚发货日期" />
      </el-table>
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
        odrState:'',//状态(实行中、结束、意外终止)
        odrFreight:'',//运费
        odrRemark:'',//订单备注
        clientId:'',//外连接 连接客户表
        opId:'',//外连接  连接会机编号
        linkmanId:'',//外连接 连接联系人编号
        odrUsId:'',//订单签约人
      },
      clientArr:[],//客户数组
      clientName:'',//查询客户名称

      orderArr:[],//订单数组
      addtions: {},//地址
      citys:regionData,
      selectedOptions: ["", "", ""],
    }
  },
  methods: {
    //打开添加订单弹框
    openOrder(){
      this.isShowOrder = true;
      this.orderObj.odrOn = Math.round(Math.random()*99999999999);//随机生成数
    },
    //关闭添加订单弹框
    closeOrder(){
      this.isShowOrder = false;
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
            odrState:'',//状态(实行中、结束、意外终止)
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
      this.axios.post("/orderFrom/addOrder",this.orderObj).then((v)=>{
        if(v.data){
          this.closeOrder();//关闭弹框
        }
      })
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

    clientArrFun(str,cb){
      console.log(str);
      // this.orderObj.clientId = '';
      this.axios({url:'orderFrom/selectByCliName',params:{name:str}}).then((v)=>{
        console.log(v.data)
        // this.clientArr = v.data;

      })
      cb(["ss","sasfdas"]);
    }
  },


  created() {
  }
}
</script>

<style scoped>

</style>
