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
public class Warehouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 仓库ID
     */
    @TableId(value = "wa_id", type = IdType.AUTO)
    private Integer waId;

    /**
     * 表ID
     */
    private Integer wiId;

    /**
     * 仓库名称
     */
    private String waName;

    /**
     * 仓库位置
     */
    private String waLocation;

    /**
     * 用途描述
            用途描述
            用途描述
     */
    private String waDescribes;

    /**
     * 状态
     */
    private Integer waState;

    /**
     * 时效性
     */
    private Integer waTimeliness;


}
