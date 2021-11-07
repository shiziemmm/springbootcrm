<template>
  <div class="div-class" v-if="orderObj != undefined">
    <el-row style="padding: 8px">
      <el-col style="font-size: 20px;" :span="2">添加商品 <span ></span></el-col>
      <el-col :offset="18" :span="4" style="font-size: 20px;">订单号：<sapn style="font-size: 20px;color: red;"></sapn>{{odrOn}}</el-col>
    </el-row>

      <el-row style="padding: 8px">
        <el-col>
          <el-table
              :header-cell-style="{textAlign: 'center'}"
              :cell-style="{ textAlign: 'center' }"
              :data="orderDetailsArr" size="medium">
            <el-table-column prop="odrdlProductName"  label="商品名" />
            <el-table-column prop="odrdlUnitPrice"   label="进价" />
            <el-table-column prop="odrdlUnitPrice"   label="售价" >
              <template #default="obj">
                <el-input size="mini" v-model="obj.row.odrdlUnitPrice" onkeyup="value=value.replace(/[^\d^\.]+/g,'').replace('.','$#$').replace(/\./g,'').replace('$#$','.')"  placeholder="售价" type="text" />
              </template>
            </el-table-column>
            <el-table-column prop="odrdlCount"   label="数量" >
              <template #default="obj">
                <el-input-number size="mini" v-model="obj.row.odrdlCount" onkeyup = "value=value.replace(/[^\d]/g,'')" placeholder="产品数量" type="text" />
              </template>
            </el-table-column>
            <el-table-column prop="odrdlRemark"  label="备注" >
              <template #default="obj">
                <el-input size="mini" v-model="obj.row.odrdlRemark" placeholder="备注" type="textarea" />
              </template>
            </el-table-column>
            <el-table-column label="操作" style="text-align: center;">
              <template #default="obj">
                <el-button size="mini" @click="deleteProduct(obj)" type="danger">移除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    <el-row>
      <el-col :offset="11"  :span="2">
        <el-button  @click="addProductDetails"  size="mini" :disabled="orderDetailsArr.length == 0" type="primary">保存明细数据</el-button>
      </el-col>
      <el-col :offset="9" :span="2">
        <el-button circle @click="isShowAddOrder = true" size="mini" style="font-size: 60px;width: 60px;height: 60px;line-height: 38px;" type="primary">+</el-button>
      </el-col>
    </el-row>
    <!--添加商品抽屉-->
    <el-drawer v-model="isShowAddOrder"  :with-header="false">

      <p style="color: black;font-size: 16px; margin-bottom:15px">添加产品</p>

      <!--搜索按钮-->
      <el-row>
        <el-col>
          <el-input
              placeholder="根据产品名称以及类型搜索"
              v-model="selectParams.keyword"
              class="input-with-select">
            <template #append>
              <el-button @click="initProduct">搜索</el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>

      <!--显示商品表格-->
      <el-row>
        <el-col>
          <el-table :data="productArr" @cell-dblclick="addProductFun" size="mini">
            <el-table-column prop="prName"  label="商品名" />
            <el-table-column prop="prCostPrice"   label="进价" />
            <el-table-column prop="prPrice"   label="售价" />
            <el-table-column prop="prDodel"   label="类型" />
            <el-table-column prop="prUnit"  label="单位" />
          </el-table>
        </el-col>
      </el-row>


    </el-drawer>


  </div>


</template>

<script>
import {ElMessage} from "element-plus";

export default {
  data(){
    return{
      orderObj:JSON.parse(sessionStorage.getItem('oederObj')),//订单对象
      isShowAddOrder:false,//是否显示添加产品抽屉

      //=======产品数据
      productObj:{},
      productArr:[],

      //======== 订单详单数据
      orderDetailsObj:{
        odrdlId:'',//订单详情编号
        odrOn:'',//订单号
        prId:'',//产品编号
        odrdlProductName:'',//产品名称
        odrdlUnitPrice:'',//单价
        odrdlMoney:'',//金额
        odrdlCount:'',//产品数量
        odrdlRemark:'',//备注
      },//订单详单对象
      orderDetailsArr:[],//订单详单数组

      //查询条件
      selectParams:{
        keyword:'',
      },
    }
  },
  methods:{
    initProduct(){
      console.log(this.orderObj)
      if(this.orderObj.orderFromDetail.length != 0) {
        for (let i = 0; i < this.orderObj.orderFromDetail.length; i++) {
          let orderDetailsDx = {};
          if(this.orderObj.orderFromDetail[i].odrdlId == null){
            continue;
          }
          orderDetailsDx.odrdlCount = this.orderObj.orderFromDetail[i].odrdlCount;//产品数量
          orderDetailsDx.odrOn = this.orderObj.orderFromDetail[i].odrOn;//订单号
          orderDetailsDx.prId = this.orderObj.orderFromDetail[i].prId;//产品编号
          orderDetailsDx.odrdlProductName = this.orderObj.orderFromDetail[i].odrdlProductName;//产品名称
          orderDetailsDx.odrdlMoney = this.orderObj.orderFromDetail[i].odrdlMoney;//进价
          orderDetailsDx.odrdlUnitPrice = this.orderObj.orderFromDetail[i].odrdlUnitPrice;//售价
          orderDetailsDx.odrdlRemark = this.orderObj.orderFromDetail[i].odrdlRemark;//备注
          this.orderDetailsArr.push(orderDetailsDx);
        }
        console.log(this.orderDetailsArr)
      }
      this.axios.post('product/findAll',this.selectParams).then(res=>{
        this.productArr=res.data.data.records
      })
    },
    addProductDetails(){
      if(this.orderDetailsArr.length == 0){
        return;
      }
      this.axios.post('orderFromDetail/addBatchOrderDetails',this.orderDetailsArr).then((v)=>{
        if(v.data){
          ElMessage.success({
            message: "保存成功",
            type: 'success'
          });
          this.$router.push('/order');
        }
      })
    },
    addProductFun(obj){
      // console.log(obj)
      let orderDetailsDx = {};
      // if(this.orderDetailsArr.length == 0){
      //   orderDetailsDx.odrdlCount = 1;//产品数量
      //   orderDetailsDx.odrOn = this.odrOn;//订单号
      //   orderDetailsDx.prId = obj.prId;//产品编号
      //   orderDetailsDx.odrdlProductName = obj.prName;//产品名称
      //   orderDetailsDx.odrdlMoney = obj.prCostPrice;//进价
      //   orderDetailsDx.odrdlUnitPrice = obj.prPrice;//售价
      //   this.orderDetailsArr.push(orderDetailsDx);
      //   return;
      // }
      for (let i = 0; i < this.orderDetailsArr.length; i++) {
        if(this.orderDetailsArr[i].prId == obj.prId){//如果已有此产品就数量加一
          this.orderDetailsArr[i].odrdlCount = this.orderDetailsArr[i].odrdlCount + 1;
          return;
        }
      }
      orderDetailsDx.odrdlCount = 1;//产品数量
      orderDetailsDx.odrOn = this.orderObj.odrOn;//订单号
      orderDetailsDx.prId = obj.prId;//产品编号
      orderDetailsDx.odrdlProductName = obj.prName;//产品名称
      orderDetailsDx.odrdlMoney = obj.prCostPrice;//进价
      orderDetailsDx.odrdlUnitPrice = obj.prPrice;//售价
      orderDetailsDx.odrId = this.orderObj.odrId;
      this.orderDetailsArr.push(orderDetailsDx);
      console.log(this.orderDetailsArr)
    },
    //删除订单详情
    deleteProduct(obj) {
      this.orderDetailsArr.splice(obj.$index, 1);
    },
  },
  created() {
    this.initProduct();//初始化
    console.log(this.orderObj);
  }
}
</script>

<style scoped>
.div-class{
  /*padding: 8px;*/
  background: #DDDDDD;
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>
