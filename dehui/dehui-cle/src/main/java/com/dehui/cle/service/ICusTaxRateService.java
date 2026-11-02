package com.dehui.cle.service;

import java.util.List;
import com.dehui.cle.domain.CusTaxRate;

/**
 * 反倾销税信息Service接口
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
public interface ICusTaxRateService 
{
    /**
     * 查询反倾销税信息
     * 
     * @param id 反倾销税信息主键
     * @return 反倾销税信息
     */
    public CusTaxRate selectCusTaxRateById(Long id);

    /**
     * 查询反倾销税信息列表
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 反倾销税信息集合
     */
    public List<CusTaxRate> selectCusTaxRateList(CusTaxRate cusTaxRate);

    /**
     * 新增反倾销税信息
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 结果
     */
    public int insertCusTaxRate(CusTaxRate cusTaxRate);

    /**
     * 修改反倾销税信息
     * 
     * @param cusTaxRate 反倾销税信息
     * @return 结果
     */
    public int updateCusTaxRate(CusTaxRate cusTaxRate);

    /**
     * 批量删除反倾销税信息
     * 
     * @param ids 需要删除的反倾销税信息主键集合
     * @return 结果
     */
    public int deleteCusTaxRateByIds(Long[] ids);

    /**
     * 删除反倾销税信息信息
     * 
     * @param id 反倾销税信息主键
     * @return 结果
     */
    public int deleteCusTaxRateById(Long id);
}
