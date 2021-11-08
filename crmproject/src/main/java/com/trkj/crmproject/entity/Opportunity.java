package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class Opportunity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 机会编号
     */
    @TableId(value = "op_id", type = IdType.AUTO)
    private Integer opId;

    /**
     * 客户
     */
    private Integer clientId;

    /**
     * 机会主题
     */
    private String opTheme;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp opDate;

    /**
     * 状态
     */
    private String opState;

    /**
     * 机会类型
     */
    private String opType;

    /**
     * 发现时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp opFindtime;

    /**
     * 客户来源
     */
    private String opCuSource;

    /**
     * 提供人
     */
    private String opProvider;

    /**
     * 客户需求
     */
    private String opCuDemand;

    /**
     * 预期签单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp opExpectedSigningDate;

    /**
     * 可能性
     */
    private String opPossibility;

    /**
     * 预期金额
     */
    private Double opExpectedAmount;

    /**
     * 优先
     */
    private String opPriority;

    /**
     * 阶段
     */
    private String opStage;

    /**
     * 阶段备注
     */
    private String opStageRemarks;

    /**
     * 时效性
     */
    private Boolean opTimeliness;

    /**
     * 客户联系人
     */
    private Integer linkmanId;

    /**
     * 负责人
     */
    private Integer empId;

    /**
     * 阶段停留
     */
    private Integer opPhaseStay;

    /**
     * 对应客户
     */
    @TableField(exist = false)
    private Client client;

    /**
     * 客户联系人
     */
    @TableField(exist = false)
    private Linkman linkman;

    /**
     * 临时字段，用来存储状态和阶段的值去后台查询
     */
    @TableField(exist = false)
    private String stateStage;
    /**
     * 负责人信息
     */
    @TableField(exist = false)
    private Emp emp;

    /**
     * 临时字段，存储当前页码数
     */
    @TableField(exist = false)
    private Integer pageNo;

    /**
     * 临时字段，存储当前页的条数
     */
    @TableField(exist = false)
    private Integer pageSize;

    /**
     * 临时字段，存储查询框的查询类别内容
     */
    @TableField(exist = false)
    private String selectType;
    /**
     * 临时字段，存储查询框的查询类别
     */
    @TableField(exist = false)
    private String selectTypes;

    /**
     * 多方，对应的报价
     */
    @TableField(exist = false)
    private List<Quotation> quotations;

    /**
     * 多方，对应的需求
     */
    @TableField(exist = false)
    private List<Demand> demands;

    /**
     * 多方，对应的解决方案
     */
    @TableField(exist = false)
    private List<Programme> programmes;

}
