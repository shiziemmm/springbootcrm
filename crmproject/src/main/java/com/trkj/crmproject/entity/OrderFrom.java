package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp odrDate;

    /**
     * 订单最晚发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp odrOutDate;

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

    /**
     * 订单主题
     */
    private  String odrName;

    /**
     * 订单发货状态
     */
    private String odrShipmentsState;


    /**
     * 订单备注
     */
    private String odrRemark;
    /**
     * 回款金额
     */
    private Double odrReturnPrice;

    /**
     * 外连接  连接报价编号
     */
    private Integer quId;

    /**
     * 回款金额
     */
    private Double odrReturnPrice;

    //关系

    @TableField(exist = false)
    private Client client;//客户对象
    @TableField(exist = false)
    private List<OrderFromDetail> orderFromDetail = null;//订单详情表
    @TableField(exist = false)
    private Quotation quotation;//报价对象
    @TableField(exist = false)
    private Emp emp;//员工对象

    private Integer odrCount;//产品数量

    public OrderFrom() {
    }

    public OrderFrom(Integer odrId, Double odrPrice, int odrCount) {
        this.odrId = odrId;
        this.odrPrice = odrPrice;
        this.odrCount = odrCount;
    }
}
