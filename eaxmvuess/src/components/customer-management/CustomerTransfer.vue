<template>
  <div>
    <el-card>
      <el-row>
        <el-col>
          <i class="el-icon-guide" style="font-size: 20px;"></i>
          <el-select v-model="value" placeholder="请选择">
            <el-option label="全部数据" value=""></el-option>
            <el-option-group
                v-for="group in options"
                :key="group.label"
                :label="group.label"
            >
              <el-option
                  v-for="item in group.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              >
              </el-option>
            </el-option-group>
          </el-select>
          <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
          <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
          <el-button icon="el-icon-search" circle style="margin-left: 10px"></el-button>
        </el-col>
        <el-col>
          <el-button  type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>转移客户</el-button>
        </el-col>
      </el-row>

      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"> </el-table-column>

        <el-table-column prop="date" label="日期" sortable/>

        <el-table-column prop="name" label="姓名" sortable/>

        <el-table-column prop="address" label="地址" show-overflow-tooltip sortable/>

        <el-table-column  label="操作">
          <template  #default="scope">

            <el-tooltip content="转公海" placement="top">
              <el-button
                  icon="el-icon-data-line" size="mini"
                  @click="updateState(scope.row.registration.registrationNumber)"></el-button>
            </el-tooltip>

            <el-tooltip content="编辑" placement="top">
              <el-button
                  icon="el-icon-star-on" size="mini"></el-button>
            </el-tooltip>

            <el-tooltip content="删除" placement="top">
              <el-button
                  icon="el-icon-star-on" size="mini"></el-button>
            </el-tooltip>

          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
      <div class="fy_div">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 40]"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="tableData.length">
        </el-pagination>
      </div>
    </el-card>

    <el-dialog
        title="客户"
        v-model="dialogVisible"
        width="60%"
        :before-close="handleClose">
      <el-form  status-icon  ref="form" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="门诊号" prop="registrationNumber">
              <el-input  :disabled="true"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="挂号日期" prop="registrationTime">
              <el-date-picker

                  type="datetime"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="挂号类型" prop="registrationType">
              <el-select  placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="经办人" prop="registrationName">
              <el-input ></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="10">
            <el-form-item label="病人姓名" prop="patientDataName">
              <el-input  ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="身份证号码" prop="patientDataCard">
              <el-input  ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="病人电话" prop="patientDataPhone">
              <el-input ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="病人性别" prop="patientDataSex">
              <el-input ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
    <span class="dialog-footer">
      <el-button >取 消</el-button>
      <el-button type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CustomerTransfer',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',
      tableData: [
        {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          date: '2016-05-07',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }
      ],
      multipleSelection: [],
      options: [
        {
          label: '客户种类',
          options: [
            {
              value: '客户',
              label: '客户'
            },
            {
              value: '供应商',
              label: '供应商'
            },
            {
              value: '合作伙伴',
              label: '合作伙伴'
            },
            {
              value: '媒体',
              label: '媒体'
            }
          ]
        },
        {
          label: '生命周期',
          options: [
            {
              value: '潜在',
              label: '潜在'
            },
            {
              value: '签约',
              label: '签约'
            },
            {
              value: '重复购买',
              label: '重复购买'
            },
            {
              value: '失效',
              label: '失效'
            }
          ]
        },
        {
          label: '定性',
          options: [
            {
              value: '有价值',
              label: '有价值'
            },
            {
              value: '没价值',
              label: '没价值'
            },
            {
              value: '不确定',
              label: '不确定'
            }
          ]
        },
        {
          label: '定级',
          options: [
            {
              value: '大单',
              label: '大单'
            },
            {
              value: '小单',
              label: '小单'
            },
            {
              value: '正常',
              label: '正常'
            }
          ]
        },
        {
          label: '预定签约时间',
          options: [
            {
              value: '本周',
              label: '本周'
            },
            {
              value: '下周',
              label: '下周'
            },
            {
              value: '本月',
              label: '本月'
            },
            {
              value: '下月',
              label: '下月'
            }
          ]
        },
        {
          label: '客户来源',
          options: [
            {
              value: '客户介绍',
              label: '客户介绍'
            },
            {
              value: '电话来访',
              label: '电话来访'
            },
            {
              value: '媒体宣传',
              label: '媒体宣传'
            },
            {
              value: '网站填表',
              label: '网站填表'
            },
            {
              value: '其他',
              label: '其他'
            }
          ]
        },
      ],
      value: ''
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    formatter(row, column) {
      return row.address
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize)  //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    pageChange(p) {
      this.initData(p, this.pageSize)
    }
  }
}
</script>

<style scoped>
.fy_div{
  margin-top:20px;
  margin-left: 450px;
}
</style>
