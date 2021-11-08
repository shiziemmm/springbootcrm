package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.dao.*;
import com.trkj.crmproject.entity.*;
import com.trkj.crmproject.entity.Vo.AddOdrVO;
import com.trkj.crmproject.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Autowired
    private QuotationMapper quotationMapper;
    @Autowired
    private QuotationDetailsMapper quotationDetailsMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private ProgrammeMapper programmeMapper;
    @Autowired
    private OrderFromDetailService orderFromDetailService;
    @Autowired
    private OrderFromService orderFromService;
    @Autowired
    private QuotationService quotationService;
    @Override
    public Boolean addOpp(Opportunity opportunity) {
        Boolean op=false;
        if(!opportunity.getOpTheme().equals("") && opportunity!=null && opportunity.getClientId()!=null){
           op =saveOrUpdate(opportunity);
        }
        return op;
    }

    @Override
    public Page<Opportunity> selectList(Opportunity opportunity, Page<Opportunity> page) {
        return page.setRecords(opportunityMapper.selectMore(opportunity,page));
    }

    @Override
    public Opportunity selectById(Integer opid) {
        return opportunityMapper.selectByOpId(opid);
    }

    @Override
    public Boolean addOdr(AddOdrVO addOdrVO) {
        if(addOdrVO.getOdr()!=null){
            addOdrVO.getOdr().setOdrDate(new Timestamp(System.currentTimeMillis()));
            addOdrVO.getOdr().setOdrState(1);
            Boolean op=orderFromService.addOrder(addOdrVO.getOdr());
            if(op && addOdrVO.getOdr().getOdrId()>0 && addOdrVO.getOdr().getQuId()!=null){
                Quotation quotation=quotationService.getById(addOdrVO.getOdr().getQuId());
                if(quotation!=null) {
                    quotation.setQuState("转成订单");
                    op=quotationService.updateById(quotation);
                    if(op){
                        List<OrderFromDetail> list=new ArrayList<>();
                        if(addOdrVO.getQuotationDetails()!=null && addOdrVO.getQuotationDetails().size()>0){
                            for (QuotationDetails qu : addOdrVO.getQuotationDetails()) {
                                OrderFromDetail order=new OrderFromDetail();
                                order.setPrId(qu.getProduct().getPrId());
                                order.setOdrdlProductName(qu.getProduct().getPrName());
                                order.setOdrdlUnitPrice(qu.getProduct().getPrPrice());
                                order.setOdrdlMoney(qu.getQdProTotalAmount());
                                order.setOdrdlCount(qu.getQdQuantity());
                                order.setOdrId(addOdrVO.getOdr().getOdrId());
                                list.add(order);
                            }
                            if(list!=null && list.size()>0){
                                orderFromDetailService.saveBatch(list);
                                return true;
                            }
                        }
                    }
                    return op;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer opid) {
        //通过id查询出所有包含的数据
        Integer i =-1;
        if(opid!=null){
            Opportunity opportunity=getById(opid);
            if(opportunity!=null){
                QueryWrapper<Quotation> quotationsQuery = new QueryWrapper<>();
                quotationsQuery.eq("op_id",opid);
                List<Quotation> quotations = quotationMapper.selectList(quotationsQuery);
                if(quotations!=null && quotations.size()>0){
                    for (Quotation quotation : quotations) {
                        QueryWrapper<QuotationDetails> quotationDetailsQuery = new QueryWrapper<>();
                        quotationDetailsQuery.eq("qu_Id",quotation.getQuId());
                        List<QuotationDetails> quotationDetails = quotationDetailsMapper.selectList(quotationDetailsQuery);
                        if(quotationDetails!=null && quotationDetails.size()>0){
                            for (QuotationDetails detail : quotationDetails) {
                                detail.setQdTimeliness(true);
                                i=quotationDetailsMapper.updateById(detail);
                            }
                        }
                        quotation.setQuTimeliness(true);
                        i=quotationMapper.updateById(quotation);
                    }
                }
                QueryWrapper<Demand> demandsQuery = new QueryWrapper<>();
                demandsQuery.eq("op_id",opid);
                List<Demand> demands = demandMapper.selectList(demandsQuery);
                if(demands!=null && demands.size()>0){
                    for (Demand demand : demands) {
                        demand.setDeTimeliness(true);
                        i=demandMapper.updateById(demand);
                    }
                }
                QueryWrapper<Programme> programmesQuery = new QueryWrapper<>();
                programmesQuery.eq("op_id",opid);
                List<Programme> programmes = programmeMapper.selectList(programmesQuery);
                if(programmes!=null && programmes.size()>0){
                    for (Programme programme : programmes) {
                        programme.setProTimeliness(true);
                        i=programmeMapper.updateById(programme);
                    }
                }
                opportunity.setOpTimeliness(true);
                i=opportunityMapper.updateById(opportunity);
            }
            if(i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Opportunity> selectAll() {
        return opportunityMapper.selectAll();
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
