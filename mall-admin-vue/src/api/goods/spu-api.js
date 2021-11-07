import request from '@/utils/request'

export function getSpuLogs(spuId) {
  return request.get(`/goods/spus/${spuId}/logs`)
}

export function getSpuAudits(spuId) {
  return request.get(`/goods/spus/${spuId}/audits`)
}

export function approve(spuId, feedback) {
  const formData = new FormData()
  formData.append('feedback', feedback)
  return request.put(`/goods/spus/${spuId}/approve`, formData)
}

export function reject(spuId, feedback) {
  const formData = new FormData()
  formData.append('feedback', feedback)
  return request.put(`/goods/spus/${spuId}/reject`, formData)
}

export function put(spuId) {
  return request.put(`/goods/spus/${spuId}/put`)
}

export function pull(spuId) {
  return request.put(`/goods/spus/${spuId}/pull`)
}
