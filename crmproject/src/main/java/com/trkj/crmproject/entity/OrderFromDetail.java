package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class OrderFromDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单详情编号
     */
    @TableId(value = "odrdl_id", type = IdType.AUTO)
    private Integer odrdlId;

    /**
     * 外连接 连接订单编号
     */
    private Integer odrId;

    /**
     * 订单号
     */
    private String odrOn;

    /**
     * 产品ID
     */
    private Integer prId;

    /**
     * 产品名称
     */
    private String odrdlProductName;

    /**
     * 单价
     */
    private Double odrdlUnitPrice;

    /**
     * 金额
     */
    private Double odrdlMoney;

    /**
     * 产品数量
     */
    private Integer odrdlCount;

    /**
     * 折扣
     */
    private Double odrdlDiscount;

    /**
     * 备注
     */
    private String odrdlRemark;

    //关系
    @TableField(exist = false)
    private Product product;//商品对象
    @TableField(exist = false)
    private OrderFrom orderFrom;//订单对象
    @TableField(exist = false)
    private Client client;//客户对象
    @TableField(exist = false)
    private String empName;//负责人名称

}
