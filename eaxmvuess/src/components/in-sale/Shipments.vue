<template>

  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">订单管理</span></el-col>
  </el-row>

  <el-row style="padding: 15px 0px">

    <el-col :offset="2" :span="4">
      <el-input @blur="initShipments" size="mini" v-model="SelectWhere.searchLike"  placeholder="根据客户名称或者负责人搜索"></el-input>
    </el-col>
    <el-col :span="1" >
      <el-button  size="mini" @click="initShipments"  type="primary" >搜索</el-button>
    </el-col>

    <el-col :offset="2" :span="5">
      <span style="font-size: 12px;">客户：</span>&nbsp;
      <el-select size="mini" style="width: 180px;" multiple collapse-tags v-model="SelectWhere.cliIdArr" @change="initShipments" placeholder="请选择">
        <el-option v-for="ar in clientArr"
                   :label="ar.clientName"
                   :value="ar.clientId">
        </el-option>
      </el-select>
    </el-col>


    <el-col :offset="1"  :span="8">
      &nbsp;<span style="font-size: 12px;">创建时间：</span>&nbsp;

      <el-date-picker @change="initShipments" style="width: 130px"
                      type="date"
                      size="mini"
                      value-format="YYYY-MM-DD"
                      v-model="SelectWhere.startDate"
                      placeholder="日期">
      </el-date-picker>
      &nbsp;<span style="font-size: 12px;">至</span>&nbsp;
      <el-date-picker @change="initShipments" style="width: 130px"
                      type="date"
                      size="mini"
                      value-format="YYYY-MM-DD"
                      v-model="SelectWhere.endDate"
                      placeholder="日期">
      </el-date-picker>
    </el-col>



  </el-row>


  <el-row>
    <el-col>
      <el-table :data="shipmentArr" height="520px" size="mini">
        <el-table-column prop="odrOn" width="100px"  label="订单号" />
        <el-table-column prop="sptOn" label="发货单号"/>
        <el-table-column prop="orderFrom.odrName" label="订单主题" />
        <el-table-column prop="orderFrom.odrCount" label="产品数量" />
        <el-table-column prop="orderFrom.customerName" label="对应客户" />
        <el-table-column prop="sptState" label="订单状态" />
        <el-table-column prop="sptLogisticsCompany" label="物流公司" />
        <el-table-column prop="sptLogistics" width="150px" label="物流单号" />
        <el-table-column prop="sptDate" width="150px" label="创建时间" />
        <el-table-column width="150px" label="操作">
          <template #default="obj">
            <el-button type="text" @click.stop="lookShipment(obj.row)" size="mini">查看</el-button>
            <el-button type="text" v-if="obj.row.sptState != '已签收'" @click.stop="updateState(obj.row)" size="mini">签收订单</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页插件-->
      <el-pagination
          style="text-align: center;"
          @size-change="shipmentSizeChange"
          @current-change="shipmentCurrentChange"
          :current-page="shipmentCurrent"
          :page-sizes="[2,4,6,8,10]"
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="shipmentSize"
          :total="shipmentArr.length">
      </el-pagination>
    </el-col>
  </el-row>

</template>

<script>
import {ElMessage} from "element-plus";

export default {
  data(){
    return{
      //===========查询条件
      SelectWhere:{
        startDate:'',//开始日期
        endDate:'',//结束日期
        searchLike:'',//模糊搜索
        cliIdArr:[],
        type:'',//类型
      },
      shipmentArr:[],//订单数组
      clientArr:[],//联系人数组

      //===分页
      shipmentSize:6,
      shipmentCurrent:1,
    }
  },
  methods:{
    //初始化
    initShipments(){
      this.axios.post("shipmentsFrom/selectWhereShipment",this.SelectWhere).then((v)=>{
        console.log(v.data.data)
        this.shipmentArr = v.data.data;
      })

    },

    //修改状态
    updateState(obj){
      alert(obj.orderFrom.odrId)
      this.axios.post("shipmentsFrom/updateShipmentState",{odrId:obj.orderFrom.odrId,sptId:obj.sptId}).then((v)=>{
        if(v.data){
          ElMessage.success({
            message: "签收成功",
            type: 'success'
          });
          this.initShipments();
        }
      })

    },

    lookShipment(obj){//查看发货单详情
      sessionStorage.setItem('shipmentObj',JSON.stringify(obj));
      this.$router.push('/shipmentsDetails');

    },

    //========================分页方法
    shipmentSizeChange: function(size) {
      this.shipmentSize = size;
    },
    shipmentCurrentChange: function(currentPage) {
      this.shipmentCurrent = currentPage;
    },

    //打开发货页面的方法
    // openShipmentProductPage(obj){
    //   sessionStorage.setItem("odrOn",obj.odrOn);
    //   this.$router.push('/shipmentsPage');
    // }
  },
  created() {
    this.axios({url:'/client/find_client'}).then((v)=>{
      this.clientArr = v.data;
    });
    this.initShipments();//初始化
  }
}
</script>

<style scoped>

</style>
