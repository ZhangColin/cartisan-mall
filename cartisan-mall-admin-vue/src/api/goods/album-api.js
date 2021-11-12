import request from '@/utils/request'

export function getAlbumImages(albumId) {
  return request.get(`/goods/albums/${albumId}/images`)
}

export function addAlbumImage(albumId, url) {
  return request.put(`/goods/albums/${albumId}/addImage?imageUrl=${url}`)
}

export function removeAlbumImage(albumId, url) {
  return request.put(`/goods/albums/${albumId}/removeImage?imageUrl=${url}`)
}
