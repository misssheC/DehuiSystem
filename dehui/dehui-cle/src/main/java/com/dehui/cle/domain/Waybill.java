package com.dehui.cle.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dehui.common.annotation.Excel;
import com.dehui.common.core.domain.BaseEntity;

/**
 * 我的运单对象 biz_waybill
 * 
 * @author ruoyi
 * @date 2026-10-29
 */
public class Waybill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 提单号 */
    @Excel(name = "提单号")
    private String billNo;

    /** 柜号 */
    @Excel(name = "柜号")
    private String containerNo;

    /** 货柜状态 */
    @Excel(name = "货柜状态")
    private String containerStatus;

    /** 客户 */
    @Excel(name = "客户")
    private String customer;

    /** 船司 */
    @Excel(name = "船司")
    private String carrier;

    /** 柜型 */
    @Excel(name = "柜型")
    private String containerType;

    /** 件数 */
    @Excel(name = "件数")
    private Long packageQty;

    /** 重量(KG) */
    @Excel(name = "重量(KG)")
    private BigDecimal weight;

    /** 方数(CBM) */
    @Excel(name = "方数(CBM)")
    private BigDecimal volume;

    /** 给柜日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "给柜日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cutoffDate;

    /** ETD */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "ETD", width = 30, dateFormat = "yyyy-MM-dd")
    private Date etd;

    /** 航程时间(Transit) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "航程时间(Transit)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transitDate;

    /** ETA */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "ETA", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eta;

    /** 清关状态 */
    @Excel(name = "清关状态")
    private String customsStatus;

    /** 是否已发邮件资料 */
    @Excel(name = "是否已发邮件资料")
    private Long isSendEmail;

    /** 清关时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "清关时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date customsDate;

    /** 客户备注 */
    @Excel(name = "客户备注")
    private String customerRemark;

    /** 航线 */
    @Excel(name = "航线")
    private String route;

    /** 起运港/起飞点 */
    @Excel(name = "起运港/起飞点")
    private String pol;

    /** 中转港 */
    @Excel(name = "中转港")
    private String transPort;

    /** 目的港/落地地点 */
    @Excel(name = "目的港/落地地点")
    private String pod;

    /** 船名航次 */
    @Excel(name = "船名航次")
    private String vesselVoyage;

    /** 中转时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "中转时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transitTime;

    /** DO日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "DO日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date doDate;

    /** 放行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "放行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 运输方式 */
    @Excel(name = "运输方式")
    private String transportType;

    /** 计价方式 */
    @Excel(name = "计价方式")
    private String priceType;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 录入方式 */
    @Excel(name = "录入方式")
    private String inputType;

    /** 进口商 */
    @Excel(name = "进口商")
    private String importer;

    /** 7501状态 */
    @Excel(name = "7501状态")
    private Long Status7501;

    /** ISF匹配状态 */
    @Excel(name = "ISF匹配状态")
    private Long isf;

    /** 是否我方申报ISF */
    @Excel(name = "是否我方申报ISF")
    private Long isMyisf;

    /** 查验代码 */
    @Excel(name = "查验代码")
    private String checkCode;

    /** 查验进展 */
    @Excel(name = "查验进展")
    private String checkProgress;

    /** 布控方式 */
    @Excel(name = "布控方式")
    private String controlMode;

    /** 业务员 */
    @Excel(name = "业务员")
    private String salesman;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operateUser;

    /** 内部备注 */
    @Excel(name = "内部备注")
    private String innerRemark;

    private Long userId;

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

    public void setBillNo(String billNo) 
    {
        this.billNo = billNo;
    }

    public String getBillNo() 
    {
        return billNo;
    }

    public void setContainerNo(String containerNo) 
    {
        this.containerNo = containerNo;
    }

    public String getContainerNo() 
    {
        return containerNo;
    }

    public void setContainerStatus(String containerStatus) 
    {
        this.containerStatus = containerStatus;
    }

    public String getContainerStatus() 
    {
        return containerStatus;
    }

    public void setCustomer(String customer) 
    {
        this.customer = customer;
    }

    public String getCustomer() 
    {
        return customer;
    }

    public void setCarrier(String carrier) 
    {
        this.carrier = carrier;
    }

    public String getCarrier() 
    {
        return carrier;
    }

    public void setContainerType(String containerType) 
    {
        this.containerType = containerType;
    }

    public String getContainerType() 
    {
        return containerType;
    }

    public void setPackageQty(Long packageQty) 
    {
        this.packageQty = packageQty;
    }

    public Long getPackageQty() 
    {
        return packageQty;
    }

    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }

    public void setVolume(BigDecimal volume) 
    {
        this.volume = volume;
    }

    public BigDecimal getVolume() 
    {
        return volume;
    }

    public void setCutoffDate(Date cutoffDate) 
    {
        this.cutoffDate = cutoffDate;
    }

    public Date getCutoffDate() 
    {
        return cutoffDate;
    }

    public void setEtd(Date etd) 
    {
        this.etd = etd;
    }

    public Date getEtd() 
    {
        return etd;
    }

    public void setTransitDate(Date transitDate) 
    {
        this.transitDate = transitDate;
    }

    public Date getTransitDate() 
    {
        return transitDate;
    }

    public void setEta(Date eta) 
    {
        this.eta = eta;
    }

    public Date getEta() 
    {
        return eta;
    }

    public void setCustomsStatus(String customsStatus) 
    {
        this.customsStatus = customsStatus;
    }

    public String getCustomsStatus() 
    {
        return customsStatus;
    }

    public void setIsSendEmail(Long isSendEmail) 
    {
        this.isSendEmail = isSendEmail;
    }

    public Long getIsSendEmail() 
    {
        return isSendEmail;
    }

    public void setCustomsDate(Date customsDate) 
    {
        this.customsDate = customsDate;
    }

    public Date getCustomsDate() 
    {
        return customsDate;
    }

    public void setCustomerRemark(String customerRemark) 
    {
        this.customerRemark = customerRemark;
    }

    public String getCustomerRemark() 
    {
        return customerRemark;
    }

    public void setRoute(String route) 
    {
        this.route = route;
    }

    public String getRoute() 
    {
        return route;
    }

    public void setPol(String pol) 
    {
        this.pol = pol;
    }

    public String getPol() 
    {
        return pol;
    }

    public void setTransPort(String transPort) 
    {
        this.transPort = transPort;
    }

    public String getTransPort() 
    {
        return transPort;
    }

    public void setPod(String pod) 
    {
        this.pod = pod;
    }

    public String getPod() 
    {
        return pod;
    }

    public void setVesselVoyage(String vesselVoyage) 
    {
        this.vesselVoyage = vesselVoyage;
    }

    public String getVesselVoyage() 
    {
        return vesselVoyage;
    }

    public void setTransitTime(Date transitTime) 
    {
        this.transitTime = transitTime;
    }

    public Date getTransitTime() 
    {
        return transitTime;
    }

    public void setDoDate(Date doDate) 
    {
        this.doDate = doDate;
    }

    public Date getDoDate() 
    {
        return doDate;
    }

    public void setReleaseTime(Date releaseTime) 
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime() 
    {
        return releaseTime;
    }

    public void setTransportType(String transportType) 
    {
        this.transportType = transportType;
    }

    public String getTransportType() 
    {
        return transportType;
    }

    public void setPriceType(String priceType) 
    {
        this.priceType = priceType;
    }

    public String getPriceType() 
    {
        return priceType;
    }

    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }

    public void setInputType(String inputType) 
    {
        this.inputType = inputType;
    }

    public String getInputType() 
    {
        return inputType;
    }

    public void setImporter(String importer) 
    {
        this.importer = importer;
    }

    public String getImporter() 
    {
        return importer;
    }

    public void setStatus7501(Long Status7501)
    {
        this.Status7501 = Status7501;
    }

    public Long getStatus7501()
    {
        return Status7501;
    }

    public void setIsf(Long isf) 
    {
        this.isf = isf;
    }

    public Long getIsf() 
    {
        return isf;
    }

    public void setIsMyisf(Long isMyisf) 
    {
        this.isMyisf = isMyisf;
    }

    public Long getIsMyisf() 
    {
        return isMyisf;
    }

    public void setCheckCode(String checkCode) 
    {
        this.checkCode = checkCode;
    }

    public String getCheckCode() 
    {
        return checkCode;
    }

    public void setCheckProgress(String checkProgress) 
    {
        this.checkProgress = checkProgress;
    }

    public String getCheckProgress() 
    {
        return checkProgress;
    }

    public void setControlMode(String controlMode) 
    {
        this.controlMode = controlMode;
    }

    public String getControlMode() 
    {
        return controlMode;
    }

    public void setSalesman(String salesman) 
    {
        this.salesman = salesman;
    }

    public String getSalesman() 
    {
        return salesman;
    }

    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }

    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }

    public void setOperateUser(String operateUser) 
    {
        this.operateUser = operateUser;
    }

    public String getOperateUser() 
    {
        return operateUser;
    }

    public void setInnerRemark(String innerRemark) 
    {
        this.innerRemark = innerRemark;
    }

    public String getInnerRemark() 
    {
        return innerRemark;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("billNo", getBillNo())
            .append("containerNo", getContainerNo())
            .append("containerStatus", getContainerStatus())
            .append("customer", getCustomer())
            .append("carrier", getCarrier())
            .append("containerType", getContainerType())
            .append("packageQty", getPackageQty())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .append("cutoffDate", getCutoffDate())
            .append("etd", getEtd())
            .append("transitDate", getTransitDate())
            .append("eta", getEta())
            .append("customsStatus", getCustomsStatus())
            .append("isSendEmail", getIsSendEmail())
            .append("customsDate", getCustomsDate())
            .append("customerRemark", getCustomerRemark())
            .append("route", getRoute())
            .append("pol", getPol())
            .append("transPort", getTransPort())
            .append("pod", getPod())
            .append("vesselVoyage", getVesselVoyage())
            .append("transitTime", getTransitTime())
            .append("doDate", getDoDate())
            .append("releaseTime", getReleaseTime())
            .append("transportType", getTransportType())
            .append("priceType", getPriceType())
            .append("businessType", getBusinessType())
            .append("inputType", getInputType())
            .append("importer", getImporter())
            .append("Status7501", getStatus7501())
            .append("isf", getIsf())
            .append("isMyisf", getIsMyisf())
            .append("checkCode", getCheckCode())
            .append("checkProgress", getCheckProgress())
            .append("controlMode", getControlMode())
            .append("salesman", getSalesman())
            .append("operator", getOperator())
            .append("supplier", getSupplier())
            .append("operateUser", getOperateUser())
            .append("innerRemark", getInnerRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
