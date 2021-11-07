import {createRouter,createWebHistory} from "vue-router"
//定义组件，定义路由
const routes = [

    //一级路由
    //登录一级路由
    //一级路由
    //登录一级路由
    {
        name:'login',
        path:'/',
        component:()=>import("/src/components/login/index.vue"),
        meta:'登入'
    },
    {
        name: 'Home',
        path: '/home',//默认路径，登入就进来
        component: () => import("/src/components/main.vue"),
        meta: {title: '主页', noCache: true},
        //二级
        children: [
            // =========      客户管理
            {
                path: '/customerManagement',
                name: 'CustomerManagement',
                component: () => import('/src/components/customer-management/CrManagement.vue'),
                meta: { title: '客户管理', icon: 'dashboard' }
            },
            {
                path: '/linkMan',
                name: 'LinkMan',
                component: () => import('/src/components/customer-management/linkMan.vue'),
                meta: { title: '联系人', icon: 'dashboard' }
            },
            {
                path: '/customerTransfer',
                name: 'CustomerTransfer',
                component: () => import('/src/components/customer-management/CustomerTransfer.vue'),
                meta: { title: '客户转移', icon: 'dashboard' }
            },
            {
                path: '/customerTransferRecord',
                name: 'CustomerTransferRecord',
                component: () => import('/src/components/customer-management/CustomerTransferRecord.vue'),
                meta: { title: '客户转移记录', icon: 'dashboard' }
            },

            // ===========售前

            {
                path: '/customerDemand',
                name: 'CustomerDemand',
                component: () => import('/src/components/pre-sales/CustomerDemand.vue'),
                meta: { title: '客户需求', icon: 'dashboard' }
            },
            {
                path: '/salesOpportunities',
                name: 'SalesOpportunities',
                component: () => import('/src/components/pre-sales/SalesOpportunities.vue'),
                meta: { title: '销售机会', icon: 'dashboard' }
            },
			{
			    path: '/quotation',
			    name: 'Quotation',
			    component: () => import('/src/components/pre-sales/Quotation.vue'),
			    meta: { title: '报价', icon: 'dashboard' }
			},
            {
                path: '/salesOpportunitiesDetails',
                name: 'SalesOpportunitiesDetails',
                hidden:true,
                component: () => import('/src/components/pre-sales/SalesOpportunitiesDetalis.vue'),
                meta: { title: '机会明细', icon: 'dashboard' }
            },
            {
                path: '/solution',
                name: 'Solution',
                component: () => import('/src/components/pre-sales/Solution.vue'),
                meta: { title: '解决方案', icon: 'dashboard' }
            },
            {
                path: '/PriceSheet',
                name: 'PriceSheet',
                component: () => import('/src/components/pre-sales/PriceSheet.vue'),
                meta: { title: '报价', icon: 'dashboard' }
            },
            {
                path: '/priceSheetDetails',
                name: 'PriceSheetDetails',
                hidden:true,
                component: () => import('/src/components/pre-sales/PriceSheetDetalis.vue'),
                meta: { title: '报价明细', icon: 'dashboard' }
            },
            {
                path: '/PriceSheet',
                name: 'PriceSheet',
                component: () => import('/src/components/pre-sales/PriceSheetDetailsList.vue'),
                meta: { title: '报价明细列表', icon: 'dashboard' }
            },


            // ===========售中
            {
                path: '/order',
                name: 'Order',
                component: () => import('/src/components/in-sale/Order.vue'),
                meta: { title: '订单', icon: 'dashboard' }
            },
            {
                path: '/orderDetails',
                name: 'OrderDetails',
                hidden: true,
                component: () => import('/src/components/in-sale/OrderDetalis.vue'),
                meta: { title: '订单明细', icon: 'dashboard' }
            },
            {
                path: '/shipments',
                name: 'Shipments',
                component: () => import('/src/components/in-sale/Shipments.vue'),
                meta: { title: '发货', icon: 'dashboard' }
            },
            {
                path: '/shipmentsDetails',
                name: 'ShipmentsDetails',
                hidden: true,
                component: () => import('/src/components/in-sale/ShipmentsDetalis.vue'),
                meta: { title: '发货明细', icon: 'dashboard' }
            },{
                path: '/planArrangement',
                name: 'PlanArrangement',
                component: () => import('/src/components/in-sale/PlanArrangement.vue'),
                meta: { title: '计划回款', icon: 'dashboard' }
            },
            {
                path: '/plan',
                name: 'Plan',
                component: () => import('/src/components/in-sale/Plan.vue'),
                meta: { title: '回款', icon: 'dashboard' }
            },
            {
                path: '/makeOutAnInvoice',
                name: 'MakeOutAnInvoice',
                component: () => import('/src/components/in-sale/MakeOutAnInvoice.vue'),
                meta: { title: '开票', icon: 'dashboard' }
            },


            // ===========售后
            {
                path: '/customerService',
                name: 'CustomerService',
                component: () => import('/src/components/after-sale/CustomerService.vue'),
                meta: { title: '客户服务', icon: 'dashboard' }
            },
            {
                path: '/customerComplaint',
                name: 'CustomerComplaint',
                component: () => import('/src/components/after-sale/CustomerComplaint.vue'),
                meta: { title: '投诉', icon: 'dashboard' }
            },
            {
                path: '/customerCare',
                name: 'CustomerCare',
                component: () => import('/src/components/after-sale/CustomerCare.vue'),
                meta: { title: '客户关怀', icon: 'dashboard' }
            },
            {
                path: '/customerConsole',
                name: 'CustomerConsole',
                component: () => import('/src/components/after-sale/CustomerConsole.vue'),
                meta: { title: '客服控制台', icon: 'dashboard' }
            },
            {
                path: '/qALibrary',
                name: 'QALibrary',
                component: () => import('/src/components/after-sale/QALibrary.vue'),
                meta: { title: 'QA库', icon: 'dashboard' }
            },
            //仓库管理

            {
                path: '/warehouse-1',
                name: 'Warehouse-1',
                component: () => import('/src/components/storage/access/enter/index.vue'),
                meta: {title: '入库', icon: 'el-icon-s-help'},
            },
            {
                path: '/warehouse-2',
                name: 'Warehouse-2',
                component: () => import('/src/components/storage/access/putup/index.vue'),
                meta: {title: '出库', icon: 'el-icon-s-help'},
            },
            {
                path: '/warehouse-1',
                name: 'Warehouse-1',
                component: () => import('/src/components/storage/warehouse/warehouse-1/index.vue'),
                meta: {title: '仓库', icon: 'el-icon-s-help'},
            },
            {
                path: '/warehouse-2',
                name: 'Warehouse-2',
                component: () => import('/src/components/storage/warehouse/warehouse-2/index.vue'),
                meta: {title: '库存', icon: 'el-icon-s-help'},
            },
            {
                path: '/product-1',
                name: 'Product-1',
                component: () => import('/src/components/storage/product-1/index.vue'),
                meta: {title: '产品', icon: 'el-icon-s-help'},
            },
            {
                path: '/product-class',
                name: 'Product-class',
                component: () => import('/src/components/storage/product-class/index.vue'),
                meta: {title: '分类', icon: 'el-icon-s-help'},
            }
        ]
    }
]


//创建Router路由实列
const router = createRouter({
    history:createWebHistory(),
    routes //这里是把上面的routes赋到这里来
})
// router.beforeEach(function (to,from,next){
// 	const  state=JSON.parse(sessionStorage.getItem("state"))
// 	if(to.name!=="/"&& state==null || to.name!=="/"&& !state.token.isValidate){}
// })
//给路由定义一个对外的引用（注入路由）就是起别名一样
export default router
