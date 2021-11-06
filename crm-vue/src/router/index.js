import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },


  // {
  //   path: '/404',
  //   component: () => import('@/views/404'),
  //   hidden: true
  // },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'CRM管理系统', icon: 'dashboard' }
    }]
  },

  // =========      客户管理
  {
    path: '/customerManagement',
    component: Layout,
    meta: { title: '客户管理', icon: 'dashboard' },
    children: [{
      path: 'customerManagement',
      name: 'CustomerManagement',
      component: () => import('@/views/customer-management/CrManagement'),
      meta: { title: '客户管理', icon: 'dashboard' }
    },
      {
        path: 'linkMan',
        name: 'LinkMan',
        component: () => import('@/views/customer-management/linkMan'),
        meta: { title: '联系人', icon: 'dashboard' }
      },
      {
        path: 'customerTransfer',
        name: 'CustomerTransfer',
        component: () => import('@/views/customer-management/CustomerTransfer'),
        meta: { title: '客户转移', icon: 'dashboard' }
      },
      {
        path: 'customerTransferRecord',
        name: 'CustomerTransferRecord',
        component: () => import('@/views/customer-management/CustomerTransferRecord'),
        meta: { title: '客户转移记录', icon: 'dashboard' }
      },
    ]
  },

  // ===========售前
  {
    path: '/preSales',
    component: Layout,
    // component: () => import('@/views/in-sale/OrderFrom'),
    meta: { title: '售前服务', icon: 'dashboard' },
    children: [{
      path: 'salesOpportunities',
      name: 'SalesOpportunities',
      component: () => import('@/views/pre-sales/SalesOpportunities'),
      meta: { title: '销售机会', icon: 'dashboard' }
    },
      {
        path: 'customerDemand',
        name: 'CustomerDemand',
        component: () => import('@/views/pre-sales/CustomerDemand'),
        meta: { title: '客户需求', icon: 'dashboard' }
      },
      {
        path: 'salesOpportunitiesDetalis',
        name: 'SalesOpportunitiesDetalis',
        hidden:true,
        component: () => import('@/views/pre-sales/SalesOpportunitiesDetalis'),
        meta: { title: '机会明细', icon: 'dashboard' }
      },
      {
        path: 'solution',
        name: 'Solution',
        component: () => import('@/views/pre-sales/Solution'),
        meta: { title: '解决方案', icon: 'dashboard' }
      },
      {
        path: 'PriceSheet',
        name: 'PriceSheet',
        component: () => import('@/views/pre-sales/PriceSheet'),
        meta: { title: '报价', icon: 'dashboard' }
      },
      {
        path: 'priceSheetDetails',
        name: 'PriceSheetDetails',
        hidden:true,
        component: () => import('@/views/pre-sales/PriceSheetDetalis'),
        meta: { title: '报价明细', icon: 'dashboard' }
      },
      {
        path: 'PriceSheet',
        name: 'PriceSheet',
        component: () => import('@/views/pre-sales/PriceSheetDetailsList'),
        meta: { title: '报价明细列表', icon: 'dashboard' }
      },
    ]
  },


  // ===========售中
  {
    path: '/inSales',
    component: Layout,
    // component: () => import('@/views/in-sale/OrderFrom'),
    meta: { title: '售中服务', icon: 'dashboard' },
    children: [{
      path: 'order',
      name: 'Order',
      component: () => import('@/views/in-sale/Order'),
      meta: { title: '订单', icon: 'dashboard' }
    },
      {
        path: 'orderDetails',
        name: 'OrderDetails',
        hidden: true,
        component: () => import('@/views/in-sale/OrderDetalis'),
        meta: { title: '订单明细', icon: 'dashboard' }
      },
      {
        path: 'shipments',
        name: 'Shipments',
        component: () => import('@/views/in-sale/Shipments'),
        meta: { title: '发货', icon: 'dashboard' }
      },
      {
        path: 'shipmentsDetails',
        name: 'ShipmentsDetails',
        hidden: true,
        component: () => import('@/views/in-sale/ShipmentsDetalis'),
        meta: { title: '发货明细', icon: 'dashboard' }
      },{
        path: 'planArrangement',
        name: 'PlanArrangement',
        component: () => import('@/views/in-sale/PlanArrangement'),
        meta: { title: '计划回款', icon: 'dashboard' }
      },
      {
        path: 'plan',
        name: 'Plan',
        component: () => import('@/views/in-sale/Plan'),
        meta: { title: '回款', icon: 'dashboard' }
      },
      {
        path: 'makeOutAnInvoice',
        name: 'MakeOutAnInvoice',
        component: () => import('@/views/in-sale/MakeOutAnInvoice'),
        meta: { title: '开票', icon: 'dashboard' }
      }
    ]
  },


  // ===========售后
  {
    path: '/afterSale',
    component: Layout,
    // component: () => import('@/views/in-sale/OrderFrom'),
    meta: { title: '售后服务', icon: 'dashboard' },
    children: [{
      path: 'customerConsole',
      name: 'CustomerConsole',
      component: () => import('@/views/after-sale/CustomerConsole'),
      meta: { title: '客服控制台', icon: 'dashboard' }
    },
      {
        path: 'customerService',
        name: 'CustomerService',
        component: () => import('@/views/after-sale/CustomerService'),
        meta: { title: '客户服务', icon: 'dashboard' }
      },
      {
        path: 'customerComplaint',
        name: 'CustomerComplaint',
        component: () => import('@/views/after-sale/CustomerComplaint'),
        meta: { title: '投诉', icon: 'dashboard' }
      },
      {
        path: 'customerCare',
        name: 'CustomerCare',
        component: () => import('@/views/after-sale/CustomerCare'),
        meta: { title: '客户关怀', icon: 'dashboard' }
      },
      {
        path: 'qALibrary',
        name: 'QALibrary',
        component: () => import('@/views/after-sale/QALibrary'),
        meta: { title: 'QA库', icon: 'dashboard' }
      },
    ]
  },


  //仓库管理
  {
    path:'/storage',
    component: Layout,
    redirect: '/storage/warehouse',
    name: 'Storage',
    meta:{title: '仓储管理', icon: 'el-icon-s-help'},
    children: [
      {
        path: '/warehouse',
        name: 'Warehouse',
        component:() => import('@/views/storage/warehouse/index'),
        meta: {title: '仓库管理', icon: 'el-icon-s-help'},
        children: [
          {
            path: 'warehouse-1',
            name: 'Warehouse-1',
            component: () => import('@/views/storage/warehouse/warehouse-1'),
            meta: {title: '仓库', icon: 'el-icon-s-help'},
          },
          {
            path: 'warehouse-2',
            name: 'Warehouse-2',
            component: () => import('@/views/storage/warehouse/warehouse-2'),
            meta: {title: '库存', icon: 'el-icon-s-help'},
          }
        ]
      },
      {
        path: '/access',
        name: 'Access',
        component:() => import('@/views/storage/access/index'),
        meta: {title: '出入库管理', icon: 'el-icon-s-help'},
        children: [
          {
            path: 'enter',
            name: 'Enter',
            component: () => import('@/views/storage/access/enter'),
            meta: {title: '入库', icon: 'el-icon-s-help'},
          },
          {
            path: 'putup',
            name: 'Putup',
            component: () => import('@/views/storage/access/putup'),
            meta: {title: '库存', icon: 'el-icon-s-help'},
          }
        ]
      }
    ]
  },
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
