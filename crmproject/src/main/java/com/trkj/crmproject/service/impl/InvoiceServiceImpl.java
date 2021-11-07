package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Invoice;
import com.trkj.crmproject.dao.InvoiceMapper;
import com.trkj.crmproject.service.InvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements InvoiceService {
    @Autowired
    InvoiceMapper invoiceMapper;


    public MyResult select(){
        return MyResult.SUCCESS_DATA(invoiceMapper.selectList(null));
    }

}
