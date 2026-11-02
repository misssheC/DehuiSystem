package com.dehui.cle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * 码头管理对象 biz_port
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public class Port extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 港头名称 */
    @Excel(name = "港头名称")
    private String portName;

    /** 港口类型 */
    @Excel(name = "港口类型")
    private String portType;

    /** 航线名称 */
    @Excel(name = "航线名称")
    private String routeType;

    /** 是否清关 */
    @Excel(name = "是否清关")
    private String clearCustomsFlag;

    /** 装货港代码 */
    @Excel(name = "装货港代码")
    private String loadPortCode;

    /** 卸货港代码 */
    @Excel(name = "卸货港代码")
    private String unloadPortCode;

    /** 创建部门 */
    private Long createDept;

    /** 删除标志 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPortName(String portName) 
    {
        this.portName = portName;
    }

    public String getPortName() 
    {
        return portName;
    }

    public void setPortType(String portType) 
    {
        this.portType = portType;
    }

    public String getPortType() 
    {
        return portType;
    }

    public void setRouteType(String routeType) 
    {
        this.routeType = routeType;
    }

    public String getRouteType() 
    {
        return routeType;
    }

    public void setClearCustomsFlag(String clearCustomsFlag) 
    {
        this.clearCustomsFlag = clearCustomsFlag;
    }

    public String getClearCustomsFlag() 
    {
        return clearCustomsFlag;
    }

    public void setLoadPortCode(String loadPortCode) 
    {
        this.loadPortCode = loadPortCode;
    }

    public String getLoadPortCode() 
    {
        return loadPortCode;
    }

    public void setUnloadPortCode(String unloadPortCode) 
    {
        this.unloadPortCode = unloadPortCode;
    }

    public String getUnloadPortCode() 
    {
        return unloadPortCode;
    }

    public void setCreateDept(Long createDept) 
    {
        this.createDept = createDept;
    }

    public Long getCreateDept() 
    {
        return createDept;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("portName", getPortName())
            .append("portType", getPortType())
            .append("routeType", getRouteType())
            .append("clearCustomsFlag", getClearCustomsFlag())
            .append("loadPortCode", getLoadPortCode())
            .append("unloadPortCode", getUnloadPortCode())
            .append("createDept", getCreateDept())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
