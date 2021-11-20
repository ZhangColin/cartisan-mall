import request from '@/utils/request'

export function getCategoryTree() {
  return request.get(`/goods/categories/tree`)
}

export function moveCategories(categories) {
  return request.post('/goods/categories/moveCategories', categories)
}
