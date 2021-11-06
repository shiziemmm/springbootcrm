<template>
  <el-row>
    <el-col>
      <div>销售机会</div>
      <el-form v-model="formData">
        <el-row>
          <el-col :span="2">
            <el-form-item label="" size="medium" label-width="130">
              <el-select v-model="formData.shuju" placeholder="请选择">
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
            </el-form-item>
          </el-col>
          <el-col :span="1">
          </el-col>
          <el-col :span="5">
            <el-row>
              <el-col :span="12">
                <el-form-item label="" size="medium">
                  <el-input v-model="formData.jihui" placeholder="请输入内容"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="" size="medium">
                  <el-button  class="addbtn" style="width: 100px;">机会主题</el-button>
                </el-form-item>
              </el-col>
              <el-col :span="3">
                <el-form-item label="" size="medium">
                  <el-select v-model="formData.value" clearable >
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="14">
          </el-col>
          <el-col :span="2">
            <el-form-item label="" size="medium">
              <el-button class="addbtn"  @click="drawer = true,cli()"><i class="el-icon-plus"></i>新建</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="op_id" label="ID" />
        <el-table-column prop="op_theme" label="机会主题"  />
        <el-table-column prop="client.client_name" label="客户" />
        <el-table-column prop="op_priority" label="优先"/>
        <el-table-column prop="op_type" label="类型" />
        <el-table-column prop="emp.emp_name" label="负责人" />
        <el-table-column prop="op_expected_signing_date" label="预计签单日期"  />
        <el-table-column prop="op_expected_amount" label="预计金额" />
        <el-table-column prop="op_possibility" label="可能性" />
        <el-table-column prop="op_phase_stay" label="阶段停留" />
        <el-table-column prop="op_date" label="更新日期" />
        <el-table-column prop="name" label="操作" >
          <el-row>
            <el-col :span="8">
              <i class="el-icon-document"></i>
            </el-col>
            <el-col :span="8">
              <i class="el-icon-edit"></i>
            </el-col>
            <el-col :span="8">
              <i class="el-icon-delete"></i>
            </el-col>
          </el-row>
        </el-table-column>
      </el-table>
      <el-drawer
        title="我是标题"
        :visible.sync="drawer"
        :with-header="false">
        <span>我来啦!</span>
      </el-drawer>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          background
          :current-page="pageno"
          :page-size="pagesize"
          layout="total, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-col>
  </el-row>
</template>

<script>
	export default{
     name: 'salesOpportunities',
		data(){
			return{
				pageno:1,
				pagesize:5,
				total:0,
				drawer:false,
				formData:{
					shuju:''
				},
				options: [{
				          label: '热门城市',
				          options: [{
				            value: 'Shanghai',
				            label: '上海'
				          }, {
				            value: 'Beijing',
				            label: '北京'
				          }]
				        }, {
				          label: '城市名',
				          options: [{
				            value: 'Chengdu',
				            label: '成都'
				          }, {
				            value: 'Shenzhen',
				            label: '深圳'
				          }, {
				            value: 'Guangzhou',
				            label: '广州'
				          }, {
				            value: 'Dalian',
				            label: '大连'
				          }]
				        }],
				tableData:[
					{
						op_id:1,
						op_theme:"一二三",
						op_date:"2021-11-06",
						op_state:"可见",
						op_type:"建筑类",
						op_findtime:"2021-05-03",
						op_priority:"一级",
						op_expected_signing_date:"2021-11-31",
						op_possibility:"90%",
						op_expected_amount:"50.00",
						op_stage:"初期沟通",
						op_phase_stay:"365天",
						emp:{
							emp_id:1,
							emp_name:"红红"
						},
						client:{
							client_id:1,
							client_name:"花花"
						}
					}
				]
			}
		},
		methods:{
			cli(){
				console.log(this.drawer)
        this.axios.
			}
		}
	}
</script>

<style>
	.el-table .cell{
	        text-align: center;
	    }
</style>
<style scoped>

	.el-icon-edit:hover{
		color: #FF1493;
	}
	.el-icon-delete:hover{
		color: #FF1493;
	}
	.el-icon-document:hover{
		color: #FF1493;
	}
	/* .addbtn{
		color: #FF1493;
	} */
	.el-pagination{
	        padding: 10px 0px;
	        background-color: #fff;
	        text-align: center;
	    }
</style>
