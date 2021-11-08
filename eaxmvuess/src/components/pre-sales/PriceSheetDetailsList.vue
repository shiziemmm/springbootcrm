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
		            <h3 style="color: #606266"><p style="float: left;line-height:40px;margin-left: 10px">报价列表</p></h3>
		        </div>
		    </div></el-col>
		    <el-col :span="10"><div class="grid-content bg-purple">
		    </div></el-col>
		</el-row>
      <el-form v-model="formData">
		  <el-row>
			  <el-col :span="22">
				  <el-row>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
						  	<el-input  placeholder="报价单号" v-model="formData.quNo" clearable @change="selectList()"></el-input>
						  </el-form-item>
					  </el-col>
					  <el-col :span="1">
					  </el-col>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
							<el-input  placeholder="报价主题" v-model="formData.quTheme" @change="selectList()" clearable></el-input>
						  </el-form-item>
					  </el-col>
					  <el-col :span="1">
					  </el-col>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
							<el-input  placeholder="客户" v-model="formData.client.clientName"  @change="selectList()" clearable></el-input>
						  </el-form-item>
					  </el-col>
					  <el-col :span="1">
					  </el-col>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
							<el-input  placeholder="销售机会" v-model="formData.opportunity.opTheme" @change="selectList()" clearable></el-input>
						  </el-form-item>
					  </el-col>
					  <el-col :span="1">
					  </el-col>
					  <el-col :span="3">
						  <el-form-item label="" size="medium ">
						  	<div class="block">
						  		<el-date-picker
						  				v-model="formData.date"
										@change="selectList()"
						  				type="daterange"
						  				range-separator="至"
						  				start-placeholder="开始日期"
						  				end-placeholder="结束日期">
						  		</el-date-picker>
						  	</div>
						  </el-form-item>
					  </el-col>
				  </el-row>
			  </el-col>
			  <el-col :span="2">
			  	<el-form-item label="" size="medium">
			  	  <el-button class="addbtn"  @click="toDoAddSheet('/priceSheets')"><i class="el-icon-plus"></i>新建</el-button>
			  	</el-form-item>			  
			  </el-col>
		  </el-row>
      </el-form>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="quId" label="ID" />
		<el-table-column prop="quNo" label="报价单号" />
        <el-table-column prop="quTheme" label="主题"  />
        <el-table-column prop="client.clientName" label="客户" />
        <el-table-column prop="quData" label="日期"/>
        <el-table-column prop="opportunity.opTheme" label="销售机会" />
        <el-table-column prop="quTotalAmount" label="报价(总)" />
        <el-table-column prop="emp.empName" label="报价人"  />
        <el-table-column prop="name" label="操作" >
			<template #default="scope">
				<el-row>
				  <el-col :span="8">
				  </el-col>
				  <el-col :span="8">
					  <el-time  @click="toRouter('/quotation','editData',scope.row)">
						  <svg t="1636263015629" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5172" width="16" height="16"><path d="M851.8 927.9H172.2c-42 0-76.1-34.1-76.1-76.1V172.2c0-42 34.1-76.1 76.1-76.1h341.4c42 0 76.1 34.1 76.1 76.1s-34.1 76.1-76.1 76.1H248.3v527.5h527.5V507.3c0-42 34.1-76.1 76.1-76.1s76.1 34.1 76.1 76.1v344.5c-0.1 42-34.2 76.1-76.2 76.1z" fill="#666777" p-id="5173"></path><path d="M489.9 607c-19.5 0-38.9-7.4-53.8-22.3-29.7-29.7-29.7-77.9 0-107.6L793.3 120c29.7-29.7 77.9-29.7 107.6 0 29.7 29.7 29.7 77.9 0 107.6L543.7 584.8c-14.9 14.8-34.4 22.2-53.8 22.2z" fill="#666666" p-id="5174"></path></svg>
					  </el-time>
				  </el-col>
				  <el-col :span="8">
					  <el-time  @click="del(scope.row)">
						  <svg t="1636262937851" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3303" width="16" height="16"><path d="M457.772 718.3v-324.188c0-18.848-15.337-34.168-34.168-34.168-18.863 0-34.199 15.321-34.199 34.168v324.188c0 18.848 15.337 34.168 34.199 34.168 18.833 0 34.168-15.32 34.168-34.168z" p-id="3304"></path><path d="M777.342 360.711c-18.863 0-34.199 15.321-34.199 34.168v441.293c0 13.649-11.073 24.752-24.722 24.752h-412.66c-13.649 0-24.722-11.104-24.722-24.752v-442.49c0-18.848-15.337-34.183-34.199-34.183-18.863 0-34.199 15.337-34.199 34.183v442.49c0 51.345 41.775 93.12 93.12 93.12h412.66c51.345 0 93.12-41.775 93.12-93.12v-441.293c-0.001-18.847-15.337-34.168-34.201-34.168z" p-id="3305"></path><path d="M634.595 718.3v-324.188c0-18.848-15.337-34.168-34.199-34.168-18.833 0-34.168 15.321-34.168 34.168v324.188c0 18.848 15.337 34.168 34.168 34.168 18.863 0 34.199-15.32 34.199-34.168z" p-id="3306"></path><path d="M895.123 242.070h-142.625v-54.244c0-51.345-41.499-93.12-92.476-93.12h-295.372c-51.345 0-93.12 41.775-93.12 93.12v54.244h-142.625c-18.863 0-34.199 15.321-34.199 34.168s15.337 34.183 34.199 34.183h766.216c18.833 0 34.168-15.337 34.168-34.183s-15.336-34.168-34.168-34.168zM684.101 242.070h-344.201v-54.244c0-13.649 11.104-24.768 24.752-24.768h295.372c13.496 0 24.078 10.873 24.078 24.768v54.244z" p-id="3307"></path></svg>
					  </el-time>
				  </el-col>
				</el-row>
			</template>
        </el-table-column>
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
     name: 'PriceSheetDetailsList',
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
				this.axios.post("/quotation/selectall",this.formData).then(res=>{
				  console.log("报价数据",res)
				  this.tableData=res.data.records
				  this.total=res.data.total
				})
			},
			//删除所有
			del(row){
				 this.$confirm('确定删除改机会的信息包括详细的子数据吗？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
				}).then(() => {
					this.axios.get("/quotation/del",{
						params:{
							quid:row.quId
						}
					}).then(res=>{
					  console.log("删除后的结果",res)
					  ElMessage.success({
					    message: res.data.obj?"删除成功":"删除失败",
					    type: 'success'
					  });
					 this.selectList()
					})
				}).catch(() => {
					console.log("5555555555555")
				});
			},
			//跳转页面
			toRouter(path,type,row){
				console.log("单行数据",row)
				if(type=="edit"){
					if(path=='/salesOpportunitiesDetails'){
						sessionStorage.setItem('isshow',false)
						if(row){
							sessionStorage.setItem('editData',JSON.stringify(row))
						}
					}
				}else if(type=='editData'){
					if(path=='/quotation'){
						sessionStorage.setItem("todo",true)
						if(row){
							sessionStorage.setItem('tianjia','tianjia')
							sessionStorage.setItem('editData',JSON.stringify(row))
						}
					}
				}else{
					var data=JSON.parse(sessionStorage.getItem('editData'))
					var bianji=sessionStorage.getItem("bianji")
					if(data && bianji){
						sessionStorage.removeItem("editData")
						sessionStorage.removeItem("bianji")
					}
					if(path=='/salesOpportunitiesDetails'){
						sessionStorage.setItem('isshow',true)
					}
				}
				this.$router.push(path);
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
			},
			//新建
			toDoAddSheet(path){
				console.log(1111111,this.$router.push(path))
				this.$router.push(path);
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
