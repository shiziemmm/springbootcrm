package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Integer odrId;

    /**
     * 回款期次编号（外链接回款期次编号）

     */
    private Integer isuId;

    /**
     * 期次名称（由回款期次名称和选中第几期回款拼接）

     */
    private String futuresName;

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
    private LocalDateTime plaTime;

    /**
     * 逾期天数（后一次回款时间不得与前一次回款时间超过30天）

     */
    private String daysOverdue;

    /**
     * 是否删除（0：删除1：未删）

     */
    private Integer plaAgree;

    /**
     * 客户名称

     */
    private String customerName;


}
