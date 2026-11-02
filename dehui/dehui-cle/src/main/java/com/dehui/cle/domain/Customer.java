package com.dehui.cle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * 客户信息对象 biz_customer
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户主键ID */
    private Long id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 业务员ID */
    @Excel(name = "业务员ID")
    private Long salesmanId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 是否签合同 */
    @Excel(name = "是否签合同")
    private Integer isSignContract;

    /** 客户登录密码 */
    private String customerPassword;

    /** 角色ID */
    private Long roleId;

    /** 合同文件ID集合 */
    private String contractFileIds;

    /** 开户文件ID集合 */
    private String accountFileIds;

    /** BOND_ID */
    private Long bondId;

    /** BOND编号 */
    private String bondNo;

    /** 绑定状态 */
    private Long bondStatus;

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

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setSalesmanId(Long salesmanId) 
    {
        this.salesmanId = salesmanId;
    }

    public Long getSalesmanId() 
    {
        return salesmanId;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setIsSignContract(Integer isSignContract) 
    {
        this.isSignContract = isSignContract;
    }

    public Integer getIsSignContract() 
    {
        return isSignContract;
    }

    public void setCustomerPassword(String customerPassword) 
    {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPassword() 
    {
        return customerPassword;
    }

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }

    public void setContractFileIds(String contractFileIds) 
    {
        this.contractFileIds = contractFileIds;
    }

    public String getContractFileIds() 
    {
        return contractFileIds;
    }

    public void setAccountFileIds(String accountFileIds) 
    {
        this.accountFileIds = accountFileIds;
    }

    public String getAccountFileIds() 
    {
        return accountFileIds;
    }

    public void setBondId(Long bondId) 
    {
        this.bondId = bondId;
    }

    public Long getBondId() 
    {
        return bondId;
    }

    public void setBondNo(String bondNo) 
    {
        this.bondNo = bondNo;
    }

    public String getBondNo() 
    {
        return bondNo;
    }

    public void setBondStatus(Long bondStatus) 
    {
        this.bondStatus = bondStatus;
    }

    public Long getBondStatus() 
    {
        return bondStatus;
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
            .append("customerName", getCustomerName())
            .append("salesmanId", getSalesmanId())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("isSignContract", getIsSignContract())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("customerPassword", getCustomerPassword())
            .append("roleId", getRoleId())
            .append("contractFileIds", getContractFileIds())
            .append("accountFileIds", getAccountFileIds())
            .append("bondId", getBondId())
            .append("bondNo", getBondNo())
            .append("bondStatus", getBondStatus())
            .append("createDept", getCreateDept())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
