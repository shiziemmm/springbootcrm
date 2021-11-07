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
          <el-input v-model="inputs" placeholder="请根据问题查询" style="width: 200px"></el-input>
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

    <el-dialog title="QA武器库"  width="65%" v-model="dialogVisible">
            <span slot="title" style="font-size: 16px">
                <i class="el-icon-collection"></i>
                {{isAdd?'新增QA':'修改QA'}}
            </span>
      <div style="color: darkgray;margin-left: 40px;margin-top: -20px">-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
      <el-form ref="qa" :model="qa" :rules="rules" label-width="80px">
        <el-form-item label="问 题:" style="margin-left: 40px;margin-top: 15px" prop="askIssue">
          <el-input v-model="qa" style="margin-left:15px;width: 116%"></el-input>
        </el-form-item>
        <el-form-item label="解 答:" style="margin-left: 40px;margin-top: 15px;height:410px">
          <el-row>
            <el-col :span="16">
              <quill-editor   :options="editorOption" style="width: 175%;margin-left: 15px;height: 300px">

              </quill-editor>
            </el-col>
          </el-row>
        </el-form-item>


        <el-form-item label="内部提示:" style="margin-left:40px;margin-top:20px">
<!--          <el-input style="margin-left:15px;height: 60px;width: 116%"-->
<!--                    type="textarea"-->
<!--                    :rows="2"-->
<!--                    placeholder="请输入内容"-->
<!--                    v-model="qa.askHint">-->
<!--          </el-input>-->
        </el-form-item>

        <el-form-item label="作 者:" style="margin-left:40px;margin-top:20px">
<!--          <el-input  style="margin-left:15px;width: 300px"-->
<!--                     placeholder="请输入内容"-->
<!--                     v-model="uId"-->
<!--                     :disabled="true">-->
<!--          </el-input>-->
        </el-form-item>

        <el-form-item label="创建日期:" style="margin-left:64%;margin-top:-10%">
<!--          <el-col :span="11" style="margin-left:-50px">-->
<!--            <el-date-picker  type="date" placeholder="选择日期" disabled v-model="qa.askDate" style="width:300px"></el-date-picker>-->
<!--          </el-col>-->
        </el-form-item>

        <el-form-item  label="分 类:" style="margin-left:40px;margin-top:0%" prop="qaclass">
<!--          <el-select v-model="qa.qaclass" value-key="oaId" clearable placeholder="请选择"  style="margin-left:15px">-->
<!--            <el-option-->
<!--                v-for="item in fenlei[1].options"-->
<!--                :key="item.oaId"-->
<!--                :label="item.oaType"-->
<!--                :value="item"-->
<!--            >-->
<!--            </el-option>-->
<!--          </el-select>-->
          <div  style="margin-left: 200px;margin-top: -40px">
            <router-link to="/qatype">
              <el-button size="mini"><i class="el-icon-s-operation" style="margin-right: 5px"></i>添加分类</el-button>
            </router-link>
          </div>
        </el-form-item>


        <el-form-item label="状 态:" style="margin-left:64%;margin-top:-10%">
<!--          <el-select v-model="qa.askState" clearable placeholder="请选择"  style="margin-left:10px;margin-top: 10px">-->
<!--            <el-option-->
<!--                v-for="item in executors"-->
<!--                :key="item.values1"-->
<!--                :label="item.labels1"-->
<!--                :value="item.values1"-->
<!--            >-->
<!--            </el-option>-->
<!--          </el-select>-->
        </el-form-item>

        <el-form-item  style="margin-top: 20px;margin-left: 330px">
          <el-button type="primary" >确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  // import {quillEditor} from 'vue-quill-editor'
  // import 'quill/dist/quill.core.css'
  // import 'quill/dist/quill.snow.css'
  // import 'quill/dist/quill.bubble.css'
export default {
  name: 'QALibrary',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName: 'first',

      //编辑器
      content:'',
      editorOption:{},
      switchContent:'', //转换之后的值
      qa:{
        user:{},
        qaclass:{},
      },
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
          value: 'A',
          label: 'A'
        },{
          value: 'B',
          label: 'B'
        },{
          value: 'C',
          label: 'C'
        },{
          value: 'D',
          label: 'D'
        }
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
