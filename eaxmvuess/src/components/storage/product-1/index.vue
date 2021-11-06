<template>
     <div>
      <div class="lable-titi">
            <el-input
                placeholder="搜索一下"
                suffix-icon="el-icon-search"
                v-model="input"
                style="width:200px;top: 10px;left: 20px"
                @change="checkChange"
              ></el-input>
      </div>
    <div class="app-container">
      <div class="app-button">
          <el-button icon="el-icon-circle-plus" @click="handAdd()" size="small">新建</el-button>
      </div>
      <div class="cont-bod-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="50" label="序号"></el-table-column>
          <el-table-column prop="prId" label="ID" width="110" sortable></el-table-column>
          <el-table-column prop="prName" label="产品名称" width="110" sortable></el-table-column>
          <el-table-column prop="prCoding" label="编码" width="110" sortable></el-table-column>
          <el-table-column prop="prModel" label="型号" width="110" sortable></el-table-column>
          <el-table-column prop="prPrice" label="价格" width="110" sortable></el-table-column>
          <el-table-column prop="prUnit" label="单位" width="110" sortable></el-table-column>
          <el-table-column prop="prState" label="状态" width="110" sortable>
            <template #default="scope">
               <el-tag :type="scope.row.prState == 0? 'success':'danger'">{{scope.row.prState == 0? '正常':'停售'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="prCostPrice" label="成本价格" width="110" sortable></el-table-column>
          <el-table-column fixed="right" label="操作" align="center" width="100">
            <template slot-scope="scope">
               <el-button @click="handUpdate(scope.row.id)" size="small" type="text">查看</el-button>
               <el-button @click="handDelete(scope.row.id)" size="small" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="page-list">
          <el-pagination
            @size-change="onPageSizeChange"
            @current-change="onPageChange"
            background
            :total="Number(total)"
            :page-sizes="[10,20,30]"
            layout="sizes, prev, pager, next, jumper"
          ></el-pagination>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
    data(){
        return{
            tableData:[],
        }
    },
    methods: {
        // 新建产品
        handAdd(){

        },
        // 查看订单
        handUpdate(){

        },
        //删除订单
        handDelete(){

        }
    },
    created(){

    }
}
</script>


<style>
.cont-bod-box {
  padding: 20px;
  background: #fff;
  border-radius: 3px;
  margin-top: 15px;
  margin-bottom: 15px;
  border: 1px solid #ebeef5;
}
.page-list {
  text-align: center;
  margin-top: 10px;
}
.app-button{
  position: absolute;
  right: 20px;
  top: 30px;
}
.dashboard-container{
  background: #efefef;
}
</style>
