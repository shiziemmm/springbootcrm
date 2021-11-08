package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
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
public class Concerned implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "con_id", type = IdType.AUTO)
    private Integer conId;

    /**
     * 主题

     */
    private String conTheme;

    /**
     * 对应客户

     */
    private String customerName;

    /**
     * 联系人

     */
    private String conLinkman;

    /**
     * 日期

     */
    private Timestamp conTconTime;

    /**
     * 执行人

     */
    private String conExecutor;

    /**
     * 类型

     */
    private String conType;

    /**
     * 关怀内容

     */
    private String conContent;

    /**
     * 客户反馈
     */
    private String conFeedback;

    /**
     * 备注

     */
    private String conRemark;

    /**
     * 是否删除（0：删除1：未删）

     */
    private String conAgree;



}
