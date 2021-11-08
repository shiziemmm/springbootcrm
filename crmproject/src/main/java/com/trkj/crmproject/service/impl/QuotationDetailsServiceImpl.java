package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Quotation;
import com.trkj.crmproject.entity.QuotationDetails;
import com.trkj.crmproject.dao.QuotationDetailsMapper;
import com.trkj.crmproject.service.QuotationDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
public class QuotationDetailsServiceImpl extends ServiceImpl<QuotationDetailsMapper, QuotationDetails> implements QuotationDetailsService {

    @Autowired
    private QuotationService quotationService;
    @Override
    public Boolean addDetails(List<QuotationDetails> quotationDetails) {
        for (QuotationDetails qu : quotationDetails) {
            qu.setQdCreationTime(new Timestamp(System.currentTimeMillis()));
        }
        Boolean op=false;
        if(quotationDetails.get(0).getQuId()!=null &&
                quotationDetails.get(0).getSum()!=null &&
                quotationDetails.get(0).getMao()!=null){
            Quotation quotation=quotationService.getById(quotationDetails.get(0).getQuId());
            if(quotation!=null){
                quotation.setQuTotalAmount(quotationDetails.get(0).getSum());
                quotation.setQuEstimatedGrossProfit(quotationDetails.get(0).getMao());
                op=quotationService.updateById(quotation);
                op=saveOrUpdateBatch(quotationDetails);
            }
        }
        return op;
    }
}
