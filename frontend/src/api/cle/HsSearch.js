import request from '@/utils/request'

// 查询HS查询列表
export function listHsSearch(query) {
  return request({
    url: '/cle/HsSearch/list',
    method: 'get',
    params: query
  })
}

// 查询HS查询详细
export function getHsSearch(id) {
  return request({
    url: '/cle/HsSearch/' + id,
    method: 'get'
  })
}

// 新增HS查询
export function addHsSearch(data) {
  return request({
    url: '/cle/HsSearch',
    method: 'post',
    data: data
  })
}

// 修改HS查询
export function updateHsSearch(data) {
  return request({
    url: '/cle/HsSearch',
    method: 'put',
    data: data
  })
}

// 删除HS查询
export function delHsSearch(id) {
  return request({
    url: '/cle/HsSearch/' + id,
    method: 'delete'
  })
}
