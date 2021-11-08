package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.trkj.crmproject.entity.Invoice;
import com.trkj.crmproject.dao.InvoiceMapper;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.InvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements InvoiceService {
    @Autowired
    InvoiceMapper invoiceMapper;

    /**
     * 查询
     * @return
     */
    public MyResult select(){
        return MyResult.SUCCESS_DATA(invoiceMapper.selectList(null));
    }

    /**
     * 新增开票单
     * @param invoice
     * @return
     */
    public int addinvo(Invoice invoice){
        return invoiceMapper.insert(invoice);
    }

    /**
     * 修改开票单
     * @param invoice
     * @return
     */
    public int edit(Invoice invoice){
        return invoiceMapper.updateById(invoice);
    }

    /**
     * 删除开票单
     * @param invId
     * @return
     */
    public int delete(Integer invId){
        return invoiceMapper.deleteById(invId);
    }



    public MyResult state(String value){
        Invoice r=new Invoice();
        MyResult m=new MyResult();
            r.setInvType(value);
            QueryWrapper<Invoice> queryWrapper =
                    new QueryWrapper<Invoice>(r);
            List<Invoice> returneds = invoiceMapper.selectList(queryWrapper);
            if(returneds.size()>0){
                m.setCode(200);
                m.setMsg("查询成功");
                m.setData(returneds);
            }else{
                m.setCode(404);
                m.setData(returneds);
                m.setMsg("失败成功");

            }
            return m;

    }
    public MyResult myResult(String name){
        Invoice r=new Invoice();
        r.setPrincipal(name);
        QueryWrapper<Invoice> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(r.getPrincipal()),"principal",r.getPrincipal());
        return MyResult.SUCCESS_DATA(invoiceMapper.selectList(queryWrapper));
    }

}
