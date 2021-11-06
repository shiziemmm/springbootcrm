<template>

  <!-- 查看病历筛选条件-->
  <el-row style="margin-bottom: 10px">
    <el-col style="margin-left: 15px" :span="4">
      <el-input size="mini" v-model="ptName" placeholder="病人姓名"></el-input>
    </el-col>
    <el-col  :span="1">
      <el-button size="mini" @click="paInIt" icon="el-icon-search" type="primary" ></el-button>
    </el-col>

  </el-row>

  <!--查看病历表格-->
  <el-row>
    <el-col>
      <el-table :data="patientBaseArr" height="460" size="mini">
        <el-table-column prop="ptName" label="姓名" />
        <el-table-column prop="ptSex" label="性别" />
        <el-table-column prop="ptAge" label="年龄" />
        <el-table-column prop="ptSbph" label="社保编码" />
        <el-table-column prop="ptUnit" label="工作单位" />
        <el-table-column prop="ptIphone" label="联系电话" />
        <el-table-column prop="ptHome" label="家庭住址" />
        <el-table-column prop="ptInDate" label="入院时间" />
        <el-table-column prop="ptZd" label="诊断" />
        <el-table-column prop="ptNo" label="住院号" />
        <el-table-column prop="ptWstext" label="外伤发生经过" />
      </el-table>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data(){
    return{
      ptName:'',
      patientBaseArr:[],
    }
  },
  methods:{
    paInIt(){
      this.axios({url:"select-ptname",params:{ptName:this.ptName}}).then((v)=>{
        this.patientBaseArr = v.data;
      })
    }
  },
  created() {
    this.paInIt();
  }
}
</script>

<style scoped>

</style>