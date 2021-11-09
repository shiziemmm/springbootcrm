package com.trkj.crmproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class WarehouseEnter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入库单ID
     */
    @TableId(value = "we_id", type = IdType.AUTO)
    private Integer weId;

    /**
     * 仓库ID
     */
    private Integer weWaId;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Timestamp weEntertime;

    /**
     * 备注
     */
    private String weRemark;

    /**
     * 时效性
     */
    private Boolean weTimeliness;
    @TableField(exist = false)
    private List<WarehouseEnterGoods> enterGoods;
}
