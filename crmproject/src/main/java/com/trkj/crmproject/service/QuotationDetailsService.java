package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.QuotationDetails;
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
public interface QuotationDetailsService extends IService<QuotationDetails> {
    Boolean addDetails(List<QuotationDetails> quotationDetails);
}
