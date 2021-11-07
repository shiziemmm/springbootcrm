package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Quotation;
import com.trkj.crmproject.dao.QuotationMapper;
import com.trkj.crmproject.service.QuotationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
public class QuotationServiceImpl extends ServiceImpl<QuotationMapper, Quotation> implements QuotationService {

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
}
