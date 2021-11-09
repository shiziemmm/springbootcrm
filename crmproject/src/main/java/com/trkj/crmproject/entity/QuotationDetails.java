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
public class QuotationDetails implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 明细编号
     */
    @TableId(value = "qd_id", type = IdType.AUTO)
    private Integer qdId;

    /**
     * 产品ID
     */
    private Integer prId;

    /**
     * 报价编号
     */
    private Integer quId;

    /**
     * 数量
     */
    private Integer qdQuantity;

    /**
     * 单个产品总金额
     */
    private Double qdProTotalAmount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp qdCreationTime;

    /**
     * 时效性
     */
    private Boolean qdTimeliness;

    /**
     * 添加人
     */
    private Integer empId;

    /**
     * 报价（总）
     */
    @TableField(exist = false)
    private Double sum;

    /**
     * 毛利
     */
    @TableField(exist = false)
    private Double mao;

    /**
     * 对应商品
     */
    @TableField(exist = false)
    private Product product;

    /**
     * 添加人实体
     */
    @TableField(exist = false)
    private Emp emp;
    /**
     * 报价
     */
    @TableField(exist = false)
    private Quotation quotation;
    /**
     * 当前页码
     */
    @TableField(exist = false)
    private Integer pageNo;
    /**
     * 当前页数量
     */
    @TableField(exist = false)
    private Integer pageSize;

    /**
     * 条件查询产品名
     */
    @TableField(exist = false)
    private String  prName;

}
