import request from '@/utils/request'

// 查询反倾销税信息列表
export function listAntiDumping(query) {
  return request({
    url: '/cle/AntiDumping/list',
    method: 'get',
    params: query
  })
}

// 查询反倾销税信息详细
export function getAntiDumping(id) {
  return request({
    url: '/cle/AntiDumping/' + id,
    method: 'get'
  })
}

// 新增反倾销税信息
export function addAntiDumping(data) {
  return request({
    url: '/cle/AntiDumping',
    method: 'post',
    data: data
  })
}

// 修改反倾销税信息
export function updateAntiDumping(data) {
  return request({
    url: '/cle/AntiDumping',
    method: 'put',
    data: data
  })
}

// 删除反倾销税信息
export function delAntiDumping(id) {
  return request({
    url: '/cle/AntiDumping/' + id,
    method: 'delete'
  })
}
