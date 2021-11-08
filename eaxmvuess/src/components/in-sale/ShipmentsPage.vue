<template>
<div>
    <el-row :gutter="15">
  <el-col :span="12" :offset="11">
    <b>发货单</b>
  </el-col>    
  <el-divider></el-divider>
    <el-form ref="rule" :rules="rule" :model="formData" size="medium" label-width="100px">
       <el-col :span="12">
            <el-form-item label="对应客户" prop="customerName">
             <el-input v-model="formData.customerName" :disabled="true"
                       :style="{width: '300px'}"></el-input>
            </el-form-item>
       </el-col>
       <el-col :span="12">
            <el-form-item label="订单编号" prop="indentId">
              <el-input v-model="formData.indentId"  :disabled="true"
                       :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发货日期" prop="shdate">
              <el-date-picker v-model="formData.shdate" type="date" value-format="yyyy-MM-dd" 
              :style="{width: '300px'}" placeholder="请选择发货日期" clearable></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="收货人/地址">
            <el-divider></el-divider>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="收货人">
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="姓名" prop="addressee">
              <el-input v-model="formData.addressee" 
                        clearable style="width:220px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7" >
            <el-form-item label="电话" prop="addresseeTelephone">
              <el-input v-model="formData.addresseeTelephone"
                        clearable style="width:220px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8" >
            <el-form-item label="移动电话" prop="addresseePhone">
              <el-input v-model="formData.addresseePhone" 
                        clearable :style="{width: '240px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-form-item label="地址">
            </el-form-item>
          </el-col>
          <el-col :span="6" >
            <el-form-item label="省" prop="province">
              <el-input v-model="formData.province" 
                        clearable style="width:220px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="城市" prop="city">
              <el-input v-model="formData.city" 
                        clearable style="width:220px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="邮编" prop="postcode">
              <el-input v-model="formData.postcode" 
                        clearable style="width:240px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="详细地址" prop="addresseeSite">
              <el-input v-model="formData.addresseeSite" 
                        clearable style="width:600px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-form-item label="明细">
            <el-divider></el-divider>  
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-form-item label="">
            <el-table :data="formData.indentDetail" border  style="width: 1000px">
            <el-table-column prop="productName" label="品名"> </el-table-column>
            <el-table-column prop="model" label="型号"> </el-table-column>
            <el-table-column prop="quantity" label="数量"> </el-table-column>
            <el-table-column prop="unitPrice" label="单价"> </el-table-column>
            <el-table-column prop="money" label="金额"> </el-table-column>
            <el-table-column prop="remark" label="备注"> </el-table-column>
          </el-table>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="打包件数" prop="packets">
              <el-input v-model="formData.packets" 
                       :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量(Kg)" prop="weight">
              <el-input v-model="formData.weight" show-word-limit
                        clearable :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物流公司" prop="logisticsCompany">
              <el-input v-model="formData.logisticsCompany" show-word-limit
                        clearable :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物流单号" prop="trackingNumber">
              <el-input v-model="formData.trackingNumber" show-word-limit
                        clearable :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="运费" prop="freight">
              <el-input v-model="formData.freight" show-word-limit
                        clearable :style="{width: '300px'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input type="textarea" v-model="formData.remark" show-word-limit
                        clearable :style="{width: '500px'}"></el-input>
            </el-form-item>
          </el-col>
    </el-form>        
    </el-row>  
<div class="app-buttonx"><el-button @click="add('rule')" icon="el-icon-truck" type="primary"  size="small">发货:点此确认发货</el-button></div> 
</div>

</template>

<script>
export default {
  name: 'shipments',
  data() {
    return{
      formData:{},
    }
  },
  //methods: {
    // init(){
    //   findIndentAnddetaile(this.$route.params.id).then(res=>{
    //     this.formData =res.data
    //     this.formData.indentId = this.$route.params.id
    //     this.formData.id = null
    //   })
    // },
  //   add(rule){
  //     this.$refs[rule].validate((valid) => {
  //       if (valid) {
  //         this.$confirm('确认发货后，订单不可修改', '提示', {
  //             confirmButtonText: '确定',
  //             cancelButtonText: '取消',
  //             type: 'warning',
  //           })
  //             .then(() => {
  //               addShipments(this.formData).then(res=>{
  //               this.$message({
  //                     message: res.message,
  //                     type: res.success?'success':'error'
  //                 });
  //                 if(res.success){
  //                     this.$router.push({path:'/indent/viewIndex/'+this.$route.params.id})
  //                 } 
  //               })
  //             })
  //             .catch(() => {
  //               this.$message({
  //                 type: 'info',
  //                 message: '已取消删除',
  //               })
  //             })
  //       }else{
  //         return false
  //       }
  //     })        
  //   },
  // },
  // created(){
  //   this.init();  
  // }
}
</script>

<style>
.app-buttonx{
  text-align: center;
}
</style>
