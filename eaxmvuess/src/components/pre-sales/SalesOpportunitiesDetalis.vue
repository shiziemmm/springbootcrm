<template>
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
			<h3 style="color: #606266"><p style="float: left;line-height:40px;margin-left: 10px">机会明细</p></h3>
		</div>
	</div></el-col>
	<el-col :span="10"><div class="grid-content bg-purple">
	</div></el-col>
</el-row>
<el-form :inline="true" ref="maxsearch" :model="addsales" class="demo-form-inline">
		<el-row>
			<el-col :span="4" style="padding: 20px 10px 0px">
				<h2>销售机会</h2>
			</el-col>
			<el-col :span="16">
			</el-col>
			<el-col :span="4" style="padding: 20px 10px 0px">
				<el-row v-if="!isshow">
					<el-col :span="24" v-if="isedit">
						 <el-button @click="cli()"><i class="el-icon-check"></i>保存</el-button>
					</el-col>
					<el-col :span="12" v-if="!isedit">
						 <el-button   @click="editState()"><i class="el-icon-edit"></i>编辑</el-button>
					</el-col>
					<el-col :span="12" v-if="!isedit">
						 <el-button  @click="cli()"><i class="el-icon-delete"></i>删除</el-button>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="24">
						 <el-button @click="addsalesSubmit()"><i class="el-icon-check"></i>保存</el-button>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-divider></el-divider>
		<el-row>
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-divider>
				  机会
				</el-divider>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="机会主题" size="medium" label-width="130px">
							<el-input  placeholder="请输入机会主题：" v-model="addsales.opTheme" clearable></el-input>
						</el-form-item>
						<el-form-item label="客户" size="medium" label-width="130px">
							<el-row>
								<el-col :span="24">
									<el-select clearable v-model="addsales.clientId" filterable  @change="addlinkmen()" >
									  <el-option
									    v-for="item in clients"
									    :key="item.clientId"
									    :label="item.clientName"
									    :value="item.clientId">
									  </el-option>
									</el-select>
								</el-col>
								<!-- <el-col  :span="12">
									<el-button><i class="el-icon-edit"></i>搜索</el-button>
								</el-col> -->
							</el-row>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="更新日期" size="medium" label-width="130px">
							<!-- <div class="block">
							    <el-date-picker v-model="addsales.opDate" type="date" placeholder="Pick a day">
							    </el-date-picker>
							</div> -->
							<el-input  :disabled="true" v-model="addsales.opDate"></el-input>
						</el-form-item>
						<el-form-item label="状态" size="medium" label-width="130px">
							<el-radio-group v-model="addsales.opState">
								<el-radio label="跟踪">跟踪</el-radio>
								<el-radio label="成功">成功</el-radio>
								<el-radio label="失败">失败</el-radio>
								<el-radio label="搁置">搁置</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="!isshow">
			<el-col :span="4">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="机会主题:" size="medium" label-width="130px">
							<span>{{addsales.opTheme}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="更新日期:" size="medium" label-width="130px">
							<span>{{addsales.opDate}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="客户:" size="medium" label-width="130px">
							<span>{{addsales.client.clientName}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="状态:" size="medium" label-width="130px">
							<span>{{addsales.opState}}</span>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-divider>
				  基本情况
				</el-divider>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="客户联系人" size="medium" label-width="130px">
							<el-select clearable v-model="addsales.linkmanId">
							  <el-option
							    v-for="item in linkmen"
							    :key="item.linkmanId"
							    :label="item.linkmanName"
							    :value="item.linkmanId">
							  </el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="发现时间" size="medium" label-width="130px">
							<div class="block">
							    <el-date-picker v-model="addsales.opFindtime" type="date" placeholder="Pick a day">
							    </el-date-picker>
							</div>
						</el-form-item>
						<el-form-item label="负责人" size="medium" label-width="130px">
							<el-input :disabled="true" v-model="addsales.empName" clearable></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="类型" size="medium" label-width="130px">
							<el-select clearable v-model="addsales.opType">
							  <el-option
							    v-for="item in types"
							    :key="item"
							    :label="item"
							    :value="item">
							  </el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="来源" size="medium" label-width="130px">
							<el-select clearable v-model="addsales.opCuSource">
							  <el-option
							    v-for="item in sources"
							    :key="item"
							    :label="item"
							    :value="item">
							  </el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="提供人" size="medium" label-width="130px">
							<el-input  placeholder="请输入姓名：" v-model="addsales.opProvider" clearable></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="需求" size="medium" label-width="130px">
							<el-input
								v-model="addsales.opCuDemand"
							    :rows="2"
							    type="textarea"
							  />
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="!isshow">
			<el-col :span="4">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="客户联系人:" size="medium" label-width="130px">
							<span>{{addsales.linkman.linkmanNmae}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="类型:" size="medium" label-width="130px">
							<span>{{addsales.opType}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="发现时间:" size="medium" label-width="130px">
							<span>{{addsales.opFindtime}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="来源:" size="medium" label-width="130px">
							<span>{{addsales.opCuSource}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="负责人:" size="medium" label-width="130px">
							<span>{{addsales.emp.empName}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="提供人:" size="medium" label-width="130px">
							<span>{{addsales.opProvider}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="需求:" size="medium" label-width="130px">
							<span>{{addsales.opCuDemand}}</span>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-divider>
				  预期
				</el-divider>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="预期签单日期" size="medium" label-width="130px">
							<div class="block">
							    <el-date-picker v-model="addsales.opExpectedSigningDate" type="date" placeholder="Pick a day">
							    </el-date-picker>
							</div>
						</el-form-item>
						<el-form-item label="预期金额" size="medium" label-width="130px">
							<el-input  placeholder="请输入姓名：" v-model="addsales.opExpectedAmount" clearable></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="可能性" size="medium" label-width="130px">
							<el-select clearable v-model="addsales.opPossibility">
							  <el-option
							    v-for="item in possibility"
							    :key="item"
							    :label="item"
							    :value="item">
							  </el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="!isshow">
			<el-col :span="4">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="预期签单日期" size="medium" label-width="130px">
							<span>{{addsales.opExpectedSigningDate}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="可能性" size="medium" label-width="130px">
							<span>{{addsales.opPossibility}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="预期金额" size="medium" label-width="130px">
							<span>{{addsales.opExpectedAmount}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-divider>
				  当前状态
				</el-divider>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="3">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="阶段" size="medium" label-width="130px">
							<!-- <el-input  placeholder="请输入姓名：" v-model="addsales.opStage" clearable></el-input> -->
							<el-select clearable v-model="addsales.opStage" >
							  <el-option
							    v-for="item in stages"
							    :key="item"
							    :label="item"
							    :value="item">
							  </el-option>
							</el-select>
						</el-form-item>
						<!-- <el-form-item label="阶段停留" size="medium" label-width="130px">
							<el-input  placeholder="请输入姓名：" v-model="addsales.opPhaseStay" clearable></el-input>
						</el-form-item> -->
					</el-col>
					<el-col :span="12">
						<el-form-item label="优先" size="medium" label-width="130px">
							<el-select clearable v-model="addsales.opPriority" >
							  <el-option
							    v-for="item in prioritys"
							    :key="item"
							    :label="item"
							    :value="item">
							  </el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="阶段备注" size="medium" label-width="130px">
							<el-input
								v-model="addsales.opStageRemarks"
							    :rows="2"
							    type="textarea"
							  />
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="!isshow">
			<el-col :span="4">
			</el-col>
			<el-col :span="18">
				<el-row>
					<el-col :span="12">
						<el-form-item label="阶段:" size="medium" label-width="130px">
							<span>{{addsales.opStage}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="优先:" size="medium" label-width="130px">
							<span>{{addsales.opPriority}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="阶段停留:" size="medium" label-width="130px">
							<span>{{addsales.opPhaseStay}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="阶段备注:" size="medium" label-width="130px">
							<span>{{addsales.opStageRemarks}}</span>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="20">
			</el-col>
			<el-col :span="4">
				<el-button @click="addsalesSubmit()"><i class="el-icon-check"></i>保存</el-button>
			</el-col>
		</el-row>
	</el-form>
	<el-row v-if="!isshow || isedit">
		<el-col :span="24">
			<el-row>
				<el-col :span="12">
					<el-card>
						<h3>详细需求</h3>
					</el-card>
					<el-card>
						<h3>解决方案</h3>
					</el-card>
				</el-col>
				<el-col :span="12">
					<el-card @click="toRouter('/quotation')">
						<h3>报价单</h3>
					</el-card>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
	 
</template>

<script>
	import qs from 'qs'
	import dayjs from 'dayjs'
	import {ElMessage} from "element-plus";
export default {
  name: 'SalesOpportunitiesDetalis',
  data(){
	  return{
		  isedit:false,
		  isshow:true,
		  addsales:{
			  opDate:dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss'),
			  opState:"跟踪",
			  empId:this.$store.state.token.empId,
			  empName:this.$store.state.token.empName,
			  emp:{
				  empId:1,
				  empName:"admin",
				  empTel:"13332546466"
			  }
			  
		  },
		  possibility:['10%','20%','30%','40%','50%','60%','70%','80%','90%','100%'],
		  types:["建筑类","建筑类","建筑类"],
		  sources:["电话","活动"],
		  prioritys:["一级","二级","三级"],
		  stages:["初步沟通","需求分析","制定方案"],
		  clients:[],//客户
		  linkmen:[],//联系人
		 
	  }
	},
  methods:{
	  editState(){
		  this.isedit=true
		  this.isshow=true
	  },
	  editIsshow(){
		  var show=sessionStorage.getItem("isshow")
		  var data=JSON.parse(sessionStorage.getItem('editData'))
		  if(show){
			  if(show=="true"){
			  	this.isshow=true
			  }else if(show=="false" && data){
				  console.log("取到的值",data)
			  	this.isshow=false
				this.addsales=data
			  }
		  }
	  },
	  selectClients(){
		  console.log("ses",this.isshow)
		  this.axios.post("/client/selectbyname",qs.stringify({clientName:this.addsales.clientName})).then(res=>{
			  console.log("客户的数据",res)
			  this.clients=res.data.data
		  })
	  },
	  addlinkmen(){
		  if(this.clients.length>0 && this.addsales.clientId){
			  this.clients.forEach(v=>{
				  if(this.addsales.clientId==v.clientId){
					  this.addsales.clientName=v.clientName
					  this.linkmen=v.linkmen
				  }
			  })
			   this.selectClients()
			  
		  }
	  },
	  addsalesSubmit(){
		  console.log("1111",this.addsales)
		  if(this.addsales.opExpectedSigningDate){
			  this.addsales.opExpectedSigningDate=dayjs(this.addsales.opExpectedSigningDate).format("YYYY-MM-DD")
		  }
		  if(this.addsales.opFindtime){
		  	this.addsales.opFindtime=dayjs(this.addsales.opFindtime).format("YYYY-MM-DD")
		  }
		  this.axios.post("/opportunity/addopp",this.addsales).then(res=>{
			  console.log("新增之后",res)
			   ElMessage.success({
				  message: res.data.obj>0?"保存成功":"保存失败",
				  type: 'success'
			  });
		  })
	  },
	  toRouter(path){
		if(path=='/quotation'){
			if(this.addsales){
				sessionStorage.setItem('cli_opp',JSON.stringify(this.addsales))
			}
		}
		  this.$router.push(path);
	  }
  },
  created(){
	  this.selectClients()
	  this.editIsshow()
  }
}
</script>

<style scoped>
	.demo-form-inline{
			background-color: #FFFFFF;
			padding: 20px 0px;
		}
		.el-card{
			margin: 20px 20px 20px 0px;
			padding: 20px;
		}
		/deep/ .el-textarea__inner{
			width: 46vw;
		}
</style>
