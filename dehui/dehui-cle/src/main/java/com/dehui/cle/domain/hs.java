package com.dehui.cle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * HS查询对象 biz_hs
 * 
 * @author ruoyi
 * @date 2026-10-30
 */
public class hs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 中文品名 */
    @Excel(name = "中文品名")
    private String cnName;

    /** 英文品名 */
    @Excel(name = "英文品名")
    private String enName;

    /** 通用CODE */
    @Excel(name = "通用CODE")
    private String commonCode;

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

    /** Article Description */
    @Excel(name = "Article Description")
    private String articleDescription;

    /** 总税率 */
    @Excel(name = "总税率")
    private String totalTaxRate;

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

    /** Cents/kg */
    @Excel(name = "Cents/kg")
    private String centsPerKg;

    /** PGA 1 */
    @Excel(name = "PGA 1")
    private String pga1;

    /** PGA 2 */
    @Excel(name = "PGA 2")
    private String pga2;

    /** PGA 3 */
    @Excel(name = "PGA 3")
    private String pga3;

    /** PGA 4 */
    @Excel(name = "PGA 4")
    private String pga4;

    /** PGA 5 */
    @Excel(name = "PGA 5")
    private String pga5;

    /** PGA 6 */
    @Excel(name = "PGA 6")
    private String pga6;

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

    public void setCnName(String cnName) 
    {
        this.cnName = cnName;
    }

    public String getCnName() 
    {
        return cnName;
    }

    public void setEnName(String enName) 
    {
        this.enName = enName;
    }

    public String getEnName() 
    {
        return enName;
    }

    public void setCommonCode(String commonCode) 
    {
        this.commonCode = commonCode;
    }

    public String getCommonCode() 
    {
        return commonCode;
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

    public void setArticleDescription(String articleDescription) 
    {
        this.articleDescription = articleDescription;
    }

    public String getArticleDescription() 
    {
        return articleDescription;
    }

    public void setTotalTaxRate(String totalTaxRate) 
    {
        this.totalTaxRate = totalTaxRate;
    }

    public String getTotalTaxRate() 
    {
        return totalTaxRate;
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

    public void setCentsPerKg(String centsPerKg) 
    {
        this.centsPerKg = centsPerKg;
    }

    public String getCentsPerKg() 
    {
        return centsPerKg;
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
            .append("cnName", getCnName())
            .append("enName", getEnName())
            .append("commonCode", getCommonCode())
            .append("hsCode", getHsCode())
            .append("antiDumping", getAntiDumping())
            .append("cpsc", getCpsc())
            .append("needCert", getNeedCert())
            .append("articleDescription", getArticleDescription())
            .append("totalTaxRate", getTotalTaxRate())
            .append("baseTaxRate", getBaseTaxRate())
            .append("section301Code", getSection301Code())
            .append("section301Tax", getSection301Tax())
            .append("fl301232Code", getFl301232Code())
            .append("fl301232Tax", getFl301232Tax())
            .append("centsPerKg", getCentsPerKg())
            .append("pga1", getPga1())
            .append("pga2", getPga2())
            .append("pga3", getPga3())
            .append("pga4", getPga4())
            .append("pga5", getPga5())
            .append("pga6", getPga6())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
