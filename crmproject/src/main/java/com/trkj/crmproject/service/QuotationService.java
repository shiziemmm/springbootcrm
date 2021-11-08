package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Quotation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface QuotationService extends IService<Quotation> {
    Integer addQuotation(Quotation quotation);
    Page<Quotation> selectAll(Page<Quotation> page,Quotation quotation);
    Boolean del(Integer quid);
    Quotation selectById(Integer quid);
}
