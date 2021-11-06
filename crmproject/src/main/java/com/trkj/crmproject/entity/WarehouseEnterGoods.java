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
 * @author MarkerHub
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseEnterGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细ID
     */
    @TableId(value = "weg_id", type = IdType.AUTO)
    private Integer wegId;

    /**
     * 入库单ID
     */
    private Integer wegWeId;

    /**
     * 产品ID
     */
    private Integer wegPrId;

    /**
     * 产品名称
     */
    private String wegPrname;

    /**
     * 数量
     */
    private Integer wegCount;

    /**
     * 备注
     */
    private String wegRemark;

    /**
     * 时效性
     */
    private Boolean wegTimeliness;


}
