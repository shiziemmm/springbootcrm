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
public class Serve implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "ser_id", type = IdType.AUTO)
    private Integer serId;

    /**
     * 主题
主题
     */
    private String serTheme;

    /**
     * 对应客户

     */
    private String customerName;

    /**
     * 客户联系人

     */
    private String serLinkman;

    /**
     * 执行人

     */
    private String serExecutor;

    /**
     * 服务类型

     */
    private String serType;

    /**
     * 关怀内容
     */
    private String serContent;

    /**
     * 客户反馈

     */
    private String serFeedback;

    /**
     * 是否删除（0：删除1：未删）

     */
    private Boolean serAgree;


}
