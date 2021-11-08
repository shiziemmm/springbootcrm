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
		            <h3 style="color: #606266"><p style="float: left;line-height:40px;margin-left: 10px">销售机会</p></h3>
		        </div>
		    </div></el-col>
		    <el-col :span="10"><div class="grid-content bg-purple">
		    </div></el-col>
		</el-row>
      <el-form v-model="formData">
        <el-row>
          <el-col :span="3" style="padding-left: 10px;">
            <el-form-item label="" size="medium" label-width="130">
              <el-select v-model="formData.stateStage" placeholder="请选择" @change="selectList()">
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
                  <el-input v-model="formData.selectType" placeholder="请输入内容"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="" size="medium">
                  <!-- <el-button  class="addbtn" style="width: 100px;" @click="selectList()">机会主题</el-button> -->
				  <el-select v-model="formData.selectTypes" clearable @change="selectList()">
				    <el-option
				      v-for="item in options2"
				      :key="item"
				      :label="item"
				      :value="item">
				    </el-option>
				  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="13">
          </el-col>
          <el-col :span="2">
            <el-form-item label="" size="medium">
              <el-button class="addbtn"  @click="toRouter('/salesOpportunitiesDetails','add')"><i class="el-icon-plus"></i>新建</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="opId" label="ID" />
        <el-table-column prop="opTheme" label="机会主题"  />
        <el-table-column prop="client.clientName" label="客户" />
        <el-table-column prop="opPriority" label="优先"/>
        <el-table-column prop="opState" label="状态" />
        <el-table-column prop="emp.empName" label="负责人" />
        <el-table-column prop="opExpectedSigningDate" label="预计签单日期"  />
        <el-table-column prop="opExpectedAmount" label="预计金额" />
        <el-table-column prop="opPossibility" label="可能性" />
        <!-- <el-table-column prop="opPhaseStay" label="阶段停留" /> -->
		<el-table-column prop="opStage" label="阶段" />
        <el-table-column prop="opDate" label="更新日期" />
        <el-table-column prop="name" label="操作" >
			<template #default="scope">
				<el-row>
				  <el-col :span="8">
					<el-time @click="toRouter('/salesOpportunitiesDetails','edit',scope.row)">
						<svg t="1636262869010" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1328" width="16" height="16"><path d="M860 323.1v-2.6l-0.7-1-0.2-1.1-0.4-0.4c-0.5-1.4-1.1-2.8-1.9-4.2-0.3-0.5-0.5-1-0.8-1.4l-0.1-0.2c-0.5-0.8-1-1.6-1.5-2.3v-0.8L610.6 77.4v-0.9l-4.1-3-1.1-1.1-0.4-0.4-1.3-1.3h-0.7c-0.9-0.6-1.8-1.1-2.7-1.7-0.5-0.3-1.1-0.6-1.8-0.9-2.6-1.2-5-2-7.6-2.5l-0.8-0.2h-0.7c-1.7-0.3-3.3-0.4-4.9-0.4H234c-38.9 0-70.5 30.4-70.5 67.8V891c0 37.4 31.6 67.9 70.5 67.9h556c38.9 0 70.5-30.4 70.5-67.9V327.9c-0.1-1.6-0.2-3.2-0.5-4.8zM616.7 170.5l132.9 126.3H616.7V170.5z m179.5 720.6c0 3-2.8 5.4-6.2 5.4H234c-3.4 0-6.2-2.4-6.2-5.4V132.9c0-3 2.8-5.4 6.2-5.4h318.4V328c0 17.2 14.4 31.2 32.1 31.2h211.7v531.9z" fill="" p-id="1329"></path><path d="M686.4 697.5H337.6c-17.1 0-31.1 14.4-31.1 32.1 0 17.7 13.9 32.1 31.1 32.1h348.9c17.1 0 31.1-14.4 31.1-32.1-0.1-17.7-14-32.1-31.2-32.1zM686.4 563.2H337.6c-17.1 0-31.1 14.4-31.1 32.1 0 17.7 13.9 32.1 31.1 32.1h348.9c17.1 0 31.1-14.4 31.1-32.1-0.1-17.7-14-32.1-31.2-32.1zM306.5 461c0 17.7 13.9 32.1 31.1 32.1h348.9c17.1 0 31.1-14.4 31.1-32.1 0-17.7-14-32.1-31.1-32.1H337.6c-17.2 0-31.1 14.4-31.1 32.1z" fill="" p-id="1330"></path></svg>
					</el-time>
				  </el-col>
				  <el-col :span="8">
				    <!-- <i class="el-icon-edit"></i> -->
					  <el-time  @click="toRouter('/salesOpportunitiesDetails','editData',scope.row)">
						  <svg t="1636263015629" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5172" width="16" height="16"><path d="M851.8 927.9H172.2c-42 0-76.1-34.1-76.1-76.1V172.2c0-42 34.1-76.1 76.1-76.1h341.4c42 0 76.1 34.1 76.1 76.1s-34.1 76.1-76.1 76.1H248.3v527.5h527.5V507.3c0-42 34.1-76.1 76.1-76.1s76.1 34.1 76.1 76.1v344.5c-0.1 42-34.2 76.1-76.2 76.1z" fill="#666777" p-id="5173"></path><path d="M489.9 607c-19.5 0-38.9-7.4-53.8-22.3-29.7-29.7-29.7-77.9 0-107.6L793.3 120c29.7-29.7 77.9-29.7 107.6 0 29.7 29.7 29.7 77.9 0 107.6L543.7 584.8c-14.9 14.8-34.4 22.2-53.8 22.2z" fill="#666666" p-id="5174"></path></svg>
					  </el-time>
				  </el-col>
				  <el-col :span="8">
				    <!-- <i class="el-icon-delete"></i> -->
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
	import {ElMessage} from "element-plus";
	export default{
     name: 'salesOpportunities',
		data(){
			return{
				pageno:1,
				pagesize:5,
				total:0,
				formData:{
					shuju:''
				},
				options: [{
				          label: '阶段',
				          options: [{
				            value: '初步沟通',
				            label: '初步沟通'
				          }, {
				            value: '需求分析',
				            label: '需求分析'
				          },{
						    value: '制定方案',
						    label: '制定方案'
						  }]
				        }, {
				          label: '状态',
				          options: [{
				            value: '跟踪',
				            label: '跟踪'
				          }, {
				            value: '成功',
				            label: '成功'
				          }, {
				            value: '失败',
				            label: '失败'
				          }, {
				            value: '搁置',
				            label: '搁置'
				          }]
				        }],
						options2:["机会主题","客户名","负责人"],
				tableData:[]
			}
		},
		methods:{
			cli(){
				console.log(this.drawer)
			},
			//条件分页查询
			selectList(){
				if(!this.formData.selectTypes){
					this.formData.selectType=""
				}
				this.formData.pageNo=this.pageno
				this.formData.pageSize=this.pagesize
				this.axios.post("/opportunity/selectlist",this.formData).then(res=>{
				  console.log("机会的数据",res)
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
					this.axios.get("/opportunity/delete",{
						params:{
							opid:row.opId
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
					if(path=='/salesOpportunitiesDetails'){
						sessionStorage.setItem('isshow',true)
						if(row){
							sessionStorage.setItem('bianji','bianji')
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
