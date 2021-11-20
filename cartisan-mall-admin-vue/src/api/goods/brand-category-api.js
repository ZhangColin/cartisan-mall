import request from '@/utils/request'

export function getByBrand(brandId) {
  return request.get(`/goods/brandCategories/brand/${brandId}`)
}

export function getByCategory(categoryId) {
  return request.get(`/goods/brandCategories/category/${categoryId}`)
}

export function buildRelation(brandId, categoryId) {
  return request.post('/goods/brandCategories/buildRelation', { brandId, categoryId })
}

export function cancelRelation(brandId, categoryId) {
  return request.post('/goods/brandCategories/cancelRelation', { brandId, categoryId })
}
