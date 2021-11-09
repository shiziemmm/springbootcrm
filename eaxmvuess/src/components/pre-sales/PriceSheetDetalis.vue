<template>
  <el-row>
    <el-col>
		<el-row>
		    <el-col :span="10"><div class="grid-content bg-purple">
		    </div></el-col>
		    <el-col :span="4"><div class="grid-content bg-purple">
		        <div style="overflow: hidden;">
		            <p style="float: left">
		                <svg t="1631071435698" class="icon"
		                     viewBox="0 0 1024 1024"
		                     version="1.1"
		                     xmlns="http://www.w3.org/2000/svg"
		                     p-id="12713"
		                     width="40"
		                     height="40">
		                    <path d="M341.333333 136.874667h341.333334v52.337777H341.333333z"
		                          fill="#2d8cf0"
		                          p-id="12714">
		                    </path>
		                    <path d="M616.903111 163.043556h-52.337778c0-24.348444-6.826667-65.194667-52.565333-65.194667-38.798222 0-52.565333 34.133333-52.565333 65.194667h-52.337778C407.096889 93.866667 450.218667 45.511111 512 45.511111c63.715556 0 104.903111 46.08 104.903111 117.532445z"
		                          fill="#2d8cf0" p-id="12715">
		                    </path>
		                    <path d="M876.088889 978.488889H147.911111V136.874667h104.903111v52.337777H200.248889V926.151111h623.502222V189.212444h-52.565333v-52.337777H876.088889V978.488889z"
		                          fill="#2d8cf0" p-id="12716">
		                    </path>
		                    <path d="M498.915556 364.316444h249.400888v52.337778H498.915556zM498.915556 703.715556h249.400888v52.337777H498.915556zM498.915556 534.072889h249.400888v52.337778H498.915556zM429.056 285.696h-52.337778v384l-83.399111-124.017778-43.463111 29.127111 179.2 266.581334V285.696z"
		                          fill="#2d8cf0" p-id="12717">
		                    </path></svg>
		            </p>
		            <h3 style="color: #606266"><p style="float: left;line-height:40px;margin-left: 10px">报价明细列表</p></h3>
		        </div>
		    </div></el-col>
		    <el-col :span="10"><div class="grid-content bg-purple">
		    </div></el-col>
		</el-row>
      <el-form v-model="formData">
		  <el-row>
			  <el-col :span="22">
				  <el-row>
					  <el-col :span="1">
					  </el-col>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
						  	<el-input  placeholder="产品名" v-model="formData.prName" clearable @change="selectList()"></el-input>
						  </el-form-item>
					  </el-col>
				  </el-row>
			  </el-col>
			  <el-col :span="2">
			  </el-col>
		  </el-row>
      </el-form>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="qdId" label="ID" />
		<el-table-column prop="product.prName" label="产品" />
        <el-table-column prop="qdQuantity" label="数量"  />
        <el-table-column prop="product.prPrice" label="单价" />
        <el-table-column prop="qdProTotalAmount" label="金额"/>
        <el-table-column prop="emp.empName" label="添加人" />
        <el-table-column prop="qdCreationTime" label="创建时间" />
		<el-table-column prop="quotation.client.clientName" label="客户" />
        <el-table-column prop="quotation.quTheme" label="报价单"  />
      </el-table>
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
	import dayjs from 'dayjs'
	import {ElMessage} from "element-plus";
	export default{
     name: 'PriceSheetDetalis',
		data(){
			return{
				pageno:1,
				pagesize:7,
				total:0,
				formData:{
					client:{},
					opportunity:{}
				},
				tableData:[]
			}
		},
		methods:{
			//条件分页查询
			selectList(){
				if(this.formData.date){
					this.formData.startTime=dayjs(this.formData.date[0]).format('YYYY-MM-DD')
					this.formData.endTime=dayjs(this.formData.date[1]).format('YYYY-MM-DD')
				}
				this.formData.pageNo=this.pageno
				this.formData.pageSize=this.pagesize
				this.axios.post("/quotationDetails/selectall",this.formData).then(res=>{
				  console.log("报价明细数据",res)
				  this.tableData=res.data.records
				  this.total=res.data.total
				})
			},
			//每页请求的页数发生改变时触发
			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
				this.pagesize=val
				this.selectList()
			},
			//当前页面发生改变时触发
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				this.pageno=val
				this.selectList()
			}
		},
		created(){
			this.selectList()
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
