<template>
  <el-container style="height: 98%;width: 100%;position: absolute;">
    <el-header  height="40px" style="line-height: 86px;background-color: #409EFF ;color: #333;">
      <el-row type="flex" justify="space-between">
        <el-col :span="7" style="line-height: 40px">
          <router-link to="/home" style=" margin-top: 0px; font-size: 20px;font-weight: 900; color: white;">
            CRM客户关系管理系统
          </router-link>
        </el-col>
        <el-col :span="3">
          <el-form style="line-height: 40px; margin-left: 20px;font-size: 12px">
            <el-form-item label="">
              <!--							<el-avatar shape="square" style="margin-top: 5px" :size="30" :src="squareUrl"></el-avatar>-->
              <el-dropdown>
                  <span class="el-dropdown-link" style="font-size: 18px;color: #303133">
                   当前登录人：{{token.empName}}<i class="el-icon-arrow-down el-icon--right"></i>
                       </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="signout">切换用户</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-header>
    <el-container style="max-height: 100%;overflow: hidden;">
      <!-- 左边 -->
      <el-aside width="200px">

        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            unique-opened="true"
        >
          <el-sub-menu index="1">
            <template #title>
              <span>客户管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item @click="pushUrl('/customerManagement')" index="1-1">客户管理</el-menu-item>
              <el-menu-item @click="pushUrl('/linkMan')" index="1-2">联系人</el-menu-item>
              <el-menu-item @click="pushUrl('/customerTransfer')" index="1-3">客户转移</el-menu-item>
              <el-menu-item @click="pushUrl('/customerTransferRecord')" index="1-4">客户转移日志</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <span>售前服务</span>
            </template>
            <el-menu-item-group >
              <el-menu-item @click="pushUrl('/salesOpportunities')" index="2-1">销售机会</el-menu-item>
              <el-menu-item @click="pushUrl('/PriceSheet')" index="2-4">报价</el-menu-item>
              <el-menu-item @click="pushUrl('/priceSheetDetails')" index="2-5">报价明细</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>

          <el-sub-menu index="3">
            <template #title>
              <span>售中服务</span>
            </template>
            <el-menu-item-group >
              <el-menu-item @click="pushUrl('/order')" index="3-1">订单</el-menu-item>
              <el-menu-item @click="pushUrl('/orderDetails')" index="3-6">订单明细</el-menu-item>
              <el-menu-item @click="pushUrl('/shipments')" index="3-2">发货</el-menu-item>
              <el-menu-item @click="pushUrl('/planArrangement')" index="3-3">计划回款</el-menu-item>
              <el-menu-item @click="pushUrl('/plan')" index="3-4">回款</el-menu-item>
              <el-menu-item @click="pushUrl('/makeOutAnInvoice')" index="3-5">开票</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <span>售后服务</span>
            </template>
            <el-menu-item-group >
              <el-menu-item @click="pushUrl('/customerConsole')" index="4-1">客服控制台</el-menu-item>
              <el-menu-item @click="pushUrl('/customerService')" index="4-2">客户服务</el-menu-item>
              <el-menu-item @click="pushUrl('/customerComplaint')" index="4-3">投诉</el-menu-item>
              <el-menu-item @click="pushUrl('/customerCare')" index="4-4">客户关怀</el-menu-item>
              <el-menu-item @click="pushUrl('/qALibrary')" index="4-5">QA库</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>

          <el-sub-menu index="5">
            <template #title>
              <span>仓库管理</span>
            </template>
            <el-menu-item-group >
              <el-menu-item @click="pushUrl('/warehouse-2')" index="5-2">库存</el-menu-item>
              <el-menu-item @click="pushUrl('/putup')" index="5-3">出库</el-menu-item>
              <el-menu-item @click="pushUrl('/enter')" index="5-4">入库</el-menu-item>
              <el-menu-item @click="pushUrl('/product-1')" index="5-5">产品</el-menu-item>
              <el-menu-item @click="pushUrl('/product-class')" index="5-6">产品分类</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>

      </el-aside>
      <!-- 右边 -->
      <el-main style="padding: 0px 20px; color: #333;background-color: #E9EEF3">
        <crumb></crumb>
        <div class="works">
          <router-view v-if="isRouterAlive"></router-view>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data(){
    return{
      token:'',
	  isRouterAlive:true,
    }
  },
  provide(){
	return{
		reload:this.reload
	}
  },
  methods:{
    pushUrl(path){
      this.$router.push(path);
    },

	reload(){
		this.isRouterAlive=false
		this.$nextTick(()=>{
			this.isRouterAlive=true
		})
	},

    signout(){
      window.localStorage.clear()
      this.$router.push('/')
    }

  },
  created(){
    this.token=JSON.parse(localStorage.getItem("loginuser"))
    console.log(this.token,'2222')
  }
}
</script>

<style scoped>
a {
  color: #333333;
  text-decoration: none;
}
table{
  margin-top: 0px;
}
.works{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.dome{
  font-size: 16px;
}
body{
  margin: 0px;
}
</style>
