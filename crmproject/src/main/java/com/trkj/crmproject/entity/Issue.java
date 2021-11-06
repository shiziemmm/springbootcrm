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
public class Issue implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号

     */
    @TableId(value = "isu_id", type = IdType.AUTO)
    private Integer isuId;

    /**
     * 期次名称

     */
    private String isuName;


}
