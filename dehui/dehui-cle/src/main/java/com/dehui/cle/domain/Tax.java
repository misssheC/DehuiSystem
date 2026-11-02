package com.dehui.cle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * 税率查询对象 biz_tax
 * 
 * @author ruoyi
 * @date 2026-11-02
 */
public class Tax extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 海关编码 */
    @Excel(name = "海关编码")
    private String hsCode;

    /** 反倾销 */
    @Excel(name = "反倾销")
    private String antiDumping;

    /** CPSC */
    @Excel(name = "CPSC")
    private String cpsc;

    /** 是否需要认证 */
    @Excel(name = "是否需要认证")
    private String needCert;

    /** 总税率 */
    @Excel(name = "总税率")
    private String totalTaxRate;

    /** 中文描述 */
    @Excel(name = "中文描述")
    private String cnDesc;

    /** 官方英文描述 */
    @Excel(name = "官方英文描述")
    private String officialDesc;

    /** 基础税率 */
    @Excel(name = "基础税率")
    private String baseTaxRate;

    /** 301加征税CODE */
    @Excel(name = "301加征税CODE")
    private String section301Code;

    /** 301加征税 */
    @Excel(name = "301加征税")
    private String section301Tax;

    /** FL301/232附加税CODE */
    @Excel(name = "FL301/232附加税CODE")
    private String fl301232Code;

    /** FL301/232附加税 */
    @Excel(name = "FL301/232附加税")
    private String fl301232Tax;

    /** 单位1 */
    @Excel(name = "单位1")
    private String unit1;

    /** 单位2 */
    @Excel(name = "单位2")
    private String unit2;

    /** Cents/kg */
    @Excel(name = "Cents/kg")
    private String centsPerKg;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /** PGA1 */
    @Excel(name = "PGA1")
    private String pga1;

    /** PGA2 */
    @Excel(name = "PGA2")
    private String pga2;

    /** PGA3 */
    @Excel(name = "PGA3")
    private String pga3;

    /** PGA4 */
    @Excel(name = "PGA4")
    private String pga4;

    /** PGA5 */
    @Excel(name = "PGA5")
    private String pga5;

    /** PGA6 */
    @Excel(name = "PGA6")
    private String pga6;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setHsCode(String hsCode) 
    {
        this.hsCode = hsCode;
    }

    public String getHsCode() 
    {
        return hsCode;
    }

    public void setAntiDumping(String antiDumping) 
    {
        this.antiDumping = antiDumping;
    }

    public String getAntiDumping() 
    {
        return antiDumping;
    }

    public void setCpsc(String cpsc) 
    {
        this.cpsc = cpsc;
    }

    public String getCpsc() 
    {
        return cpsc;
    }

    public void setNeedCert(String needCert) 
    {
        this.needCert = needCert;
    }

    public String getNeedCert() 
    {
        return needCert;
    }

    public void setTotalTaxRate(String totalTaxRate) 
    {
        this.totalTaxRate = totalTaxRate;
    }

    public String getTotalTaxRate() 
    {
        return totalTaxRate;
    }

    public void setCnDesc(String cnDesc) 
    {
        this.cnDesc = cnDesc;
    }

    public String getCnDesc() 
    {
        return cnDesc;
    }

    public void setOfficialDesc(String officialDesc) 
    {
        this.officialDesc = officialDesc;
    }

    public String getOfficialDesc() 
    {
        return officialDesc;
    }

    public void setBaseTaxRate(String baseTaxRate) 
    {
        this.baseTaxRate = baseTaxRate;
    }

    public String getBaseTaxRate() 
    {
        return baseTaxRate;
    }

    public void setSection301Code(String section301Code) 
    {
        this.section301Code = section301Code;
    }

    public String getSection301Code() 
    {
        return section301Code;
    }

    public void setSection301Tax(String section301Tax) 
    {
        this.section301Tax = section301Tax;
    }

    public String getSection301Tax() 
    {
        return section301Tax;
    }

    public void setFl301232Code(String fl301232Code) 
    {
        this.fl301232Code = fl301232Code;
    }

    public String getFl301232Code() 
    {
        return fl301232Code;
    }

    public void setFl301232Tax(String fl301232Tax) 
    {
        this.fl301232Tax = fl301232Tax;
    }

    public String getFl301232Tax() 
    {
        return fl301232Tax;
    }

    public void setUnit1(String unit1) 
    {
        this.unit1 = unit1;
    }

    public String getUnit1() 
    {
        return unit1;
    }

    public void setUnit2(String unit2) 
    {
        this.unit2 = unit2;
    }

    public String getUnit2() 
    {
        return unit2;
    }

    public void setCentsPerKg(String centsPerKg) 
    {
        this.centsPerKg = centsPerKg;
    }

    public String getCentsPerKg() 
    {
        return centsPerKg;
    }

    public void setEffectiveDate(Date effectiveDate) 
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() 
    {
        return effectiveDate;
    }

    public void setExpirationDate(Date expirationDate) 
    {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() 
    {
        return expirationDate;
    }

    public void setPga1(String pga1) 
    {
        this.pga1 = pga1;
    }

    public String getPga1() 
    {
        return pga1;
    }

    public void setPga2(String pga2) 
    {
        this.pga2 = pga2;
    }

    public String getPga2() 
    {
        return pga2;
    }

    public void setPga3(String pga3) 
    {
        this.pga3 = pga3;
    }

    public String getPga3() 
    {
        return pga3;
    }

    public void setPga4(String pga4) 
    {
        this.pga4 = pga4;
    }

    public String getPga4() 
    {
        return pga4;
    }

    public void setPga5(String pga5) 
    {
        this.pga5 = pga5;
    }

    public String getPga5() 
    {
        return pga5;
    }

    public void setPga6(String pga6) 
    {
        this.pga6 = pga6;
    }

    public String getPga6() 
    {
        return pga6;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hsCode", getHsCode())
            .append("antiDumping", getAntiDumping())
            .append("cpsc", getCpsc())
            .append("needCert", getNeedCert())
            .append("totalTaxRate", getTotalTaxRate())
            .append("cnDesc", getCnDesc())
            .append("officialDesc", getOfficialDesc())
            .append("baseTaxRate", getBaseTaxRate())
            .append("section301Code", getSection301Code())
            .append("section301Tax", getSection301Tax())
            .append("fl301232Code", getFl301232Code())
            .append("fl301232Tax", getFl301232Tax())
            .append("unit1", getUnit1())
            .append("unit2", getUnit2())
            .append("centsPerKg", getCentsPerKg())
            .append("effectiveDate", getEffectiveDate())
            .append("expirationDate", getExpirationDate())
            .append("pga1", getPga1())
            .append("pga2", getPga2())
            .append("pga3", getPga3())
            .append("pga4", getPga4())
            .append("pga5", getPga5())
            .append("pga6", getPga6())
            .append("createTime", getCreateTime())
            .toString();
    }
}
