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
public class Demand implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 需求编号
     */
    @TableId(value = "de_id", type = IdType.AUTO)
    private Integer deId;

    /**
     * 机会编号
     */
    private Integer opId;

    /**
     * 需求主题
     */
    private String deTheme;

    /**
     * 记录时间
     */
    private LocalDateTime deRecordTime;

    /**
     * 需求提供人
     */
    private String deProvider;

    /**
     * 需求内容
     */
    private String deContent;

    /**
     * 时效性
     */
    private Boolean deTimeliness;

    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 记录人
     */
    private Integer empId;


}
