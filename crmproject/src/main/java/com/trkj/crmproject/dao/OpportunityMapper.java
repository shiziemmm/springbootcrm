package com.trkj.crmproject.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Opportunity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface OpportunityMapper extends BaseMapper<Opportunity> {
    List<Opportunity> selectMore(@Param("opportunity") Opportunity opportunity, Page<Opportunity> page);
    Opportunity selectByOpId(@Param("opid") Integer opid);
    List<Opportunity> selectAll();
}
