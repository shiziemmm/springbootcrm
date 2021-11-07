package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class CallCenten implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 客户服务编号
     */
    @TableId(value = "call_centen_id", type = IdType.AUTO)
    private Integer callCentenId;

    /**
     * 对应客户id
     */
    private Integer clientId;

    /**
     * 客户服务主题
     */
    private String callCentenTheme;

    /**
     * 客户服务类型
     */
    private String callCentenType;

    /**
     * 客户服务方式
     */
    private String callCentenWay;

    /**
     * 开始时间
     */
    private Timestamp callCentenStartTime;

    /**
     * 花费时间
     */
    private String callCentenTime;

    /**
     * 状态
     */
    private Integer callCentenState;

    /**
     * 执行人
     */
    private String callCentenExecutor;

    /**
     * 服务内容
     */
    private String callCentenContent;

    /**
     * 客户反馈
     */
    private String callCentenFeedback;

    /**
     * 备注
     */
    private String callCentenRemark;

    /**
     * 客户的所有者
     */
    @TableField(exist = false)
    private Client client;
}
