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
public class Linkman implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 联系人编号
     */
    @TableId(value = "linkman_id", type = IdType.AUTO)
    private Integer linkmanId;

    /**
     * 对应客户id
     */
    private Integer clientId;

    /**
     * 联系人姓名
     */
    private String linkmanName;

    /**
     * 联系人电话
     */
    private String linkmanPhone;

    /**
     * 联系人职位
     */
    private String linkmanPosition;

    /**
     * 联系人性别（男女）
     */
    private String linkmanSex;

    /**
     * 联系人备注
     */
    private String linkmanRemark;


}
