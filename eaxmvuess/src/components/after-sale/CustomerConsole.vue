<template>
  <div class="div_top">
    <div class="div_top_z">
      客服控制台
    </div>
  </div>

  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <el-input v-model="inputs" placeholder="客户" style="width: 200px"></el-input>
          <el-button class="button" type="text">操作按钮</el-button>
        </div>
      </template>
      <div v-for="o in 4" :key="o" class="text item">{{'列表内容 ' + o }}</div>
    </el-card>

    <el-card class="box-card1">
      <template #header>
        <div class="card-header">
          <el-input v-model="inputs" placeholder="QA" style="width: 200px"></el-input>
          <el-button class="button" type="text">操作按钮</el-button>
        </div>
      </template>
      <div v-for="o in 4" :key="o" class="text item">{{'列表内容 ' + o }}</div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'CustomerConsole'
}
</script>

<style scoped>
.div_top{
  height: 50px;
  line-height: 50px;
}
.div_top_z{
  text-align: center;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 500px;
  float: left;
}
.box-card1{
  width: 500px;
  float: left;
}

</style>
