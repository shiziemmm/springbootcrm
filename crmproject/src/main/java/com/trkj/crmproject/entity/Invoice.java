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
public class Invoice implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "inv_id", type = IdType.AUTO)
    private Integer invId;

    /**
     * 订单编号（外键字段链接订单表）

     */
    private String orderNumber;

    /**
     * 客户名称

     */
    private String customerName;

    /**
     * 开票日期

     */
    private LocalDateTime invTime;

    /**
     * 票据类型

     */
    private String invType;

    /**
     * 开票金额

     */
    private Double invSum;

    /**
     * 是否回款(0:已回1：未回)

     */
    private String invWhether;

    /**
     * 负责人

     */
    private String principal;

    /**
     * 备注

     */
    private String invRemarkinvRemark;

    /**
     * 是否删除（0：删除1：未删）

     */
    private Boolean invAgree;


}
