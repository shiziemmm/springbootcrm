<template>
	<div>报价</div>
	<div v-if="!todo">
		<el-form :inline="true" ref="addPriceSheet" :model="addPriceSheet" class="demo-form-inline" :rules="addPriceSheetrules">
				<el-row>
					<el-col :span="5" style="padding: 20px 10px 0px">
						<h2>报价/预下单</h2>
					</el-col>
					<el-col :span="15">
					</el-col>
					<el-col :span="4" style="padding: 20px 10px 0px">
						<el-row v-if="isshow">
							<el-col :span="12">
								 <el-button @click="addPriceSheetSubmit('addPriceSheet')"><i class="el-icon-check"></i>保存</el-button>
							</el-col>
							<el-col :span="12">
								 <el-button @click="tuiChu('/PriceSheet')"><i class="el-icon-check"></i>退出</el-button>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
				<el-divider></el-divider>
				<el-row v-if="isshow">
					<el-col :span="3">
					</el-col>
					<el-col :span="18">
						<el-row>
							<el-col :span="12">
								<el-form-item label="主题" size="medium" label-width="130px" prop="quTheme">
									<el-input  placeholder="请输入主题：" v-model="addPriceSheet.quTheme" clearable></el-input>
								</el-form-item>
								<el-form-item label="客户" size="medium" label-width="130px" prop="clientId">
									<el-row>
										<el-col :span="24">
										<el-select clearable v-model="addPriceSheet.clientId" filterable style="width: 46vw;" >
										  <el-option
											v-for="item in client"
											:key="item.clientId"
											:label="item.clientName"
											:value="item.clientId">
										  </el-option>
										</el-select>
										</el-col>
									</el-row>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<!-- <el-form-item label="状态" size="medium" label-width="130px">
									<el-radio-group v-model="addPriceSheet.quState">
										<el-radio label="可见">可见</el-radio>
										<el-radio label="不可见">不可见</el-radio>
										<el-radio label="转订单">转成订单</el-radio>
									</el-radio-group>
								</el-form-item> -->
							</el-col>
						</el-row>
					</el-col>
				</el-row>
				<el-row v-if="!isshow">
					<el-col :span="3">
					</el-col>
					<el-col :span="18">
						<el-row>
							<el-col :span="12">
								<el-form-item label="主题" size="medium" label-width="130px">
									<span>{{addPriceSheet.quTheme}}</span>
								</el-form-item>
								<el-form-item label="客户" size="medium" label-width="130px">
									<span>{{addPriceSheet.client.clientName}}</span>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="状态" size="medium" label-width="130px">
									<span>{{addPriceSheet.quState}}</span>
								</el-form-item>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="3">
					</el-col>
					<el-col :span="18">
						<el-row>
							<el-col :span="12">
								<el-form-item label="报价单号" size="medium" label-width="130px">
									<el-input  :disabled="true" v-model="addPriceSheet.quNo"></el-input>
								</el-form-item>
								<el-form-item label="报价(总)" size="medium" label-width="130px" prop="quTotalAmount">
									<el-input  placeholder="请输入报价(总)：" v-model="addPriceSheet.quTotalAmount" clearable></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="日期" size="medium" label-width="130px">
									<div class="block">
									    <el-date-picker v-model="addPriceSheet.quData" type="date" placeholder="选择时间">
									    </el-date-picker>
									</div>
								</el-form-item>
								<el-form-item label="预估毛利" size="medium" label-width="130px">
									<el-input  :disabled="true" v-model="addPriceSheet.quEstimatedGrossProfit"></el-input>
								</el-form-item>
							</el-col>
							<el-form-item label="机会" size="medium" label-width="130px" prop="opId">
								<el-row>
									<el-col :span="24">
										<el-select clearable v-model="addPriceSheet.opId" filterable style="width: 46vw;" >
										  <el-option
											v-for="item in opportunity"
											:key="item.opId"
											:label="item.opTheme"
											:value="item.opId">
										  </el-option>
										</el-select>
									</el-col>
								</el-row>
							</el-form-item>
						</el-row>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="3">
					</el-col>
					<el-col :span="18">
						<!-- v-model="addPriceSheet.emp.empName" -->
						<el-row>
							<el-col :span="12">
								<el-form-item label="报价人" size="medium" label-width="130px">
									<el-input :disabled="true" placeholder="请输入姓名：" v-model="addPriceSheet.emp.empName" clearable></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<!-- v-model="addPriceSheet.emp.empTel" -->
								<el-form-item label="报价人联系方式" size="medium" label-width="130px">
									<el-input :disabled="true" placeholder="请输入姓名：" v-model="addPriceSheet.emp.empTel" clearable></el-input>
								</el-form-item>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="3">
					</el-col>
					<el-col :span="18">
						<el-row>
							<el-col :span="24">
								<el-form-item label="交付说明" size="medium" label-width="130px">
									<el-input
										v-model="addPriceSheet.quDeliveryInstructions"
									    :rows="2"
									    type="textarea"
									  />
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="24">
								<el-form-item label="备注" size="medium" label-width="130px">
									<el-input
										v-model="addPriceSheet.quRemarks"
									    :rows="2"
									    type="textarea"
									  />
								</el-form-item>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="20">
					</el-col>
					<el-col :span="2">
						<el-button @click="addPriceSheetSubmit('addPriceSheet')"><i class="el-icon-check"></i>保存</el-button>
					</el-col>
					<el-col :span="2">
						 <el-button @click="tuiChu('/PriceSheet')"><i class="el-icon-check"></i>退出</el-button>
					</el-col>
				</el-row>
			</el-form>
			 <el-dialog title="即将跳转页面" v-model="outerVisible" width="50%">
				<span>保存成功!将要前往商品添加页面</span>
					<template #footer>
					  <span class="dialog-footer">
						<el-button type="primary" @click="editState">确定</el-button>
					  </span>
					</template>
			 </el-dialog>
	</div>
</template>

<script>
	import dayjs from 'dayjs'
	import qs from 'qs'
	import {ElMessage} from "element-plus";
	// empId:this.$store.state.token.empId,
	export default{
		inject:['reload'],//引入无痕刷新方法
		data(){
			 return{
				  addPriceSheet:{
					  quState:"可见",
					  empId:JSON.parse(localStorage.getItem("loginuser")).empId,
					  emp:JSON.parse(localStorage.getItem("loginuser")),
					  quData:dayjs(new Date()).format('YYYY-MM-DD'),
					  client:{
						  clientId:2
					  },
					  opportunity:{}
				  },
				  client:[],
				  opportunity:[],
				  isshow:true,
				  todo:false,
				  addPriceSheetrules:{
					  quTheme:[{required: true,message: '请输入报价主题！',trigger: 'blur'}],
					  quTotalAmount:[{pattern:/^(([1-9]{1}\d*)|(0{1}))(\.{0}|\.{1}\d{1,2})$/,message: '请输入正确金额！',trigger: 'blur'}],
					  clientId:[{required: true,message: '请选择对应的客户！',trigger: 'blur'}],
					  opId:[{required: true,message: '请选择对应的机会！',trigger: 'blur'}],
				  }
			 }
		},
		methods:{
			//退出
			tuiChu(path){
				this.$router.push(path);
			},
			//查询客户
			selectClients(){
			  this.axios.post("/client/selectbyname",qs.stringify({clientName:this.addPriceSheet.clientName})).then(res=>{
				  console.log("客户的数据",res)
				  this.client=res.data.data
			  })
			},
			//查询机会
			selectOpportunity(){
			  this.axios.get("/opportunity/selectall",).then(res=>{
				  console.log("机会的数据",res)
				  this.opportunity=res.data.data
			  })
			},
			addPriceSheetSubmit(formname){
				let $this=this
				$this.$refs[formname].validate((valid) => {
				  console.log("表单信息：", $this.addPriceSheet, "一年挣结果：", valid);
				  if (valid) {
					  if(this.addPriceSheet.quData){
						this.addPriceSheet.quData=dayjs(this.addPriceSheet.quData).format("YYYY-MM-DD")
					  }
					  console.log(this.addPriceSheet)
					  this.axios.post("/quotation/addquotation",this.addPriceSheet).then(res=>{
						  console.log("新增之后",res)
						  if(res.data.obj>0){
							  ElMessage.success({
								  message:"保存成功",
								  type: 'success'
							  });
							  this.$router.push("/PriceSheet");
						  }else{
							  ElMessage.error({
								  message:"保存失败",
								  type: 'error'
							  });
						  }
					  })
				  }
				})
			},
			//生成报价单号
			createQuNo() {
				var code ='';
				//设置长度，这里看需求，我这里设置了4
				var codeLength = 4;
				//设置随机字符
				var random = new Array(0,1,2,3,4,5,6,7,8,9);
				//循环codeLength我设置的4就是循环4次
				for (var i = 0; i < codeLength; i++) {
					//设置随机数范围,这设置为0 ~ 36
					var index = Math.floor(Math.random()* 9);
					//字符串拼接将每次随机的字符进行拼接
					code += random[index];
				}
				//将拼接好的字符串赋值给展示的code
				this.addPriceSheet.quNo="qu"+dayjs(new Date()).format('DDHHmmss')+code
			},
			
		},
		created(){
			this.createQuNo()
			this.selectOpportunity()
			this.selectClients()
		}
		
	}
</script>

<style scoped>
	.div-class{
	  /*padding: 8px;*/
	  background: #DDDDDD;
	  position: absolute;
	  width: 100%;
	  height: 100%;
	}
	.demo-form-inline{
				background-color: #FFFFFF;
				padding: 20px 0px;
			}
			/deep/ .el-textarea__inner{
				width: 46vw;
			}
</style>
