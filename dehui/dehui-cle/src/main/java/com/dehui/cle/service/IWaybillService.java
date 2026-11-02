package com.dehui.cle.service;

import java.util.List;
import com.dehui.cle.domain.Waybill;

/**
 * 我的运单Service接口
 * 
 * @author ruoyi
 * @date 2026-10-29
 */
public interface IWaybillService 
{
    /**
     * 查询我的运单
     * 
     * @param id 我的运单主键
     * @return 我的运单
     */
    public Waybill selectWaybillById(Long id);

    /**
     * 查询我的运单列表
     * 
     * @param waybill 我的运单
     * @return 我的运单集合
     */
    public List<Waybill> selectWaybillList(Waybill waybill);

    /**
     * 新增我的运单
     * 
     * @param waybill 我的运单
     * @return 结果
     */
    public int insertWaybill(Waybill waybill);

    /**
     * 修改我的运单
     * 
     * @param waybill 我的运单
     * @return 结果
     */
    public int updateWaybill(Waybill waybill);

    /**
     * 批量删除我的运单
     * 
     * @param ids 需要删除的我的运单主键集合
     * @return 结果
     */
    public int deleteWaybillByIds(Long[] ids);

    /**
     * 删除我的运单信息
     * 
     * @param id 我的运单主键
     * @return 结果
     */
    public int deleteWaybillById(Long id);

    public List<Waybill> selectMyWaybillList(Waybill waybill);

    /**
     * 查询我的运单详情
     * @param id 运单ID
     * @return 运单详情
     */
    public Waybill selectMyWaybillById(Long id);
}
