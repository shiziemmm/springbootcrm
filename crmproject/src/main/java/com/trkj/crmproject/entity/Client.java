package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"handler"})
public class Client implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 客户编号
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private Integer clientId;

    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 客户手机号码
     */
    private String clientPhone;

    /**
     * 客户创建时间
     */
    private Timestamp clientTime;

    /**
     * 公海客户
     */
    private Integer clientSeas;

    /**
     * 客户种类
     */
    private String clientKind;

    /**
     * 客户生命周期
     */
    private String clientPeriod;

    /**
     * 客户定性
     */
    private String clientQualitative;

    /**
     * 客户定级
     */
    private String clientRank;

    /**
     * 预计签约时间
     */
    private String clientSigningDate;

    /**
     * 预计签约金额
     */
    private Double clientContractAmount;

    /**
     * 客户来源
     */
    private String clientSource;

    /**
     * 客户地址
     */
    private String clientSite;

    /**
     * 客户备注
     */
    private String clientRemark;

    /**
     * 所有者
     */
    private String clientTransfer;

    /**
     * 客户下的联系人
     */
    @TableField(exist = false)
    private List<Linkman> linkmen;

    /**
     * 客户的所有者
     */
    @TableField(exist = false)
    private Emp emp;
}
