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
							<el-col :span="24">
								 <el-button @click="addPriceSheetSubmit('addPriceSheet')"><i class="el-icon-check"></i>保存</el-button>
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
								<el-form-item label="客户" size="medium" label-width="130px">
									<el-row>
										<el-col :span="24">
											<el-input  :disabled="true" style="width: 46vw;" v-model="addPriceSheet.client.clientName"></el-input>
										</el-col>
									</el-row>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="状态" size="medium" label-width="130px">
									<el-radio-group v-model="addPriceSheet.quState" :disabled="true">
										<el-radio label="可见">可见</el-radio>
										<el-radio label="不可见">不可见</el-radio>
										<el-radio label="转订单">转成订单</el-radio>
									</el-radio-group>
								</el-form-item>
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
									    <el-date-picker v-model="addPriceSheet.quData" type="date" placeholder="Pick a day">
									    </el-date-picker>
									</div>
								</el-form-item>
								<el-form-item label="预估毛利" size="medium" label-width="130px">
									<el-input  :disabled="true" v-model="addPriceSheet.quEstimatedGrossProfit"></el-input>
								</el-form-item>
							</el-col>
							<el-form-item label="机会" size="medium" label-width="130px">
								<el-row>
									<el-col :span="24">
										<el-input  :disabled="true" style="width: 46vw;" v-model="addPriceSheet.opportunity.opTheme"></el-input>
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
					<el-col :span="4">
						<el-button @click="addPriceSheetSubmit('addPriceSheet')"><i class="el-icon-check"></i>保存</el-button>
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
	 <div v-if="todo">
		 <el-row style="padding: 8px">
				 <el-col>
				   <el-table
					   :header-cell-style="{textAlign: 'center'}"
					   :cell-style="{ textAlign: 'center' }" show-summary
					   :data="detailsArr" size="medium">
					 <el-table-column prop="odrdlProductName"  label="商品名" />
					 <el-table-column prop="odrdlMoney"   label="进价" />
					 <el-table-column prop="odrdlUnitPrice"   label="售价" >
					   <template #default="obj">
						 <el-input size="mini" v-model="obj.row.odrdlUnitPrice" onkeyup="value=value.replace(/[^\d^\.]+/g,'').replace('.','$#$').replace(/\./g,'').replace('$#$','.')"  placeholder="售价" type="text" />
					   </template>
					 </el-table-column>
					 <el-table-column prop="odrdlCount"   label="数量" >
					   <template #default="obj">
						 <el-input-number size="mini" :min="1" v-model="obj.row.qdQuantity" onkeyup = "value=value.replace(/[^\d]/g,'')" placeholder="产品数量" type="text" @click="sumPrice(obj.row)"/>
					   </template>
					 </el-table-column>
					 <el-table-column prop="odrdlRemark"  label="小计" >
					   <template #default="obj">
						 <el-input size="mini" :disabled="true" v-model="obj.row.qdProTotalAmount" onkeyup="value=value.replace(/[^\d^\.]+/g,'').replace('.','$#$').replace(/\./g,'').replace('$#$','.')"  placeholder="小计" type="text" />
					   </template>
					 </el-table-column>
					 <el-table-column label="操作" style="text-align: center;">
					   <template #default="obj">
						 <el-button size="mini" @click="deleteProduct(obj)" type="danger">移除</el-button>
					   </template>
					 </el-table-column>
				   </el-table>
				 </el-col>
			   </el-row>
			 <el-row>
			   <el-col :offset="11"  :span="2">
				 <el-button  @click="addQuDetails"  size="mini" :disabled="detailsArr.length == 0" type="primary">保存明细数据</el-button>
			   </el-col>
			   <el-col :offset="9" :span="2">
				 <el-button circle @click="isShowAddOrder = true" size="mini" style="font-size: 60px;width: 60px;height: 60px;line-height: 38px;" type="primary">+</el-button>
			   </el-col>
			 </el-row>
			 <!--添加商品抽屉-->
			 <el-drawer v-model="isShowAddOrder"  :with-header="false">
		 
			   <p style="color: black;font-size: 16px; margin-bottom:15px">添加产品</p>
		 
			   <!--搜索按钮-->
			   <el-row>
				 <el-col>
				   <el-input
					   placeholder="根据产品名称以及类型搜索"
					   v-model="selectParams.keyword"
					   class="input-with-select">
					 <template #append>
					   <el-button @click="initProduct">搜索</el-button>
					 </template>
				   </el-input>
				 </el-col>
			   </el-row>
		 
			   <!--显示商品表格-->
			   <el-row>
				 <el-col>
				   <el-table :data="productArr" @cell-dblclick="addProductFun" size="mini">
					 <el-table-column prop="prName"  label="商品名" />
					 <el-table-column prop="prCostPrice"   label="进价" />
					 <el-table-column prop="prPrice"   label="售价" />
					 <el-table-column prop="prDodel"   label="类型" />
					 <el-table-column prop="prUnit"  label="单位" />
				   </el-table>
				 </el-col>
			   </el-row>
			 </el-drawer>
		 </div>
</template>

<script>
	import dayjs from 'dayjs'
	import {ElMessage} from "element-plus";
	// empId:this.$store.state.token.empId,
	export default{
		inject:['reload'],//引入无痕刷新方法
		data(){
			 return{
				  addPriceSheet:{
					  client:{},
					  opportunity:{},
					  quState:"可见",
					  empId:JSON.parse(localStorage.getItem("loginuser")).empId,
					  emp:JSON.parse(localStorage.getItem("loginuser")),
					  quData:dayjs(new Date()).format('YYYY-MM-DD'),
				  },
				  isshow:true,
				  outerVisible:false,
				  todo:false,
				  //查询条件
				selectParams:{
				  keyword:'',
				},
				 detailsArr:[],//报价商品明细
				  isShowAddOrder:false,//是否显示添加产品抽屉
				  sum:0,
				  addPriceSheetrules:{
					  quTheme:[{required: true,message: '请输入报价主题！',trigger: 'blur'}],
					  quTotalAmount:[{pattern:/^(([1-9]{1}\d*)|(0{1}))(\.{0}|\.{1}\d{1,2})$/,message: '请输入正确金额！',trigger: 'blur'}]
				  },
				  datils:[],
			 }
		},
		methods:{
			init(){
				var j=JSON.parse(sessionStorage.getItem("cli_opp"))
				console.log("保存的数据",j)
				if(j){
					this.addPriceSheet.client=j.client
					this.addPriceSheet.clientId=j.client.clientId
					this.addPriceSheet.opportunity=j
					this.addPriceSheet.opId=j.opId
					this.addPriceSheet.quId=j.quId
				}
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
							  sessionStorage.setItem("quId",res.data.obj)
							  this.outerVisible=true
							  sessionStorage.setItem("todo",true)
						  }else{
							  ElMessage.error({
								  message:"保存失败",
								  type: 'error'
							  });
						  }
						  if(!this.todo && res.data.obj>0){
							  setTimeout(function () {
								sessionStorage.setItem("todo",true);
							  },1000);
						  }
					  })
				  }
				})
				
			},
			editToDo(){
			  var show=sessionStorage.getItem("todo")
			  var tianjia=sessionStorage.getItem("tianjia")
			  if(show){
				  if(show=="true"){
					this.todo=true
					if(tianjia){
						var editData=JSON.parse(sessionStorage.getItem("editData"));
						if(editData){
							this.axios.get('quotation/selectbyid',{
								params:{
									quid:editData.quId
								}
							}).then(res=>{
								console.log("232",res)
								if(res.data.obj){
									if(res.data.obj.quotationDetails && res.data.obj.quotationDetails.length>0){
										res.data.obj.quotationDetails.forEach(v=>{
											var details={}
											details.qdQuantity = v.qdQuantity;//产品数量
											details.prId = v.prId;//产品编号
											details.odrdlProductName = v.product.prName;//产品名称
											details.odrdlMoney = v.product.prCostPrice;//进价
											details.odrdlUnitPrice = v.product.prPrice;//售价
											details.qdProTotalAmount = v.qdQuantity*v.product.prPrice;//小计
											details.qdId=v.qdId
											this.detailsArr.push(details);
											console.log("datas",this.detailsArr)
										})
									}
								}
							  })
						}
					}
				  }else if(show=="false"){
					this.todo=false
				  }
			  }
			},
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
				this.addPriceSheet.quNo=dayjs(new Date()).format('HHmmss')+code
			},
			editState(){
				 this.todo=true;
			},
			initProduct(){
			      this.axios.post('product/findAll',this.selectParams).then(res=>{
			        this.productArr=res.data.data.records
			      })
			    },
			sumPrice(row){
				console.log(111111,row)
				this.sum=row.odrdlUnitPrice*row.qdQuantity
				this.detailsArr.forEach(v=>{
					if(v.prId==row.prId){
						v.qdProTotalAmount=this.sum
					}
				})
			},
			addQuDetails(){
				// sessionStorage.getItem("quId")
				var quId=parseInt(sessionStorage.getItem("quId"))
				console.log("sedrs",quId)
				var sum=0
				var jin=0
				var shou=0
				var tianjia=sessionStorage.getItem("tianjia")
				if(this.detailsArr.length>0 && quId>0){
					this.detailsArr.forEach(v=>{
						v.quId=quId
						v.empId=JSON.parse(localStorage.getItem("loginuser")).empId
						console.log("222",v.qdProTotalAmount)
						sum+=v.qdProTotalAmount
						jin+=v.odrdlMoney*v.qdQuantity//计算进价
						shou+=v.odrdlUnitPrice*v.qdQuantity//计算售价
						v.sum=sum//总报价
						v.mao=shou-jin//毛利
					})
				}
				
				console.log(this.detailsArr,sum)
				this.axios.post('quotationDetails/adddetails',this.detailsArr).then(res=>{
					console.log(res)
					ElMessage.success({
						  message: res.data.obj>0?"保存成功":"保存失败",
						  type: 'success'
					});
					if(res.data.obj>0){
						if(sessionStorage.getItem("todo")){
							sessionStorage.removeItem("todo")
							// this.reload()
							if(!tianjia){
								this.$router.push("/salesOpportunitiesDetails");
							}else{
								this.$router.push("/PriceSheet");
							}
						}
					}
				})
			},
			addProductFun(obj){
			  let orderDetailsDx = {};
			  for (let i = 0; i < this.detailsArr.length; i++) {
				if(this.detailsArr[i].prId == obj.prId){//如果已有此产品就数量加一
				  this.detailsArr[i].qdQuantity = this.detailsArr[i].qdQuantity + 1;
				  return;
				}
			  }
			  console.log(obj.prCostPrice)
			  orderDetailsDx.qdQuantity = 1;//产品数量
			  orderDetailsDx.odrOn = this.odrOn;//订单号
			  orderDetailsDx.prId = obj.prId;//产品编号
			  orderDetailsDx.odrdlProductName = obj.prName;//产品名称
			  orderDetailsDx.odrdlMoney = obj.prCostPrice;//进价
			  orderDetailsDx.odrdlUnitPrice = obj.prPrice;//售价
			  orderDetailsDx.qdProTotalAmount = obj.prPrice;//小计
			  this.detailsArr.push(orderDetailsDx);
			  console.log(this.detailsArr)
			},
			deleteProduct(obj) {
			  this.detailsArr.splice(obj.$index, 1);
			},
			
		},
		created(){
			this.createQuNo()
			this.editToDo()
			this.initProduct();//初始化
			this.init()
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
