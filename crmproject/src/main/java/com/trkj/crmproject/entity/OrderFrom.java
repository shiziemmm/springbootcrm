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
public class OrderFrom implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单编号
     */
    @TableId(value = "odr_id", type = IdType.AUTO)
    private Integer odrId;

    /**
     * 订单号
     */
    private String odrOn;

    /**
     * 订单总额
     */
    private Double odrPrice;

    /**
     * 订单发货总额
     */
    private Double odrOutPrice;

    /**
     * 对应客户名称
     */
    private String customerName;

    private String chanceName;

    /**
     * 订单生成日期
     */
    private LocalDateTime odrDate;

    /**
     * 订单最晚发货时间
     */
    private LocalDateTime odrOutDate;

    /**
     * 收件人姓名
     */
    private String odrAddressee;

    /**
     * 收件人电话
     */
    private String odrAddresseeTelephone;

    /**
     * 收件人移动电话
     */
    private String odrAddresseePhone;

    /**
     * 省区
     */
    private String odrProvince;

    /**
     * 城市
     */
    private String odrCity;

    /**
     * 地址类型
     */
    private String odrAddressType;

    /**
     * 收货人地址
     */
    private String odrAddresseeSite;

    /**
     * 邮编
     */
    private String odrPostcode;

    /**
     * 状态(实行中、结束、意外终止)
     */
    private Integer odrState;

    /**
     * 运费
     */
    private Double odrFreight;

    /**
     * 外连接 连接客户表
     */
    private Integer clientId;

    /**
     * 外连接  连接会机编号
     */
    private Integer opId;

    /**
     * 外连接 连接联系人编号
     */
    private Integer linkmanId;

    /**
     * 订单签约人
     */
    private Integer odrUsId;


}
