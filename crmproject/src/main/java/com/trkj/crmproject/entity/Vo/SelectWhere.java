package com.trkj.crmproject.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 条件查询
 */
@Data
public class Where {

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Date startDate;//开始日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Date endDate;//结束日期

    private String searchLike;//模糊搜索
    private Long doctorType;//类型
    private List usId;//员工编号
}
