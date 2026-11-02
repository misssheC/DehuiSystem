package com.dehui.cle.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * 反倾销税信息对象 biz_tax_rate
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
public class CusTaxRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 案件编号 */
    @Excel(name = "案件编号")
    private String caseNo;

    /** 涉及产品 */
    @Excel(name = "涉及产品")
    private String enDesc;

    /** 中文品名 */
    @Excel(name = "中文品名")
    private String cnDesc;

    /** 税率（%） */
    @Excel(name = "税率", readConverterExp = "%=")
    private BigDecimal taxRate;

    /** 政令发布日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "政令发布日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

    /** 涉及海关编码 */
    @Excel(name = "涉及海关编码")
    private String hsCode;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCaseNo(String caseNo) 
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo() 
    {
        return caseNo;
    }

    public void setEnDesc(String enDesc) 
    {
        this.enDesc = enDesc;
    }

    public String getEnDesc() 
    {
        return enDesc;
    }

    public void setCnDesc(String cnDesc) 
    {
        this.cnDesc = cnDesc;
    }

    public String getCnDesc() 
    {
        return cnDesc;
    }

    public void setTaxRate(BigDecimal taxRate) 
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() 
    {
        return taxRate;
    }

    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() 
    {
        return issueDate;
    }

    public void setHsCode(String hsCode) 
    {
        this.hsCode = hsCode;
    }

    public String getHsCode() 
    {
        return hsCode;
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
            .append("caseNo", getCaseNo())
            .append("enDesc", getEnDesc())
            .append("cnDesc", getCnDesc())
            .append("taxRate", getTaxRate())
            .append("issueDate", getIssueDate())
            .append("hsCode", getHsCode())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
