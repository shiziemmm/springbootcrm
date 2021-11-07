package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Opportunity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface OpportunityService extends IService<Opportunity> {
    Boolean addOpp(Opportunity opportunity);
    Page<Opportunity> selectList(Opportunity opportunity, Page<Opportunity> page);
}
