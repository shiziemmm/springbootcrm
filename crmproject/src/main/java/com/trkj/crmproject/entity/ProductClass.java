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
public class ProductClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品分类表
     */
    @TableId(value = "pc_id", type = IdType.AUTO)
    private Integer pcId;

    /**
     * 父ID
     */
    private Integer pcPid;

    /**
     * 分类名称
     */
    private String pcName;

    /**
     * 时效性
     */
    private Boolean pcTimeliness;


}
