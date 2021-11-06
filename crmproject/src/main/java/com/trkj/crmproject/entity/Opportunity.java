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
    private LocalDateTime opDate;

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
    private LocalDateTime opFindtime;

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
    private LocalDateTime opExpectedSigningDate;

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


}
