package com.dehui.cle.service.impl;

import java.util.List;
import com.dehui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dehui.cle.mapper.CusTaxRateMapper;
import com.dehui.cle.domain.CusTaxRate;
import com.dehui.cle.service.ICusTaxRateService;

/**
 * 反倾销税信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
@Service
public class CusTaxRateServiceImpl implements ICusTaxRateService 
{
    @Autowired
    private CusTaxRateMapper cusTaxRateMapper;

    /**
     * 查询反倾销税信息
     * 
     * @param id 反倾销税信息主键
     * @return 反倾销税信息
     */
    @Override
    public CusTaxRate selectCusTaxRateById(Long id)
    {
        return cusTaxRateMapper.selectCusTaxRateById(id);
    }

    /**
     * 查询反倾销税信息列表
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 反倾销税信息
     */
    @Override
    public List<CusTaxRate> selectCusTaxRateList(CusTaxRate cusTaxRate)
    {
        return cusTaxRateMapper.selectCusTaxRateList(cusTaxRate);
    }

    /**
     * 新增反倾销税信息
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 结果
     */
    @Override
    public int insertCusTaxRate(CusTaxRate cusTaxRate)
    {
        cusTaxRate.setCreateTime(DateUtils.getNowDate());
        return cusTaxRateMapper.insertCusTaxRate(cusTaxRate);
    }

    /**
     * 修改反倾销税信息
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 结果
     */
    @Override
    public int updateCusTaxRate(CusTaxRate cusTaxRate)
    {
        cusTaxRate.setUpdateTime(DateUtils.getNowDate());
        return cusTaxRateMapper.updateCusTaxRate(cusTaxRate);
    }

    /**
     * 批量删除反倾销税信息
     * 
     * @param ids 需要删除的反倾销税信息主键
     * @return 结果
     */
    @Override
    public int deleteCusTaxRateByIds(Long[] ids)
    {
        return cusTaxRateMapper.deleteCusTaxRateByIds(ids);
    }

    /**
     * 删除反倾销税信息信息
     * 
     * @param id 反倾销税信息主键
     * @return 结果
     */
    @Override
    public int deleteCusTaxRateById(Long id)
    {
        return cusTaxRateMapper.deleteCusTaxRateById(id);
    }
}
