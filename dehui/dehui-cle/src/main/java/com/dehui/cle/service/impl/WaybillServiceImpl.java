package com.dehui.cle.service.impl;

import java.util.List;

import com.dehui.common.annotation.DataScope;
import com.dehui.common.exception.ServiceException;
import com.dehui.common.utils.DateUtils;
import com.dehui.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dehui.cle.mapper.WaybillMapper;
import com.dehui.cle.domain.Waybill;
import com.dehui.cle.service.IWaybillService;

/**
 * 我的运单Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-10-29
 */
@Service
public class WaybillServiceImpl implements IWaybillService 
{
    @Autowired
    private WaybillMapper waybillMapper;

    /**
     * 查询我的运单
     * 
     * @param id 我的运单主键
     * @return 我的运单
     */
    @Override
    public Waybill selectWaybillById(Long id)
    {
        return waybillMapper.selectWaybillById(id);
    }

    /**
     * 查询我的运单列表
     * 
     * @param waybill 我的运单
     * @return 我的运单
     */
    @Override
    @DataScope(userAlias = "w")
    public List<Waybill> selectWaybillList(Waybill waybill)
    {
        return waybillMapper.selectWaybillList(waybill);
    }

    /**
     * 新增我的运单
     * 
     * @param waybill 我的运单
     * @return 结果
     */
    @Override
    public int insertWaybill(Waybill waybill)
    {
        waybill.setCreateTime(DateUtils.getNowDate());
        waybill.setUserId(SecurityUtils.getUserId());
        return waybillMapper.insertWaybill(waybill);
    }

    /**
     * 删除我的运单信息
     * 
     * @param id 我的运单主键
     * @return 结果
     */
    @Override
    public int deleteWaybillById(Long id)
    {
        return waybillMapper.deleteWaybillById(id);
    }


    @Override
    public int updateWaybill(Waybill waybill) {
        // 校验：如果是普通用户（非管理员），只能修改自己的
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            Waybill old = waybillMapper.selectWaybillById(waybill.getId());
            if (old != null && !SecurityUtils.getUserId().equals(old.getUserId())) {
                throw new ServiceException("无权修改他人的运单");
            }
        }
        waybill.setUpdateTime(DateUtils.getNowDate());
        return waybillMapper.updateWaybill(waybill);
    }

    @Override
    public int deleteWaybillByIds(Long[] ids) {
        // 校验：只能删除自己的
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            for (Long id : ids) {
                Waybill old = waybillMapper.selectWaybillById(id);
                if (old != null && !SecurityUtils.getUserId().equals(old.getUserId())) {
                    throw new ServiceException("无权删除他人的运单");
                }
            }
        }
        return waybillMapper.deleteWaybillByIds(ids);
    }

    @Override
    public List<Waybill> selectMyWaybillList(Waybill waybill) {
        waybill.setUserId(SecurityUtils.getUserId());
        return waybillMapper.selectMyWaybillList(waybill);
    }

    @Override
    public Waybill selectMyWaybillById(Long id) {
        Waybill waybill = waybillMapper.selectWaybillById(id);
        if (waybill == null) {
            throw new ServiceException("运单不存在");
        }
        Long currentUserId = SecurityUtils.getUserId();
        if (!currentUserId.equals(waybill.getUserId())) {
            throw new ServiceException("无权查看该运单");
        }
        return waybill;
    }
}
