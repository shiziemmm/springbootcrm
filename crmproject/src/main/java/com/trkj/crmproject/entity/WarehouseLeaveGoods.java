package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class WarehouseLeaveGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细ID
     */
    @TableId(value = "wlg_id", type = IdType.AUTO)
    private Integer wlgId;

    /**
     * 产品ID
     */
    private Integer wlgPrId;

    /**
     * 出库单ID
     */
    private Integer wlgWlId;

    /**
     * 产品名称
     */
    private String wlgPrname;

    /**
     * 出库数量
     */
    private Integer wlgCount;

    /**
     * 备注
     */
    private String wlgRemark;

    /**
     * 时效性
     */
    private Boolean wlgTimeliness;

    @TableField(exist = false)
    private Product product;
}
