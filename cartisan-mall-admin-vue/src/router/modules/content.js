import Layout from '@/layout'

export default {
  path: '/content',
  component: Layout,
  redirect: '/content/ad',
  // name: 'goods',
  meta: { title: '内容管理', icon: 'component' },
  children: [{
    path: 'ad',
    // name: 'ad',
    component: () => import('@/views/content/ad/Ad'),
    meta: { title: '广告', icon: 'nested' }
  }, {
    path: 'activity',
    // name: 'activity',
    component: () => import('@/views/content/activity/Activity'),
    meta: { title: '活动', icon: 'nested' }
  }]
}

