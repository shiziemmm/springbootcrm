package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class Plan implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "pla_id", type = IdType.AUTO)
    private Integer plaId;

    /**
     * 订单编号（外键字段链接订单表）

     */
    private String odrId;



    /**
     * 负责人

     */
    private String principal;

    /**
     * 付款方式

     */
    private String payment;

    /**
     * 计划回款金额

     */
    private Double plaPrice;

    /**
     * 计划回款日期

     */
    private Timestamp plaTime;



    /**
     * 是否删除（0：删除1：未删）

     */
    private Integer plaAgree;

    /**
     * 客户名称

     */
    private String customerName;

    private String plaWhether;

    private String invoice;


}
