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
public class WarehouseEnter implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 入库单ID
     */
    @TableId(value = "we_id", type = IdType.AUTO)
    private Integer weId;

    /**
     * 仓库ID
     */
    private Integer waId;

    /**
     * 主题
     */
    private String weTheme;

    /**
     * 状态
     */
    private Integer weState;

    /**
     * 填单人
     */
    private String weName;

    /**
     * 入库时间
     */
    private LocalDateTime weEntertime;

    /**
     * 备注
     */
    private String weRemark;

    /**
     * 时效性
     */
    private Integer weTimeliness;


}
