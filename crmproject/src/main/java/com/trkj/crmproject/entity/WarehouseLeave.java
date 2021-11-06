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
 * @author MarkerHub
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出库单ID
     */
    @TableId(value = "wl_id", type = IdType.AUTO)
    private Integer wlId;

    /**
     * 仓库ID
     */
    private Integer wlWaId;

    /**
     * 对应订单ID
     */
    private Integer wlInId;

    /**
     * 标题
     */
    private String wlTheme;

    /**
     * 状态
     */
    private Integer wlState;

    /**
     * 执行人
     */
    private String wlExecute;

    /**
     * 出库时间
     */
    private LocalDateTime wlLeavetime;

    /**
     * 备注
     */
    private String wlRemark;

    /**
     * 时效性
     */
    private Boolean wlTimeliness;


}
