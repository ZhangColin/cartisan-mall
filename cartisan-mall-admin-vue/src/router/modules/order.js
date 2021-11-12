import Layout from '@/layout'
import nested from '@/layout/nested'

export default {
  path: '/order',
  component: Layout,
  redirect: '/order/order',
  // name: 'Order',
  meta: { title: '订单管理', icon: 'component' },
  children: [{
    path: 'order',
    // name: 'Order',
    component: nested,
    redirect: '/order/orders/order-list',
    meta: { title: '订单', icon: 'nested' },
    children: [{
      path: 'order-list',
      component: () => import('@/views/order/order/Order'),
      meta: { title: '订单', breadcrumb: false },
      hidden: false
    }]
  }, {
    path: 'return-cause',
    // name: 'returnCause',
    component: () => import('@/views/order/return-cause/ReturnCause'),
    meta: { title: '退货原因', icon: 'nested' },
    hidden: false
  }]
}

