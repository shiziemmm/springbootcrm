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
public class WarehouseLeaveGoods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 明细ID
     */
    @TableId(value = "wlg_id", type = IdType.AUTO)
    private Integer wlgId;

    /**
     * 产品ID
     */
    private Integer prId;

    /**
     * 出库单ID
     */
    private Integer wlId;

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
    private Integer wlgTimeliness;


}
