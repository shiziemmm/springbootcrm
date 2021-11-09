package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

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
public class ShipmentsFrom implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 发货编号
     */
    @TableId(value = "spt_id", type = IdType.AUTO)
    private Integer sptId;

    /**
     * 订单编号
     */
    private String odrOn;

    /**
     * 发货单号
     */
    private String sptOn;

    /**
     * 订单状态
     */
    private String sptState;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp sptDate;

    /**
     * 签收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp sptSignDate;

    /**
     * 运费
     */
    private Double sptSfPrice;//运费

    /**
     * 物流公司
     */
    private String sptLogisticsCompany;

    /**
     * 物流编号
     */
    private String sptLogistics;

    /**
     * 外连接 连接客户编号
     */
    private Integer clientId;

    //关系
    @TableField(exist = false)
    private OrderFrom orderFrom;//订单对象
    @TableField(exist = false)
    private List<ShipmentsFromDetail> shipmentsDetailsList;//发货详单集合


}
