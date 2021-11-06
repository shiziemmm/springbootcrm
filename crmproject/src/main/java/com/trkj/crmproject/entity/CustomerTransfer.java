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
public class CustomerTransfer implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 转移编号
     */
    @TableId(value = "customer_transfer_id", type = IdType.AUTO)
    private Integer customerTransferId;

    /**
     * 对应客户id
     */
    private Integer clientId;

    /**
     * 原所有者
     */
    private String customerTransferOld;

    /**
     * 新所有者
     */
    private String customerTransferNew;

    /**
     * 转移备注
     */
    private String customerTransferRemark;

    /**
     * 操作者
     */
    private String customerTransferHandlers;

    /**
     * 操作时间
     */
    private LocalDateTime customerTransferTime;

    /**
     * 转移状态
     */
    private Integer customerTransferState;


}
