package com.trkj.crmproject.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 条件查询
 */
@Data
public class SelectWhere {

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp startDate;//开始日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")//将数据库的类型返回成指定类型
    private Timestamp endDate;//结束日期

    private String searchLike;//模糊搜索
    private Long type;//类型
    private Double startPrice;//起始金额
    private Double endPrice;//结束金额
    private List<Long> empArr;//员工集合
}
