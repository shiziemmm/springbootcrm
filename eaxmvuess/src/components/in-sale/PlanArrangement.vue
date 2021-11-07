<template>
  <el-card style="height: 600px">
    <el-row :span="8">
      <el-select v-model="value"  placeholder="请选择" style="width: 150px" @change="dome()">
        <el-option-group
          v-for="group in options"
          :key="group.label"
          :label="group.label">
          <el-option
            v-for="item in group.options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-option-group>
      </el-select>
      <el-input placeholder="请输入内容" style="width: 150px;margin-left: 100px" v-model="content"></el-input>
      <el-button class="el-button el-button--default is-circle"  @click="cs()">
<!--        <i class="el-icon-search"></i>-->
        查找
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
        <span style="font-size: 12px">计划回款 共{{tableData.length}}条</span>
        <el-button  size="small" type="danger" v-show="relieve" @click="cs1()">
          解除搜索
        </el-button>
        <el-button size="small" style="margin-left: 1100px">新增</el-button>
      </el-col>

    </el-row>
    <el-row style="margin-top: 20px">
      <el-col>
        <el-table
          :data="tableData"
          border
          show-summary
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{ textAlign: 'center' }"
          style="width: 100%">
          <el-table-column
              prop="orderNumber"
              header-align="center"
              label="订单编号">
          </el-table-column>
          <el-table-column
              prop="retPrice"
              label="回款金额">
          </el-table-column>
          <el-table-column
            prop="customerName"
            label="客户名称">
          </el-table-column>
          <el-table-column
            prop="futuresName"
            label="期次名称">
          </el-table-column>
          <el-table-column
            prop="principal"
            label="负责人">
          </el-table-column>

          <el-table-column
              prop="retTime"
              label="回款时间">
          </el-table-column>
          <el-table-column
              prop="retWhether"
              label="是否回款">
          </el-table-column>
          <el-table-column
              prop="invoice"
              label="是否开发票">
          </el-table-column>
          <el-table-column label="操作" width="220px">
            <template v-slot:default="r">
              <!--        <el-button type="danger" @click="open(r.row)">删除</el-button>-->
              <el-button type="primary" size="small">视图</el-button>
              <el-button type="primary" size="small">视图</el-button>
              <el-button type="primary" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>

export default {
  data() {
    return {
      options: [{
        options: [{
          value: '全部数据',
          label: '全部数据'
        }]
      }, {
        label: '是否回款',
        options: [{
          value: '已回',
          label: '已回'
        }, {
          value: '未回',
          label: '未回'
        }]
      },
        {
          label: '是否已开发票',
          options: [{
            value: '已开',
            label: '已开'
          }, {
            value: '未开',
            label: '未开'
          }]
        }],
      value: '全部数据',
      content:'',
      relieve:false,
      tableData: []
    }
  },
  methods:{
    getData(){
      this.axios.post("/returned/returnlist").then((v)=>{
        this.tableData=v.data.data
        for (let i = 0; i < this.tableData.length ; i++) {
          console.log(this.tableData[i].retTime)
          const date = new Date(this.tableData[i].retTime)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
         this.tableData[i].retTime=y + '-' + MM + '-' + d
            // let date=new Date(this.tableData[i].retTime)
            // this.tableData[i].retTime=new window.FormData(date,"yyyy-MM-dd hh:mm")
        }
        // return formatDate(date, "yyyy-MM-dd hh:mm");
        console.log(this.tableData)
      }).catch()
    },
    dome(){
      if(this.value=="全部数据"){
        this.getData()
      }else{
      this.axios({
        url:"/returned/state",
        params:{value:this.value}
      }).then((res)=>{
        this.tableData=res.data.data
      }).catch()
      console.log(this.value,"1111")
      this.relieve=true
    }
    },
    cs(){
      console.log(this.content,"222")
      this.relieve=true
    },
    cs1(){
      this.relieve=false
      this.value='全部数据'
      this.content=''
      this.getData()
    }
  },
  created() {
    this.getData()
  }
}
</script>

<style scoped>

</style>
