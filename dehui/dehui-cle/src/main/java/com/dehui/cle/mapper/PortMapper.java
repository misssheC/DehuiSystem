package com.dehui.cle.mapper;

import java.util.List;
import com.dehui.cle.domain.Port;

/**
 * 码头管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public interface PortMapper 
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
     * 删除码头管理
     * 
     * @param id 码头管理主键
     * @return 结果
     */
    public int deletePortById(Long id);

    /**
     * 批量删除码头管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePortByIds(Long[] ids);
}
