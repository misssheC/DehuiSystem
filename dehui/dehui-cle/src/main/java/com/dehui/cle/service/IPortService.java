package com.dehui.cle.service;

import java.util.List;
import com.dehui.cle.domain.Port;

/**
 * 码头管理Service接口
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IPortService 
{
    /**
     * 查询码头管理
     * 
     * @param id 码头管理主键
     * @return 码头管理
     */
    public Port selectPortById(Long id);

    /**
     * 查询码头管理列表
     * 
     * @param port 码头管理
     * @return 码头管理集合
     */
    public List<Port> selectPortList(Port port);

    /**
     * 新增码头管理
     * 
     * @param port 码头管理
     * @return 结果
     */
    public int insertPort(Port port);

    /**
     * 修改码头管理
     * 
     * @param port 码头管理
     * @return 结果
     */
    public int updatePort(Port port);

    /**
     * 批量删除码头管理
     * 
     * @param ids 需要删除的码头管理主键集合
     * @return 结果
     */
    public int deletePortByIds(Long[] ids);

    /**
     * 删除码头管理信息
     * 
     * @param id 码头管理主键
     * @return 结果
     */
    public int deletePortById(Long id);
}
