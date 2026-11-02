package com.dehui.cle.service.impl;

import java.util.List;
import com.dehui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dehui.cle.mapper.TaxMapper;
import com.dehui.cle.domain.Tax;
import com.dehui.cle.service.ITaxService;

/**
 * 税率查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
@Service
public class TaxServiceImpl implements ITaxService 
{
    @Autowired
    private TaxMapper taxMapper;

    /**
     * 查询税率查询
     * 
     * @param id 税率查询主键
     * @return 税率查询
     */
    @Override
    public Tax selectTaxById(Long id)
    {
        return taxMapper.selectTaxById(id);
    }

    /**
     * 查询税率查询列表
     * 
     * @param tax 税率查询
     * @return 税率查询
     */
    @Override
    public List<Tax> selectTaxList(Tax tax)
    {
        return taxMapper.selectTaxList(tax);
    }

    /**
     * 新增税率查询
     * 
     * @param tax 税率查询
     * @return 结果
     */
    @Override
    public int insertTax(Tax tax)
    {
        tax.setCreateTime(DateUtils.getNowDate());
        return taxMapper.insertTax(tax);
    }

    /**
     * 修改税率查询
     * 
     * @param tax 税率查询
     * @return 结果
     */
    @Override
    public int updateTax(Tax tax)
    {
        return taxMapper.updateTax(tax);
    }

    /**
     * 批量删除税率查询
     * 
     * @param ids 需要删除的税率查询主键
     * @return 结果
     */
    @Override
    public int deleteTaxByIds(Long[] ids)
    {
        return taxMapper.deleteTaxByIds(ids);
    }

    /**
     * 删除税率查询信息
     * 
     * @param id 税率查询主键
     * @return 结果
     */
    @Override
    public int deleteTaxById(Long id)
    {
        return taxMapper.deleteTaxById(id);
    }
}
