package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.QuotationDetails;
import com.trkj.crmproject.dao.QuotationDetailsMapper;
import com.trkj.crmproject.service.QuotationDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class QuotationDetailsServiceImpl extends ServiceImpl<QuotationDetailsMapper, QuotationDetails> implements QuotationDetailsService {

    @Override
    public Boolean addDetails(List<QuotationDetails> quotationDetails) {
        return saveBatch(quotationDetails);
    }
}
