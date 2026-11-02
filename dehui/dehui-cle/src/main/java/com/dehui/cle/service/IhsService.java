package com.dehui.cle.service;

import java.util.List;
import com.dehui.cle.domain.hs;

/**
 * HS查询Service接口
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IhsService 
{
    /**
     * 查询HS查询
     * 
     * @param id HS查询主键
     * @return HS查询
     */
    public hs selecthsById(Long id);

    /**
     * 查询HS查询列表
     * 
     * @param hs HS查询
     * @return HS查询集合
     */
    public List<hs> selecthsList(hs hs);

    /**
     * 新增HS查询
     * 
     * @param hs HS查询
     * @return 结果
     */
    public int inserths(hs hs);

    /**
     * 修改HS查询
     * 
     * @param hs HS查询
     * @return 结果
     */
    public int updatehs(hs hs);

    /**
     * 批量删除HS查询
     * 
     * @param ids 需要删除的HS查询主键集合
     * @return 结果
     */
    public int deletehsByIds(Long[] ids);

    /**
     * 删除HS查询信息
     * 
     * @param id HS查询主键
     * @return 结果
     */
    public int deletehsById(Long id);
}
