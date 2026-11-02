import request from '@/utils/request'

// 查询码头管理列表
export function listPort(query) {
  return request({
    url: '/cle/port/list',
    method: 'get',
    params: query
  })
}

// 查询码头管理详细
export function getPort(id) {
  return request({
    url: '/cle/port/' + id,
    method: 'get'
  })
}

// 新增码头管理
export function addPort(data) {
  return request({
    url: '/cle/port',
    method: 'post',
    data: data
  })
}

// 修改码头管理
export function updatePort(data) {
  return request({
    url: '/cle/port',
    method: 'put',
    data: data
  })
}

// 删除码头管理
export function delPort(id) {
  return request({
    url: '/cle/port/' + id,
    method: 'delete'
  })
}
