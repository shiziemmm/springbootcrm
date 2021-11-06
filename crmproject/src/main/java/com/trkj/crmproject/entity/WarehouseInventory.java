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
public class WarehouseInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "wi_id", type = IdType.AUTO)
    private Integer wiId;

    /**
     * 仓库ID
     */
    private Integer wiWaId;

    /**
     * 产品ID
     */
    private Integer wiPrId;

    /**
     * 仓库名称
     */
    private String wiWaname;

    /**
     * 产品名称
     */
    private String wiPrname;

    /**
     * 数量
     */
    private Integer wiCount;

    /**
     * 时效性
     */
    private Boolean wiTimeliness;


}
