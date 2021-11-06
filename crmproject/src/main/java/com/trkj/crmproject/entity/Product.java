package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 产品ID
     */
    @TableId(value = "pr_id", type = IdType.AUTO)
    private Integer prId;

    private Integer qdId;

    /**
     * 表ID
     */
    private Integer wiId;

    /**
     * 产品分类表
     */
    private Integer pcId;

    /**
     * 明细ID
     */
    private Integer wegId;

    /**
     * 产品名称
     */
    private String prName;

    /**
     * 编码
     */
    private String prCoding;

    /**
     * 状态
     */
    private Integer prState;

    /**
     * 单位
     */
    private String prUnit;

    /**
     * 价格
     */
    private Double prPrice;

    /**
     * 成本价格
     */
    private Double prCostPrice;

    /**
     * 型号
     */
    private String prModel;

    /**
     * 重量(kg)
     */
    private Integer prWeight;

    /**
     * 明细概要
     */
    private String prOutline;

    /**
     * 产品说明
     */
    private String prDescription;

    /**
     * 备注
     */
    private String prRemark;

    /**
     * 时效性
     */
    private Integer prTimeliness;


}
