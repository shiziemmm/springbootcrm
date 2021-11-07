<template>
  <div>
    <el-card style="width: 490px;height: 100%">
      <!--表头-->
      <el-row>

        <el-table  style="margin-top: 20px">
          <el-table-column
              prop="registrationNumber"
              width="130px">
          </el-table-column>
          <el-table-column>
            <el-button
                icon="el-icon-view" size="mini"
                @click="edit(scope.row)"></el-button>
          </el-table-column>
          <el-table-column>
            <el-button
                icon="el-icon-view" size="mini"
                @click="edit(scope.row)"></el-button>
          </el-table-column>

        </el-table>
      </el-row>
    </el-card>


    <el-card style="width: 60%;height: 100%;margin-left: 500px;margin-top: -160px">
      <!--表头-->
      <el-table
          stripe
          style="width: 100%">
        <el-table-column
            prop="prescriptionId"
            label="客户">
        </el-table-column>
        <el-table-column
            prop="registration.registrationNumber"
            label="原所有者">
        </el-table-column>
        <el-table-column
            prop="registration.patient.patientDataName"
            label="新所有者">
        </el-table-column>
        <el-table-column
            prop="prescriptionTime"
            label="操作人">
        </el-table-column>
        <el-table-column
            prop="prescriptionMoney"
            label="操作时间">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'CustomerTransferRecord'
}
</script>

<style scoped>

</style>
