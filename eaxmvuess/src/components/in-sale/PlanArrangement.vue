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
      <el-button class="el-button el-button--default is-circle" @click="cs()">
        <i class="el-icon-search"></i>
      </el-button>
    </el-row>
    <el-row style="margin-top: 20px;margin-left: 15px">
      <el-col >
        <span style="font-size: 12px">计划回款 共0条</span>
        <el-button icon="el-icon-loading" size="small" type="danger" v-show="relieve" @click="cs1()">
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
          style="width: 100%">
          <el-table-column
            prop="id"
            label="ID"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名">
          </el-table-column>
          <el-table-column
            prop="amount1"
            label="数值 1">
          </el-table-column>
          <el-table-column
            prop="amount2"
            label="数值 2">
          </el-table-column>
          <el-table-column
            prop="amount3"
            label="数值 3">
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
      tableData: [{
        id: '12987122',
        name: '王小虎',
        amount1: '234',
        amount2: '3.2',
        amount3: 10
      }, {
        id: '12987123',
        name: '王小虎',
        amount1: '165',
        amount2: '4.43',
        amount3: 12
      }, {
        id: '12987124',
        name: '王小虎',
        amount1: '324',
        amount2: '1.9',
        amount3: 9
      }, {
        id: '12987125',
        name: '王小虎',
        amount1: '621',
        amount2: '2.2',
        amount3: 17
      }, {
        id: '12987126',
        name: '王小虎',
        amount1: '539',
        amount2: '4.1',
        amount3: 15
      }]
    }
  },
  methods:{
    dome(){
      console.log(this.value,"1111")
      this.relieve=true
    },
    cs(){
      console.log(this.content,"222")
      this.relieve=true
    },
    cs1(){
      this.relieve=false
      this.value='全部数据'
      this.content=''
    }
  }
}
</script>

<style scoped>

</style>
