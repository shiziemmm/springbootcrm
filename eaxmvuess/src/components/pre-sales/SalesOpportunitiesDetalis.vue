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
<el-form :inline="true" ref="addsales" :model="addsales" class="demo-form-inline" :rules="addsalesrules">
		<el-row>
			<el-col :span="4" style="padding: 10px 10px 0px">
				<h2>销售机会</h2>
			</el-col>
			<el-col :span="16">
			</el-col>
			<el-col :span="4" style="padding: 10px 10px 0px">
				<el-row v-if="!isshow">
					<el-col :span="12" v-if="!isedit">
						 <el-button   @click="editState()"><i class="el-icon-edit"></i>编辑</el-button>
					</el-col>
					<el-col :span="12" v-if="!isedit">
						 <el-button  @click="tuiChu()"><i class="el-icon-delete"></i>退出</el-button>
					</el-col>
				</el-row>
				<el-row v-if="isshow">
					<el-col :span="12">
						 <el-button @click="addsalesSubmit('addsales')"><i class="el-icon-check"></i>保存</el-button>
					</el-col>
					<el-col :span="12" v-if="!isedit">
						 <el-button  @click="tuiChu()"><i class="el-icon-delete"></i>退出</el-button>
					</el-col>
					<!--  v-if="!ifedit"<el-col :span="24" v-else>
						 <el-button @click="editBaoCun()"><i class="el-icon-check"></i>保存</el-button>
					</el-col> -->
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
						<el-form-item label="机会主题" size="medium" label-width="130px" prop="opTheme">
							<el-input  placeholder="请输入机会主题：" v-model="addsales.opTheme" clearable></el-input>
						</el-form-item>
						<el-form-item label="客户" size="medium" label-width="130px" prop="clientId">
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
						<el-form-item label="更新日期" size="medium" label-width="130px" required>
							<!-- <div class="block">
							    <el-date-picker v-model="addsales.opDate" type="date" placeholder="选择时间">
							    </el-date-picker>
							</div> -->
							<el-input  :disabled="true" v-model="addsales.opDate"></el-input>
						</el-form-item>
						<el-form-item label="状态" size="medium" label-width="130px" required>
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
						<el-form-item label="机会主题:" size="medium" label-width="130px" required>
							<span>{{addsales.opTheme}}</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="更新日期:" size="medium" label-width="130px" required>
							<span>{{addsales.opDate}}</span>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="客户:" size="medium" label-width="130px" required>
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
						<el-form-item label="客户联系人" size="medium" label-width="130px" prop="linkmanId">
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
							    <el-date-picker v-model="addsales.opFindtime" type="date" placeholder="选择时间">
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
							    <el-date-picker v-model="addsales.opExpectedSigningDate" type="date" placeholder="选择时间">
							    </el-date-picker>
							</div>
						</el-form-item>
						<el-form-item label="预期金额" size="medium" label-width="130px" prop="opExpectedAmount">
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
						<el-form-item label="阶段" size="medium" label-width="130px" prop="opStage">
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
			<el-col :span="2" >
				<el-button @click="addsalesSubmit('addsales')"><i class="el-icon-check"></i>保存</el-button>
			</el-col>
			<el-col :span="2" v-if="!isedit">
				 <el-button  @click="tuiChu()"><i class="el-icon-delete"></i>退出</el-button>
			</el-col>
			<!-- v-if="!ifedit"<el-col :span="4" v-else>
				<el-button @click="editBaoCun()"><i class="el-icon-check"></i>保存</el-button>
			</el-col> -->
		</el-row>
	</el-form>
	<el-row v-if="!isshow || isedit">
		<el-col :span="24">
			<el-row>
				<el-col :span="12">
					<el-card>
						<h3 style="cursor: pointer;" @click="outerVisible1=true">详细需求 +</h3>
						<el-row v-for=" d in demands" :key="d.deId" style="margin: 10px 0px;border-bottom: #DDDDDD solid 1px;">
							<el-col :span="24" >
								<el-row>
									<el-col :span="24">
										<el-row>
											<el-col :span="8">
												主题：{{d.deTheme}}
											</el-col>
											<el-col :span="8">
												内容：{{d.deContent}}
											</el-col>
											<el-col :span="8">
												<el-row>
													<el-col :span="8">
													</el-col>
												  <el-col :span="8">
													  <el-time @click="outerVisible1=true,editDemand(d)">
														  <svg t="1636263015629" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5172" width="16" height="16"><path d="M851.8 927.9H172.2c-42 0-76.1-34.1-76.1-76.1V172.2c0-42 34.1-76.1 76.1-76.1h341.4c42 0 76.1 34.1 76.1 76.1s-34.1 76.1-76.1 76.1H248.3v527.5h527.5V507.3c0-42 34.1-76.1 76.1-76.1s76.1 34.1 76.1 76.1v344.5c-0.1 42-34.2 76.1-76.2 76.1z" fill="#666777" p-id="5173"></path><path d="M489.9 607c-19.5 0-38.9-7.4-53.8-22.3-29.7-29.7-29.7-77.9 0-107.6L793.3 120c29.7-29.7 77.9-29.7 107.6 0 29.7 29.7 29.7 77.9 0 107.6L543.7 584.8c-14.9 14.8-34.4 22.2-53.8 22.2z" fill="#666666" p-id="5174"></path></svg>
													  </el-time>
												  </el-col>
												  <el-col :span="8">
												    <!-- <i class="el-icon-delete"></i> -->
													  <el-time @click="del(d,'demands')">
														  <svg t="1636262937851" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3303" width="16" height="16"><path d="M457.772 718.3v-324.188c0-18.848-15.337-34.168-34.168-34.168-18.863 0-34.199 15.321-34.199 34.168v324.188c0 18.848 15.337 34.168 34.199 34.168 18.833 0 34.168-15.32 34.168-34.168z" p-id="3304"></path><path d="M777.342 360.711c-18.863 0-34.199 15.321-34.199 34.168v441.293c0 13.649-11.073 24.752-24.722 24.752h-412.66c-13.649 0-24.722-11.104-24.722-24.752v-442.49c0-18.848-15.337-34.183-34.199-34.183-18.863 0-34.199 15.337-34.199 34.183v442.49c0 51.345 41.775 93.12 93.12 93.12h412.66c51.345 0 93.12-41.775 93.12-93.12v-441.293c-0.001-18.847-15.337-34.168-34.201-34.168z" p-id="3305"></path><path d="M634.595 718.3v-324.188c0-18.848-15.337-34.168-34.199-34.168-18.833 0-34.168 15.321-34.168 34.168v324.188c0 18.848 15.337 34.168 34.168 34.168 18.863 0 34.199-15.32 34.199-34.168z" p-id="3306"></path><path d="M895.123 242.070h-142.625v-54.244c0-51.345-41.499-93.12-92.476-93.12h-295.372c-51.345 0-93.12 41.775-93.12 93.12v54.244h-142.625c-18.863 0-34.199 15.321-34.199 34.168s15.337 34.183 34.199 34.183h766.216c18.833 0 34.168-15.337 34.168-34.183s-15.336-34.168-34.168-34.168zM684.101 242.070h-344.201v-54.244c0-13.649 11.104-24.768 24.752-24.768h295.372c13.496 0 24.078 10.873 24.078 24.768v54.244z" p-id="3307"></path></svg>
													  </el-time>
												  </el-col>
												</el-row>
											</el-col>
										</el-row>
									</el-col>
								</el-row>
							</el-col>
						</el-row>
					</el-card>
					<el-card>
						<h3 style="cursor: pointer;" @click="outerVisible2=true">解决方案 +</h3>
						<el-row v-for=" p in programmes" :key="p.proId" style="margin: 10px 0px;border-bottom: #DDDDDD solid 1px;">
							<el-col :span="24" >
								<el-row>
									<el-col :span="24">
										<el-row>
											<el-col :span="8">
												主题：{{p.proTheme}}
											</el-col>
											<el-col :span="8">
												内容：{{p.proContent}}
											</el-col>
											<el-col :span="8">
												<el-row>
													<el-col :span="8">
													</el-col>
												  <el-col :span="8">
													  <el-time @click="outerVisible2=true,editProgramme(p)">
														  <svg t="1636263015629" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5172" width="16" height="16"><path d="M851.8 927.9H172.2c-42 0-76.1-34.1-76.1-76.1V172.2c0-42 34.1-76.1 76.1-76.1h341.4c42 0 76.1 34.1 76.1 76.1s-34.1 76.1-76.1 76.1H248.3v527.5h527.5V507.3c0-42 34.1-76.1 76.1-76.1s76.1 34.1 76.1 76.1v344.5c-0.1 42-34.2 76.1-76.2 76.1z" fill="#666777" p-id="5173"></path><path d="M489.9 607c-19.5 0-38.9-7.4-53.8-22.3-29.7-29.7-29.7-77.9 0-107.6L793.3 120c29.7-29.7 77.9-29.7 107.6 0 29.7 29.7 29.7 77.9 0 107.6L543.7 584.8c-14.9 14.8-34.4 22.2-53.8 22.2z" fill="#666666" p-id="5174"></path></svg>
													  </el-time>
												  </el-col>
												  <el-col :span="8">
												    <!-- <i class="el-icon-delete"></i> -->
													  <el-time @click="del(p,'programmes')">
														  <svg t="1636262937851" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3303" width="16" height="16"><path d="M457.772 718.3v-324.188c0-18.848-15.337-34.168-34.168-34.168-18.863 0-34.199 15.321-34.199 34.168v324.188c0 18.848 15.337 34.168 34.199 34.168 18.833 0 34.168-15.32 34.168-34.168z" p-id="3304"></path><path d="M777.342 360.711c-18.863 0-34.199 15.321-34.199 34.168v441.293c0 13.649-11.073 24.752-24.722 24.752h-412.66c-13.649 0-24.722-11.104-24.722-24.752v-442.49c0-18.848-15.337-34.183-34.199-34.183-18.863 0-34.199 15.337-34.199 34.183v442.49c0 51.345 41.775 93.12 93.12 93.12h412.66c51.345 0 93.12-41.775 93.12-93.12v-441.293c-0.001-18.847-15.337-34.168-34.201-34.168z" p-id="3305"></path><path d="M634.595 718.3v-324.188c0-18.848-15.337-34.168-34.199-34.168-18.833 0-34.168 15.321-34.168 34.168v324.188c0 18.848 15.337 34.168 34.168 34.168 18.863 0 34.199-15.32 34.199-34.168z" p-id="3306"></path><path d="M895.123 242.070h-142.625v-54.244c0-51.345-41.499-93.12-92.476-93.12h-295.372c-51.345 0-93.12 41.775-93.12 93.12v54.244h-142.625c-18.863 0-34.199 15.321-34.199 34.168s15.337 34.183 34.199 34.183h766.216c18.833 0 34.168-15.337 34.168-34.183s-15.336-34.168-34.168-34.168zM684.101 242.070h-344.201v-54.244c0-13.649 11.104-24.768 24.752-24.768h295.372c13.496 0 24.078 10.873 24.078 24.768v54.244z" p-id="3307"></path></svg>
													  </el-time>
												  </el-col>
												</el-row>
											</el-col>
										</el-row>
									</el-col>
								</el-row>
							</el-col>
						</el-row>
					</el-card>
				</el-col>
				<el-col :span="12">
					<el-card>
						<h3 style="cursor: pointer;" @click="toRouter('/quotation')">报价单 +</h3>
						<el-row v-for=" q in quotations" :key="q.quId" style="margin: 10px 0px;border-bottom: #DDDDDD solid 1px;">
							<el-col :span="24" >
								<el-row>
									<el-col :span="24">
										<el-row>
											<el-col :span="8">
												主题：{{q.quTheme}}
											</el-col>
											<el-col :span="8">
												报价：{{q.quTotalAmount}}元
											</el-col>
											<el-col :span="8">
											</el-col>
										</el-row>
									</el-col>
								</el-row>
								<el-row>
									<el-col :span="4">
									</el-col>
									<el-col :span="20">
										<el-row>
											<el-col :span="8">
												<!-- 编辑明细 -->
											</el-col>
											<el-col :span="8">
												<!-- 查看明细 -->
											</el-col>
											<el-col v-if="q.quState=='可见' && q.quState!='转成订单'" :span="8" @click="addOdr(q)">
												<span style="color: red;cursor: pointer;">转成订单</span>
											</el-col>
										</el-row>
									</el-col>
								</el-row>
							</el-col>
						</el-row>
					</el-card>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
	<el-dialog title="添加需求" v-model="outerVisible1" width="50%">
		<el-form ref="addDemand" :model="addDemand" :rules="addDemandrules">
			<el-row>
				<el-col :span="1">
				</el-col>
				<el-col :span="20">
					<el-row>
						<el-col :span="24">
							<el-form-item label="需求主题" size="medium" label-width="130px" prop="deTheme">
								<el-input style="width: 30vw;" v-model="addDemand.deTheme"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="客户" size="medium" label-width="130px">
								<el-input style="width: 30vw;" :disabled="true" v-model="addDemand.client.clientName"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="对应机会" size="medium" label-width="130px">
								<el-input style="width: 30vw;" :disabled="true" v-model="addDemand.opportunity.opTheme"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="10">
							<el-form-item label="需求提供人" size="medium" label-width="130px">
								<el-input v-model="addDemand.deProvider"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="14">
							<el-form-item label="记录日期" size="medium" label-width="130px">
								<div class="block">
								    <el-date-picker v-model="addDemand.deRecordTime" type="date" placeholder="选择时间">
								    </el-date-picker>
								</div>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="需求内容" size="medium" label-width="130px">
								<el-input style="width: 30vw;" v-model="addDemand.deContent"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-col>
			</el-row>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button type="primary" @click="addDemands('addDemand')">保存</el-button>
				<el-button type="primary" @click="outerVisible1=false">取消</el-button>
			</span>
		</template>
	</el-dialog>
	<el-dialog title="解决方案" v-model="outerVisible2" width="50%">
		<el-form ref="addProgramme" :model="addProgramme" :rules="addProgrammerules">
			<el-row>
				<el-col :span="1">
				</el-col>
				<el-col :span="20">
					<el-row>
						<el-col :span="24">
							<el-form-item label="方案主题" size="medium" label-width="130px" prop="proTheme">
								<el-input style="width: 30vw;" v-model="addProgramme.proTheme"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="客户" size="medium" label-width="130px">
								<el-input style="width: 30vw;" :disabled="true" v-model="addProgramme.client.clientName"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="10">
							<el-form-item label="对应机会" size="medium" label-width="130px">
								<el-input :disabled="true" v-model="addProgramme.opportunity.opTheme"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="14">
							<el-form-item label="记录日期" size="medium" label-width="130px">
								<div class="block">
								    <el-date-picker v-model="addProgramme.proRecordTime" type="date" placeholder="选择时间">
								    </el-date-picker>
								</div>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="方案内容" size="medium" label-width="130px">
								<el-input style="width: 30vw;" v-model="addProgramme.proContent"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="客户反馈" size="medium" label-width="130px">
								<el-input style="width: 30vw;" v-model="addProgramme.proCustomerFeedback"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-col>
			</el-row>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button type="primary" @click="addProgrammes('addProgramme')">保存</el-button>
				<el-button type="primary" @click="outerVisible2=false">取消</el-button>
			</span>
		</template>
	</el-dialog>
	 
</template>

<script>
	import qs from 'qs'
	import dayjs from 'dayjs'
	import {ElMessage} from "element-plus";
export default {
	inject:['reload'],//引入无痕刷新方法
  name: 'SalesOpportunitiesDetalis',
  data(){
	  return{
		  isedit:false,
		  isshow:true,
		  addsales:{
			  opDate:dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss'),
			  opState:"跟踪",
			  empId:this.$store.state.token.empId,
			 empId:JSON.parse(localStorage.getItem("loginuser")).empId,
			 emp:JSON.parse(localStorage.getItem("loginuser")),
		  },
		  possibility:['10%','20%','30%','40%','50%','60%','70%','80%','90%','100%'],
		  types:["建筑类","建筑类","建筑类"],
		  sources:["电话","活动"],
		  prioritys:["一级","二级","三级"],
		  stages:["初步沟通","需求分析","制定方案"],
		  clients:[],//客户
		  linkmen:[],//联系人
		 quotations:[],
		 opp:{},
		 ifedit:false,//是否编辑
		 addsalesrules:{
			 opTheme:[{required: true,message: '请输入机会主题！',trigger: 'blur'}],
			 clientId:[{required: true,message: '请选择客户！',trigger: 'change'}],
			 opStage:[{required: true,message: '请选择状态！',trigger: 'change'}],
			 opExpectedAmount:[{pattern:/^(([1-9]{1}\d*)|(0{1}))(\.{0}|\.{1}\d{1,2})$/,message: '请输入正确金额！',trigger: 'blur'}]
		 },
		 outerVisible1:false,
		 outerVisible2:false,
		 addDemand:{
			 client:{},
			 opportunity:{}
		 },
		 addDemandrules:{
			 deTheme:[{required: true,message: '请输入需求主题！',trigger: 'blur'}]
		 },
		 addProgramme:{
			 client:{},
			 opportunity:{}
		 },
		 addProgrammerules:{
			 proTheme:[{required: true,message: '请输入方案主题！',trigger: 'blur'}]
		 },
		 demands:[],
		 programmes:[]
	  }
	},
  methods:{
	  //删除方案和需求
	  del(row,name){
		  if(name=='demands'){
			  this.axios.get("/demand/del",{params:{
				  deid:row.deId
			  }}
			  ).then(res=>{
				console.log("ssss",res)	
				this.selectByOpId()
				this.reload()
			  }) 
		  }else if(name=='programmes'){
		  		this.axios.get("/programme/del",{params:{
				  proid:row.proId
			  }}
		  		).then(res=>{
					console.log("ssss",res)
					this.selectByOpId()
					this.reload()
		  		}) 	  
		  }
	  },
	  //修改需求
	  editDemand(row){
		  console.log("111111111111111",row)
		  this.addDemand.deTheme=row.deTheme
		  this.addDemand.deProvider=row.deProvider
		  this.addDemand.deRecordTime=row.deRecordTime
		  this.addDemand.deContent=row.deContent
		  this.addDemand.deId=row.deId
		  console.log(this.addDemand)
	  },
	  //修改方案
	  editProgramme(row){
		  console.log("111111111111111",row)
		  this.addProgramme.proTheme=row.proTheme
		  this.addProgramme.proRecordTime=row.proRecordTime
		  this.addProgramme.proContent=row.proContent
		  this.addProgramme.proCustomerFeedback=row.proCustomerFeedback
		  this.addProgramme.proId=row.proId
		  console.log(this.addProgramme)
	  },
	  //添加方案
	  addProgrammes(formname){
		  let $this=this
		  $this.$refs[formname].validate((valid) => {
			  console.log("表单信息：", $this.addProgramme, "一年挣结果：", valid);
			  if (valid) {
				  this.addProgramme.opId=this.addProgramme.opportunity.opId
				  this.addProgramme.clientId=this.addProgramme.client.clientId
				  this.addProgramme.empId=JSON.parse(localStorage.getItem("loginuser")).empId
				  if(this.addProgramme.proRecordTime){
					  this.addProgramme.proRecordTime=dayjs(this.addProgramme.proRecordTime).format('YYYY-MM-DD')
				  }
				  console.log(this.addProgramme)
				  this.axios.post("/programme/addprogramme",this.addProgramme
				  ).then(res=>{
					console.log("：",res)	
				  ElMessage.success({
					message: res.data.obj>0?"保存成功":"保存失败",
					type: 'success'
				  });
				  }) 
				 this.outerVisible2=false
				 this.selectByOpId()
				 this.reload()
		  }
		})
	  },
	  //添加需求
	  addDemands(formname){
		  let $this=this
		  $this.$refs[formname].validate((valid) => {
			  console.log("表单信息：", $this.addDemand, "一年挣结果：", valid);
			  if (valid) {
				  this.addDemand.opId=this.addDemand.opportunity.opId
				  this.addDemand.clientId=this.addDemand.client.clientId
				  this.addDemand.empId=JSON.parse(localStorage.getItem("loginuser")).empId
				  if(this.addDemand.deRecordTime){
					  this.addDemand.deRecordTime=dayjs(this.addDemand.deRecordTime).format('YYYY-MM-DD')
				  }
				  console.log(this.addDemand)
				  this.axios.post("/demand/adddemand",this.addDemand
				  ).then(res=>{
				  	console.log("：",res)	
				  ElMessage.success({
				    message: res.data.obj>0?"保存成功":"保存失败",
				    type: 'success'
				  });
				  }) 
				 this.outerVisible1=false
				 this.selectByOpId()
				 this.reload()
			  }
			})
	  },
	  //退出
	  tuiChu(){
		  this.$router.push("/salesOpportunities");
	  },
	  //转订单
	  addOdr(row){
		  console.log(row)
		  var sum=0;
		  var zong=0;
		  if(row.quotationDetails.length>0){
			  row.quotationDetails.forEach(v=>{
				  sum+=v.qdQuantity
				  zong+=v.qdProTotalAmount
			  })
		  }
		  let odr={
			  odrOn:row.quNo,
			  odrPrice:zong,
			  odrUsId:JSON.parse(localStorage.getItem("loginuser")).empId,
			  opId:this.opp.opId,
			  linkmanId:this.opp.linkman.linkmanId,
			  clientId:this.opp.client.clientId,
			  customerName:this.opp.client.clientName,
			  odrCount:sum,
			  quId:row.quId
		  }
		  let params={
			  odr:odr,
			  quotationDetails:row.quotationDetails
		  }
			this.axios.post("/opportunity/addodr",params
			).then(res=>{
				console.log("：",res)	
			ElMessage.success({
			  message: res.data.obj>0?"转订单成功":"转订单失败",
			  type: 'success'
			});
			this.reload()
			}) 
	  },
	  //根据机会id查找报价、需求、方案
	  selectByOpId(){
		   var data=JSON.parse(sessionStorage.getItem('editData'))
		   if(data && data.opId){
			   this.axios.get("/opportunity/selectbyid",{
				   params:{
					   opid:data.opId
				   }
			   }).then(res=>{
					console.log("机会数据：",res)
					this.quotations=res.data.obj.quotations
					this.opp=res.data.obj
					this.demands=res.data.obj.demands
					this.programmes=res.data.obj.programmes
			   })
		   }
	  },
	 //修改当前可视化状态
	  editState(){
		  this.isedit=true
		  this.isshow=true
		  // this.ifedit=true
		  this.addsales.opDate=dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss')
	  },
	  //判断是否显示，取出sessionStorage值赋值
	  editIsshow(){
		  var show=sessionStorage.getItem("isshow")
		  var data=JSON.parse(sessionStorage.getItem('editData'))
		  var bianji=sessionStorage.getItem("bianji")
		  if(data){
			  this.addDemand.client=data.client
			  this.addDemand.opportunity=data
			  this.addProgramme.client=data.client
			  this.addProgramme.opportunity=data
		  }
		  if(show){
			  if(show=="true"){
			  	this.isshow=true
				if(bianji){
					this.addsales=data
					this.addsales.opDate=dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss')
				}
			  }else if(show=="false" && data){
				  console.log("取到的值",data)
			  	this.isshow=false
				this.addsales=data
			  }
		  }
	  },
	  //查询客户和对应的联系人
	  selectClients(){
		  console.log("ses",this.isshow)
		  this.axios.post("/client/selectbyname",qs.stringify({clientName:this.addsales.clientName})).then(res=>{
			  console.log("客户的数据",res)
			  this.clients=res.data.data
		  })
	  },
	  //选择客户后绑定对应的联系人
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
	  //新增销售机会
	  addsalesSubmit(formname){
		  let $this=this
		  $this.$refs[formname].validate((valid) => {
			  console.log("表单信息：", $this.addsales, "一年挣结果：", valid);
			  if (valid) {
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
						  sessionStorage.setItem('editData',JSON.stringify(this.addsales))
						  var show=sessionStorage.getItem("isshow")
						  if(show=='true'){
							  this.$router.push("/salesOpportunities");
						  }else{
							  this.reload()
						  }
				  })
			  }
		  })
	  },
	  //跳转页面（报价单）
	  toRouter(path){
		if(path=='/quotation'){
			var show=sessionStorage.getItem("todo")
			if(show){
				sessionStorage.removeItem("todo")
			}
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
	  this.selectByOpId()
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
