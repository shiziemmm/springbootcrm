<template>
  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">订单详情</span></el-col>
  </el-row>

  <el-row style="padding: 15px 0px">

    <el-col :offset="5" :span="4">
      <el-input @blur="initOrderDetails" size="mini" v-model="SelectWhere.searchLike"  placeholder="根据客户名称或者负责人搜索"></el-input>
    </el-col>
    <el-col :span="1" >
      <el-button  size="mini" @click="initOrderDetails"  type="primary" >搜索</el-button>
    </el-col>

    <el-col :offset="2" :span="5">
      <span style="font-size: 12px;">负责人：</span>&nbsp;
      <el-select size="mini" v-model="SelectWhere.empArr" @change="initOrderDetails" multiple collapse-tags placeholder="请选择">
        <el-option v-for="ar in empArr"
                   :label="ar.empName"
                   :value="ar.empId">
        </el-option>
      </el-select>
    </el-col>

  </el-row>

  <!--显示商品表格-->
  <el-row>
    <el-col>
      <el-table @cell-click="lookProductText" height="520px" :data="orderDetailsArr.slice((orderDetailsCurrent-1)*orderDetailsSize,orderDetailsCurrent*orderDetailsSize)" size="mini">
        <el-table-column prop="orderFrom.odrName" label="订单主题"/>
        <el-table-column prop="orderFrom.odrOn" label="订单号"/>
        <el-table-column prop="odrdlProductName"  label="产品名称" />
        <el-table-column prop="odrdlCount"   label="订单数量" />
        <el-table-column prop="odrdlUnitPrice"   label="单价" />
        <el-table-column   label="总价" >
          <template #default="obj">
            {{obj.row.odrdlCount*obj.row.odrdlUnitPrice}}
          </template>
        </el-table-column>
        <el-table-column prop="empName"  label="所有者" />
        <el-table-column prop="orderFrom.customerName" label="客户名称"/>
      </el-table>

      <!--分页插件-->
      <el-pagination
          style="text-align: center;"
          @size-change="orderDetailsSizeChange"
          @current-change="orderDetailsCurrentChange"
          :current-page="orderDetailsCurrent"
          :page-sizes="[2,4,6,8,10]"
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="orderDetailsSize"
          :total="orderDetailsArr.length">
      </el-pagination>
    </el-col>
  </el-row>


</template>

<script>
export default {
  data(){
    return{
      //======================查询条件数据
      SelectWhere:{
        startPrice:'',//起始金额
        endPrice:'',//结束金额
        searchLike:'',//模糊搜索
        empArr:[],//客户编号数组
        doctorType:2,//类型
      },
      orderDetailsObj:{},//订单详情对象
      orderDetailsArr:[],//订单详情数组

      empArr:[],//员工对象

      //分页
      orderDetailsCurrent:1,//住院登记分页当前页
      orderDetailsSize:8,//住院登记分页页大小
    }
  },
  methods:{
    initOrderDetails(){
        this.axios.post("/orderFromDetail/selectOrderDetailsWhere",this.SelectWhere).then((v)=>{
          console.log(v.data.data)
          this.orderDetailsArr = v.data.data;
        })
    },

    lookProductText(obj){
      console.log(obj)
      sessionStorage.setItem('odrOn',obj.odrOn);
      // sessionStorage.setItem('odrOn',obj);
      this.$router.push('/orderParticulars');
    },

    //========================分页方法
    orderDetailsSizeChange: function(size) {
      this.orderDetailsSize = size;
    },
    orderDetailsCurrentChange: function(currentPage) {
      this.orderDetailsCurrent = currentPage;
    },
  },
  created() {
    this.axios({url:'emp/empSelectAllByOrder'}).then((v)=>{
      this.empArr = v.data.data;
    })
    this.initOrderDetails();
  }
}
</script>

<style scoped>

</style>
