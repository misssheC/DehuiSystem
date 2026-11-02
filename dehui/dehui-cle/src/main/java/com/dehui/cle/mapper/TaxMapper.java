package com.dehui.cle.mapper;

import java.util.List;
import com.dehui.cle.domain.Tax;

/**
 * 税率查询Mapper接口
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
public interface TaxMapper 
{
    /**
     * 查询税率查询
     * 
     * @param id 税率查询主键
     * @return 税率查询
     */
    public Tax selectTaxById(Long id);

    /**
     * 查询税率查询列表
     * 
     * @param tax 税率查询
     * @return 税率查询集合
     */
    public List<Tax> selectTaxList(Tax tax);

    /**
     * 新增税率查询
     * 
     * @param tax 税率查询
     * @return 结果
     */
    public int insertTax(Tax tax);

    /**
     * 修改税率查询
     * 
     * @param tax 税率查询
     * @return 结果
     */
    public int updateTax(Tax tax);

    /**
     * 删除税率查询
     * 
     * @param id 税率查询主键
     * @return 结果
     */
    public int deleteTaxById(Long id);

    /**
     * 批量删除税率查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaxByIds(Long[] ids);
}
