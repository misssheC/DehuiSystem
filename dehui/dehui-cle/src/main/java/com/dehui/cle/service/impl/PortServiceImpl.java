package com.dehui.cle.service.impl;

import java.util.List;
import com.dehui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dehui.cle.mapper.PortMapper;
import com.dehui.cle.domain.Port;
import com.dehui.cle.service.IPortService;

/**
 * 码头管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class PortServiceImpl implements IPortService 
{
    @Autowired
    private PortMapper portMapper;

    /**
     * 查询码头管理
     * 
     * @param id 码头管理主键
     * @return 码头管理
     */
    @Override
    public Port selectPortById(Long id)
    {
        return portMapper.selectPortById(id);
    }

    /**
     * 查询码头管理列表
     * 
     * @param port 码头管理
     * @return 码头管理
     */
    @Override
    public List<Port> selectPortList(Port port)
    {
        return portMapper.selectPortList(port);
    }

    /**
     * 新增码头管理
     * 
     * @param port 码头管理
     * @return 结果
     */
    @Override
    public int insertPort(Port port)
    {
        port.setCreateTime(DateUtils.getNowDate());
        return portMapper.insertPort(port);
    }

    /**
     * 修改码头管理
     * 
     * @param port 码头管理
     * @return 结果
     */
    @Override
    public int updatePort(Port port)
    {
        port.setUpdateTime(DateUtils.getNowDate());
        return portMapper.updatePort(port);
    }

    /**
     * 批量删除码头管理
     * 
     * @param ids 需要删除的码头管理主键
     * @return 结果
     */
    @Override
    public int deletePortByIds(Long[] ids)
    {
        return portMapper.deletePortByIds(ids);
    }

    /**
     * 删除码头管理信息
     * 
     * @param id 码头管理主键
     * @return 结果
     */
    @Override
    public int deletePortById(Long id)
    {
        return portMapper.deletePortById(id);
    }
}
