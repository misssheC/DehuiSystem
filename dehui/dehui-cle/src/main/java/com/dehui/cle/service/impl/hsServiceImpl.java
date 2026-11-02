package com.dehui.cle.service.impl;

import java.util.List;
import com.dehui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dehui.cle.mapper.hsMapper;
import com.dehui.cle.domain.hs;
import com.dehui.cle.service.IhsService;

/**
 * HS查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class hsServiceImpl implements IhsService 
{
    @Autowired
    private hsMapper hsMapper;

    /**
     * 查询HS查询
     * 
     * @param id HS查询主键
     * @return HS查询
     */
    @Override
    public hs selecthsById(Long id)
    {
        return hsMapper.selecthsById(id);
    }

    /**
     * 查询HS查询列表
     * 
     * @param hs HS查询
     * @return HS查询
     */
    @Override
    public List<hs> selecthsList(hs hs)
    {
        return hsMapper.selecthsList(hs);
    }

    /**
     * 新增HS查询
     * 
     * @param hs HS查询
     * @return 结果
     */
    @Override
    public int inserths(hs hs)
    {
        hs.setCreateTime(DateUtils.getNowDate());
        return hsMapper.inserths(hs);
    }

    /**
     * 修改HS查询
     * 
     * @param hs HS查询
     * @return 结果
     */
    @Override
    public int updatehs(hs hs)
    {
        hs.setUpdateTime(DateUtils.getNowDate());
        return hsMapper.updatehs(hs);
    }

    /**
     * 批量删除HS查询
     * 
     * @param ids 需要删除的HS查询主键
     * @return 结果
     */
    @Override
    public int deletehsByIds(Long[] ids)
    {
        return hsMapper.deletehsByIds(ids);
    }

    /**
     * 删除HS查询信息
     * 
     * @param id HS查询主键
     * @return 结果
     */
    @Override
    public int deletehsById(Long id)
    {
        return hsMapper.deletehsById(id);
    }
}
