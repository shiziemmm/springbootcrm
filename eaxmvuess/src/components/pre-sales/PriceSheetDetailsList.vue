<template>
<div>报价明细列表</div>
<div>机会明细</div>
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
						 <el-button @click="addsalesSubmit"><i class="el-icon-check"></i>保存</el-button>
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
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="更新日期:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="客户:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="状态：" size="medium" label-width="130px">
							<span>1111111</span>
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
							<el-input  placeholder="请输入姓名：" v-model="addsales.empId" clearable></el-input>
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
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="类型:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="发现时间:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="来源:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="负责人:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="提供人" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="需求" size="medium" label-width="130px">
							<span>1111111</span>
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
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="可能性" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="预期金额" size="medium" label-width="130px">
							<span>1111111</span>
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
						<el-form-item label="阶段" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="优先" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="阶段停留" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="阶段备注:" size="medium" label-width="130px">
							<span>1111111</span>
						</el-form-item>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
		<el-row v-if="isshow">
			<el-col :span="20">
			</el-col>
			<el-col :span="4">
				<el-button @click="cli()"><i class="el-icon-check"></i>保存</el-button>
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
					<el-card>
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
  name: 'PriceSheetDetailsList',
  data(){
	  return{
		  isedit:false,
		  isshow:true,
		  addsales:{
			  opDate:dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss'),
			  opState:"跟踪"
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
	  selectClients(){
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
	  }
  },
  created(){
	  this.selectClients()
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
