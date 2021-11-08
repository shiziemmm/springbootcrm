package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.dao.QuotationDetailsMapper;
import com.trkj.crmproject.entity.Programme;
import com.trkj.crmproject.entity.Quotation;
import com.trkj.crmproject.dao.QuotationMapper;
import com.trkj.crmproject.entity.QuotationDetails;
import com.trkj.crmproject.service.QuotationService;
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
public class QuotationServiceImpl extends ServiceImpl<QuotationMapper, Quotation> implements QuotationService {

    @Autowired
    private QuotationMapper quotationMapper;
    @Autowired
    private QuotationDetailsMapper quotationDetailsMapper;
    @Override
    public Integer addQuotation(Quotation quotation) {
        Integer id=-1;
        if(!"".equals(quotation.getQuTheme()) && quotation.getQuTheme()!=null){
            Boolean op=save(quotation);
            if(op){
                id=quotation.getQuId();
                return id;
            }
        }
        return id;
    }

    @Override
    public Page<Quotation> selectAll(Page<Quotation> page, Quotation quotation) {
        return page.setRecords(quotationMapper.selectAll(page,quotation));
    }

    @Override
    public Boolean del(Integer quid) {
        Integer i=-1;
        if(quid!=null){
            Quotation quotation=getById(quid);
            if(quotation!=null){
                QueryWrapper<QuotationDetails> quotationsQuery = new QueryWrapper<>();
                quotationsQuery.eq("qu_id",quid);
                List<QuotationDetails> quotations = quotationDetailsMapper.selectList(quotationsQuery);
                if(quotations!=null && quotations.size()>0){
                    for (QuotationDetails quotationDetails : quotations) {
                        quotationDetails.setQdTimeliness(true);
                        i=quotationDetailsMapper.updateById(quotationDetails);
                    }
                }
                quotation.setQuTimeliness(true);
                return updateById(quotation);
            }
        }
        return false;
    }

    @Override
    public Quotation selectById(Integer quid) {
        return quotationMapper.selectByQuId(quid);
    }
}
