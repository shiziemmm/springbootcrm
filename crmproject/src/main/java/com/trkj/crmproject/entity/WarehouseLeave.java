package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Timestamp wlLeavetime;

    /**
     * 备注
     */
    private String wlRemark;

    /**
     * 时效性
     */
    private Boolean wlTimeliness;
    @TableField(exist = false)
    private List<WarehouseLeaveGoods> warehouseLeaveGoods;

    @TableField(exist = false)
    private String odrOn;
}
