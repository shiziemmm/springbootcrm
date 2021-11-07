package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Quotation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 报价编号
     */
    @TableId(value = "qu_id", type = IdType.AUTO)
    private Integer quId;

    /**
     * 机会编号
     */
    private Integer opId;

    /**
     * 报价主题
     */
    private String quTheme;

    /**
     * 转为订单状态
     */
    private String quState;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp quData;

    /**
     * 报价总金额
     */
    private Double quTotalAmount;

    /**
     * 预估毛利
     */
    private Double quEstimatedGrossProfit;

    /**
     * 交付说明
     */
    private String quDeliveryInstructions;

    /**
     * 备注
     */
    private String quRemarks;

    /**
     * 时效性
     */
    private Boolean quTimeliness;

    /**
     * 报价人
     */
    private Integer empId;

    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 报价单号
     */
    private String quNo;

    /**
     * 对应客户
     */
    @TableField(exist = false)
    private Client client;

    /**
     * 报价人
     */
    @TableField(exist = false)
    private Emp emp;

    /**
     * 对应机会
     */
    @TableField(exist = false)
    private Opportunity opportunity;



}
