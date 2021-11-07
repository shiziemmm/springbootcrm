package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.entity.Opportunity;
import com.trkj.crmproject.dao.OpportunityMapper;
import com.trkj.crmproject.service.EmpService;
import com.trkj.crmproject.service.OpportunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private EmpService empService;
    @Autowired
    private OpportunityMapper opportunityMapper;
    @Override
    public Boolean addOpp(Opportunity opportunity) {
        Boolean op=false;
        if(!opportunity.getOpTheme().equals("") && opportunity!=null && opportunity.getClientId()!=null){
           op =save(opportunity);
        }
        return op;
    }

    @Override
    public Page<Opportunity> selectList(Opportunity opportunity, Page<Opportunity> page) {
        return page.setRecords(opportunityMapper.selectMore(opportunity,page));
    }

    public Page<Opportunity> selectAll(Opportunity opportunity,Page<Opportunity> page) {
        QueryWrapper<Opportunity> qw=new QueryWrapper<Opportunity>();
        if(!"".equals(opportunity.getOpTheme()) && opportunity.getOpTheme()!=null){
            qw.like("op_theme",opportunity.getOpTheme());
        }
        if(opportunity.getStateStage()!=null){
            qw.like("op_state",opportunity.getOpState()).or().like("op_stage",opportunity.getOpStage());
        }
        for (Opportunity opp : opportunityMapper.selectList(qw)) {
            if(opp.getClientId()!=null){
                Client client=clientService.getById(opp.getClientId());
                opp.setClient(client);
            }
            if(opp.getEmpId()!=null){
                Emp emp=empService.getById(opp.getEmpId());
                opp.setEmp(emp);
            }
        }
        return page.setRecords(opportunityMapper.selectList(qw));
    }
}
