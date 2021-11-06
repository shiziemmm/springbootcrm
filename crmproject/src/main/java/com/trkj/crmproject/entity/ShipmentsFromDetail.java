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
public class ShipmentsFromDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 发货明细ID
     */
    @TableId(value = "sptdl_id", type = IdType.AUTO)
    private Integer sptdlId;

    /**
     * 发货ID
     */
    private String sptOn;

    /**
     * 产品ID
     */
    private Integer prId;

    private String productName;

    /**
     * 单价
     */
    private Integer unitPrice;

    /**
     * 进价
     */
    private Double money;

    /**
     * 产品数量
     */
    private Integer quantity;


}
