package com.dehui.cle.mapper;

import java.util.List;
import com.dehui.cle.domain.Waybill;

/**
 * 我的运单Mapper接口
 * 
 * @author ruoyi
 * @date 2026-10-29
 */
public interface WaybillMapper 
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
     * 删除我的运单
     * 
     * @param id 我的运单主键
     * @return 结果
     */
    public int deleteWaybillById(Long id);

    /**
     * 批量删除我的运单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWaybillByIds(Long[] ids);

    public List<Waybill> selectMyWaybillList(Waybill waybill);
}
