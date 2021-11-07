package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Opportunity;
import com.trkj.crmproject.dao.OpportunityMapper;
import com.trkj.crmproject.service.OpportunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpportunityServiceImpl extends ServiceImpl<OpportunityMapper, Opportunity> implements OpportunityService {

    @Override
    public Boolean addOpp(Opportunity opportunity) {
        Boolean op=false;
        if(!opportunity.getOpTheme().equals("") && opportunity!=null && opportunity.getClientId()!=null){
           op =save(opportunity);
        }
        return op;
    }
}
