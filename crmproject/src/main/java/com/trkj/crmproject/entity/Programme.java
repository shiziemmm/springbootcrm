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
public class Programme implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 方案编号
     */
    @TableId(value = "pro_id", type = IdType.AUTO)
    private Integer proId;

    /**
     * 机会编号
     */
    private Integer opId;

    /**
     * 方案主题
     */
    private String proTheme;

    /**
     * 方案内容
     */
    private String proContent;

    /**
     * 客户反馈
     */
    private String proCustomerFeedback;

    /**
     * 记录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp proRecordTime;

    /**
     * 时效性
     */
    private Boolean proTimeliness;

    /**
     * 记录人
     */
    private Integer empId;

    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 对应客户
     */
    @TableField(exist = false)
    private Client client;
    /**
     * 对应机会
     */
    @TableField(exist = false)
    private Opportunity opportunity;
}
