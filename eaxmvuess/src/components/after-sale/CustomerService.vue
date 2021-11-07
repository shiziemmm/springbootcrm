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
          <el-input v-model="inputs" placeholder="请根据服务主题查询" style="width: 200px"></el-input>
          <el-button icon="el-icon-search" circle style="margin-left: 10px"></el-button>
        </el-col>
        <el-col>
          <el-button @click="dialogVisible = true" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>新建</el-button>
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
  name: 'CustomerService',
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
          label: '服务类型',
          options: [
            {
              value: '答疑',
              label: '答疑'
            },
            {
              value: '故障排除',
              label: '故障排除'
            },
            {
              value: '培训',
              label: '培训'
            },
            {
              value: '升级',
              label: '升级'
            }
          ]
        },{
          label: '服务方式',
          options: [
            {
              value: '电话',
              label: '电话'
            },
            {
              value: '传真',
              label: '传真'
            },
            {
              value: '邮寄',
              label: '邮寄'
            },
            {
              value: '上门',
              label: '上门'
            }
          ]
        },{
          label: '花费时间',
          options: [
            {
              value: '1个小时',
              label: '1个小时'
            },
            {
              value: '2个小时',
              label: '2个小时'
            },
            {
              value: '半个工作日',
              label: '半个工作日'
            }
            ,
            {
              value: '一个工作日',
              label: '一个工作日'
            }
          ]
        },{
          label: '状态',
          options: [
            {
              value: 0,
              label: '无需处理'
            },
            {
              value: 1,
              label: '未处理'
            },
            {
              value: 2,
              label: '处理中'
            },
            {
              value: 3,
              label: '处理完成'
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
