<template>
<div>机会明细</div>
<el-form :inline="true" ref="maxsearch" :model="addsales" class="demo-form-inline">
	<el-row>
		<el-col :span="4">
			销售机会
		</el-col>
		<el-col :span="14">
		</el-col>
		<el-col :span="6">
			<el-row>
				<el-col :span="24" v-if="show">
					 <el-button @click="cli()"><i class="el-icon-check"></i>保存</el-button>
				</el-col>
				<el-col :span="12" v-if="!show">
					 <el-button   @click="cli()"><i class="el-icon-edit"></i>编辑</el-button>
				</el-col>
				<el-col :span="12" v-if="!show">
					 <el-button  @click="cli()"><i class="el-icon-delete"></i>删除</el-button>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
	<el-divider></el-divider>
	<el-row>
		<el-col :span="4">
		</el-col>
		<el-col :span="16">
			<el-divider>
			  机会
			</el-divider>
		</el-col>
		<el-col :span="4">
		</el-col>
	</el-row>
	<el-row>
		<el-col :span="12">
			<el-form-item label="申请人" size="medium" label-width="130">
				<el-input  placeholder="请输入姓名：" v-model="searchMore.sename" clearable></el-input>
			</el-form-item>
		</el-col>
		<el-col :span="12">
			<el-form-item label="申请人" size="medium" label-width="130">
				<el-input  placeholder="请输入姓名：" v-model="searchMore.sename" clearable></el-input>
			</el-form-item>
		</el-col>
	</el-row>
</el-form>
	 
</template>

<script>
export default {
  name: 'SalesOpportunitiesDetalis',
  data(){
	  return{
		  addsales:{},
		  show:false
	  }
  }
}
</script>

<style scoped>
	.demo-form-inline{
		background-color: #FFFFFF;
	}
</style>
