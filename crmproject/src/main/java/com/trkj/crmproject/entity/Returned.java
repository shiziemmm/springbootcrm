package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
public class Returned implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "ret_id", type = IdType.AUTO)
    private Integer retId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 订单编号（外键字段链接订单表）
     */
    private String orderNumber;

    /**
     * 回款期次编号（外链接回款期次编号）
     */
    private Integer isuId;

    /**
     * 期次名称（由回款期次名称和选中第几期回款拼接）

     */
    private String futuresName;

    /**
     * 经办人

     */
    private String principal;

    /**
     * 回款金额

     */
    private Double retPrice;

    /**
     * 回款日期

     */
    private Timestamp retTime;

    /**
     * 是否回款

     */
    private String retWhether;

    /**
     * 已开发票

     */
    private String invoice;

    /**
     * 是否删除（0：删除1：未删）

     */
    private Boolean retAgree;


}
