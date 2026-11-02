import request from '@/utils/request'

// 查询我的运单列表
export function listWaybill(query) {
  return request({
    url: '/cle/waybill/list',
    method: 'get',
    params: query
  })
}

// 查询我的运单详细
export function getWaybill(id) {
  return request({
    url: '/cle/waybill/' + id,
    method: 'get'
  })
}

// 新增我的运单
export function addWaybill(data) {
  return request({
    url: '/cle/waybill',
    method: 'post',
    data: data
  })
}

// 修改我的运单
export function updateWaybill(data) {
  return request({
    url: '/cle/waybill',
    method: 'put',
    data: data
  })
}

// 删除我的运单
export function delWaybill(id) {
  return request({
    url: '/cle/waybill/' + id,
    method: 'delete'
  })
}

export function listMyWaybill(query) {
  return request({
    url: '/cle/waybill/my/list',
    method: 'get',
    params: query
  })
}

// 查询我的运单详细
export function getMyWaybill(id) {
  return request({
    url: '/cle/waybill/my/' + id,
    method: 'get'
  })
}
