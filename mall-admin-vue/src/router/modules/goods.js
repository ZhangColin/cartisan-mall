/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'
// import nested from '@/layout/nested'

export default {
  path: '/goods',
  component: Layout,
  redirect: '/goods/brand',
  // name: 'goods',
  meta: { title: '商品管理', icon: 'component' },
  children: [{
    path: 'template',
    // name: 'template',
    component: () => import('@/views/goods/template/Template'),
    meta: { title: '规格参数模板', icon: 'nested' }
  }, {
    path: 'category',
    // name: 'category',
    component: () => import('@/views/goods/category/Category'),
    meta: { title: '分类', icon: 'nested' }
  }, {
    path: 'brand',
    // name: 'brand',
    component: () => import('@/views/goods/brand/Brand'),
    meta: { title: '品牌', icon: 'nested' }
  }, {
    path: 'album',
    // name: 'album',
    component: () => import('@/views/goods/album/Album'),
    meta: { title: '图片库', icon: 'nested' }
  }]
}

