import Layout from '@/layout'
import nested from '@/layout/nested'

export default {
  path: '/goods',
  component: Layout,
  redirect: '/goods/brand',
  // name: 'goods',
  meta: { title: '商品管理', icon: 'component' },
  children: [{
    path: 'spu',
    // name: 'spu',
    component: nested,
    redirect: '/goods/spu/spu-list',
    meta: { title: '商品', icon: 'nested' },
    children: [{
      path: 'spu-list',
      component: () => import('@/views/goods/spu/Spu'),
      meta: { title: '商品', breadcrumb: false },
      hidden: false
    }, {
      path: 'sku',
      name: 'sku',
      component: () => import('@/views/goods/spu/Sku'),
      meta: { title: 'SKU' },
      hidden: true
    }, {
      path: 'goodsForm',
      name: 'goodsForm_add',
      component: () => import('@/views/goods/spu/GoodsForm'),
      meta: { title: 'SPU_add' },
      hidden: true
    }, {
      path: 'goodsForm/:spuId',
      name: 'goodsForm_eidt',
      component: () => import('@/views/goods/spu/GoodsForm'),
      meta: { title: 'SPU_edit' },
      hidden: true
    }]
  }, {
    path: 'category',
    // name: 'category',
    component: () => import('@/views/goods/category/Category'),
    meta: { title: '分类', icon: 'nested' }
  }, {
    path: 'specification',
    // name: 'specification',
    component: () => import('@/views/goods/specification/Specification'),
    meta: { title: '商品规格', icon: 'nested' }
  }, {
    path: 'brand',
    // name: 'brand',
    component: () => import('@/views/goods/brand/Brand'),
    meta: { title: '品牌', icon: 'nested' }
  }, {
    path: 'album',
    // name: 'album',
    component: nested,
    redirect: '/goods/album/album-list',
    meta: { title: '图片库', icon: 'nested' },
    children: [{
      path: 'album-list',
      component: () => import('@/views/goods/album/Album'),
      meta: { title: '图片库', breadcrumb: false },
      hidden: false
    }, {
      path: 'albumImages',
      name: 'albumImages',
      component: () => import('@/views/goods/album/AlbumImages'),
      meta: { title: '相册图片' },
      hidden: true
    }]
  }]
}

