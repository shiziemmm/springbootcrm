<template>

  <div class="div-class">

    <div class="div-one">

      <el-row>
        <el-col style="margin-left:30px;font-size: 20px;height: 70px;line-height: 70px" :offset="1" :span="2">订单信息</el-col>
      </el-row>

      <el-divider>
        详情
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
          执行状态：{{orderObj.odrState == 1 ? '执行中' : orderObj.odrState == 2 ? '结束' : '意外终止'}}
        </el-col>
        <el-col style="margin-left: 250px" :span="6">
          发货状态：{{orderObj.odrShipmentsState}}
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


      <el-row>
        <el-col :span="6" style="margin-top: 40px;margin-bottom: 40px;margin-left: 500px">
          <el-button  type="primary" @click="addCk" :disabled="orderObj.odrCount == 0" v-if="orderObj.odrState == 1 && orderObj.odrShipmentsState == '未发货'" size="medium">点击生成出库单</el-button> <el-button type="danger" @click="returnHome" size="medium">返回</el-button>
        </el-col>
      </el-row>

    </div>

  </div>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      orderObj:{},
      odrOn:sessionStorage.getItem("odrOn"),//订单号

    }
  },
  methods:{
    initOrderParticulars(){
        this.axios({url:"orderFrom/selectOrderByOdrId",params:{odrOn:this.odrOn}}).then((v)=>{
          console.log(v.data.obj);
         this.orderObj = v.data.obj;
        })
    },
    addProduct(obj){
      sessionStorage.setItem('orderObj',JSON.stringify(this.orderObj));
      // sessionStorage.setItem('odrOn',obj);
      this.$router.push('/addProduct');
    },

    //返回主页
    returnHome(){
      this.$router.push('/order');
      sessionStorage.clear("odrOn")//清空订单号
    },

    //出库方法
    addCk(){
      this.axios.post("orderFromDetail/addwWrehouseLeave",{"orderFromDetail":this.orderObj.orderFromDetail,"odrId":this.orderObj.odrId}).then((v)=>{
        ElMessage.success({
          message: "出库成功",
          type: 'success'
        });
        this.$router.push('/order');
        sessionStorage.clear("odrOn");//清空订单号
      })
    }

  },
  created() {
    // this.orderObj = JSON.parse(sessionStorage.getItem('orderObj'));//订单对象
    this.initOrderParticulars();

  }
}
</script>

<style scoped>
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
