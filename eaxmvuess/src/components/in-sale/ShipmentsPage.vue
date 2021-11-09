<template>

  <div class="div-class" v-if="odrOn != undefined">

    <div class="div-one">

      <el-row>
        <el-col style="margin-left:30px;font-size: 20px;height: 60px;line-height: 60px" :offset="1" :span="2">发货单</el-col>
      </el-row>

      <el-divider>
        信息
      </el-divider>

      <el-row style="margin-top: 50px; ">
        <el-col style="margin-left: 200px" :span="6">
          主题：{{orderObj.odrName}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          对应客户：{{orderObj.customerName}}
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          订单号：{{orderObj.odrOn}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          对应报价：{{orderObj.quotation == null ? '无': orderObj.quotation.quTheme}}
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          总金额：{{orderObj.odrPrice}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          产品数量：{{orderObj.odrCount}}
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          创建订单时间：{{orderObj.odrDate}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          最晚发货时间：{{orderObj.odrOutDate}}
        </el-col>
      </el-row>


      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          收货人名称：{{orderObj.odrCity}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          收货人电话：{{orderObj.odrAddresseePhone}}
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          收货人地址：{{orderObj.odrProvince}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          收货人详情地址：{{orderObj.odrCity}}
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="18">
          订单备注：{{orderObj.odrRemark}}
        </el-col>
      </el-row>

      <el-divider style="margin: 50px 0px">
        产品信息
      </el-divider>


      <el-row>
        <el-col style="margin-top: 40px;margin-bottom: 10px;margin-left: 1200px" :span="2">
          <el-button  v-if="orderObj.odrState == 1 && orderObj.odrShipmentsState == '未发货'" @click.stop="addProduct()"  type="primary" size="mini">编辑明细</el-button>
        </el-col>
      </el-row>
      <el-table
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{ textAlign: 'center' }"
          :data="orderObj.orderFromDetail" size="medium">
        <el-table-column prop="odrdlProductName"  label="商品名" />
        <el-table-column prop="odrdlMoney"   label="进价" />
        <el-table-column prop="odrdlUnitPrice"   label="售价" >
        </el-table-column>
        <el-table-column prop="odrdlCount"   label="数量" >
        </el-table-column>
        <el-table-column prop="odrdlRemark"  label="备注" >
        </el-table-column>
      </el-table>

      <el-row>
        <el-col :span="3" style="margin-top: 40px;margin-bottom: 10px;margin-left: 1100px">
          合计：{{orderObj.odrPrice}}
        </el-col>
      </el-row>

      <el-divider style="margin: 50px 0px">
        发货信息
      </el-divider>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
          发货单号：<el-input disabled  style="width: 230px" v-model="shipmentObj.sptOn" placeholder="物流单号"/>
        </el-col>


        <el-col style="margin-left: 250px" :span="6">
          物流公司：
          <el-select style="width: 230px" placeholder="物流公司" v-model="shipmentObj.sptLogisticsCompany">
            <el-option value="顺丰快递"></el-option>
            <el-option value="圆通快递"></el-option>
            <el-option value="申通快递"></el-option>
            <el-option value="邮政快递"></el-option>
            <el-option value="中通快递"></el-option>
          </el-select>
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col style="margin-left: 200px" :span="6">
           &nbsp;&nbsp;&nbsp;&nbsp; 运费 &nbsp;：<el-input style="width: 230px" v-model="shipmentObj.sptSfPrice" onkeyup="value=value.replace(/[^\d^\.]+/g,'').replace('.','$#$').replace(/\./g,'').replace('$#$','.')" placeholder="运费" />
        </el-col>

        <el-col style="margin-left: 250px" :span="6">
          物流单号：<el-input style="width: 230px" v-model="shipmentObj.sptLogistics" placeholder="物流单号"/>
        </el-col>
      </el-row>



      <el-row>
        <el-col :span="6" style="margin-top: 40px;margin-bottom: 40px;margin-left: 600px">
          <el-button type="primary" @click="addShipment" size="medium">发货</el-button>
        </el-col>


      </el-row>



    </div>

  </div>

  <div class="div-class" style="position: absolute;width: 100%;height: 100%" v-if="odrOn == undefined">
    错误！！！！！！！！
    <el-button @click="this.$router.push('/order');">返回</el-button>
  </div>


</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: 'shipments',
  data() {
    return{
      orderObj:{},
      odrOn:sessionStorage.getItem("odrOn"),//订单号
      shipmentObj:{
        odrOn:'',//订单号
        sptOn:'',//发货单号
        sptLogisticsCompany:'',//物流公司
        sptLogistics:'',//物流编号
        clientId:'',//客户编号
        sptSfPrice:'',//运费
      },
    }
  },
  methods: {
    initOrderShipment(){
      this.axios({url:"orderFrom/selectOrderByOdrId",params:{odrOn:this.odrOn}}).then((v)=>{
        console.log(v.data.obj);
        this.orderObj = v.data.obj;
      })
    },
    addShipment(){
      this.shipmentObj.clientId = this.orderObj.clientId;//客户编号
      this.shipmentObj.odrOn = this.orderObj.odrOn;//订单号

      this.axios.post("shipmentsFrom/addShipment",this.shipmentObj).then((v)=>{
        ElMessage.success({
          message: "发货成功！",
          type: 'success'
        });
        sessionStorage.clear("odrOn")//清空订单号
        this.$router.push('/order');
      })
    }
  },
  created(){
    this.shipmentObj.sptOn = "fh"+Math.round(Math.random()*99999999999);//随机生成数
    this.initOrderShipment();
  }
}
</script>

<style>
*{
  margin: 0px;
}
.div-class{
  /*padding: 8px;*/
  background: #DDDDDD;
  padding-left: 120px;
  padding-top: 20px;
  padding-bottom:20px ;
}
.div-one{
  font-size: 14px;
  background-color: white;
  width: 1300px;
}
</style>
