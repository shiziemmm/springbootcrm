<template>

  <!--查看病历表格-->
  <el-row>
    <el-col>
      <el-table :data="patientBaseArr" height="460" size="mini">
        <el-table-column prop="ptName" label="姓名" />
        <el-table-column prop="ptSex" label="性别" />
        <el-table-column prop="ptAge" label="年龄" />
        <el-table-column prop="rtName" label="与患者的关系" />
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
  data() {
    return {

    }
  },
  methods: {
    amm(){
      this.axios.post("/select").then((v)=>{
        alert(v.data)
      })
    }
  },
  created() {
    this.amm();
  }
}
</script>

<style scoped>

</style>
