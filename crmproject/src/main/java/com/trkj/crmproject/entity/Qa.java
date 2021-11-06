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
public class Qa implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * QA库编号
     */
    @TableId(value = "qa_id", type = IdType.AUTO)
    private Integer qaId;

    /**
     * 客户服务编号
     */
    private Integer callCentenId;

    /**
     * 问题
     */
    private String qaProblem;

    /**
     * 答案
     */
    private String qaExplain;

    /**
     * 内部提示
     */
    private String qaInternalTip;

    /**
     * 作者
     */
    private String qaAuthor;

    /**
     * 创建时间
     */
    private LocalDateTime qaTime;

    /**
     * 分类
     */
    private String qaClassify;

    /**
     * 状态
     */
    private String qaState;


}
