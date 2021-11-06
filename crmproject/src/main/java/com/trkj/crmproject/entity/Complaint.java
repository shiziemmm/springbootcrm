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
public class Complaint implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "com_id", type = IdType.AUTO)
    private Integer comId;

    /**
     * 投诉主题

     */
    private String comThemes;

    /**
     * 对应客户

     */
    private String customerName;

    /**
     * 首问接待人

     */
    private String comLinkman;

    /**
     * 分类

     */
    private String comType;

    /**
     * 描述

     */
    private String comDescribe;

    /**
     * 投诉日期

     */
    private LocalDateTime comTime;

    /**
     * 花费时间

     */
    private String comSpendTime;

    /**
     * 紧急程度

     */
    private String comUrgency;

    /**
     * 投诉人
     */
    private String comComplainant;

    /**
     * 处理过程

     */
    private String comTreating;

    /**
     * 客户反馈
     */
    private String comFeedback;

    /**
     * 回访确认
     */
    private String comReturn;

    /**
     * 备注
     */
    private String comRemark;

    /**
     * 是否删除（0：删除1：未删）

     */
    private Integer comAgree;


}
