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
public class ShipmentsFrom implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 发货编号
     */
    @TableId(value = "spt_id", type = IdType.AUTO)
    private Integer sptId;

    /**
     * 订单编号
     */
    private String odrOn;

    /**
     * 发货单号
     */
    private String sptOn;

    /**
     * 订单状态
     */
    private String sptState;

    /**
     * 发货日期
     */
    private LocalDateTime sptDate;

    /**
     * 物流公司
     */
    private String sptLogisticsCompany;

    /**
     * 物流编号
     */
    private String sptLogistics;

    /**
     * 外连接 连接客户编号
     */
    private Integer clientId;


}
