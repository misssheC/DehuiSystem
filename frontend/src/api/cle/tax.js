import request from '@/utils/request'

// 查询税率查询列表
export function listTax(query) {
  return request({
    url: '/cle/tax/list',
    method: 'get',
    params: query
  })
}

// 查询税率查询详细
export function getTax(id) {
  return request({
    url: '/cle/tax/' + id,
    method: 'get'
  })
}

// 新增税率查询
export function addTax(data) {
  return request({
    url: '/cle/tax',
    method: 'post',
    data: data
  })
}

// 修改税率查询
export function updateTax(data) {
  return request({
    url: '/cle/tax',
    method: 'put',
    data: data
  })
}

// 删除税率查询
export function delTax(id) {
  return request({
    url: '/cle/tax/' + id,
    method: 'delete'
  })
}
