package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Invoice;
import com.trkj.crmproject.service.InvoiceService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("select")
    public MyResult select(){
        return invoiceService.select();
    }

    @PostMapping(value = "addInvo")
    public int addInvo(@RequestBody Invoice invoice){
        if(invoice.getInvId()==0){
            int insert = invoiceService.addinvo(invoice);
            if(insert>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            int edit = invoiceService.edit(invoice);
            if(edit>0){
                return 1;
            }else{
                return 0;
            }
        }

    }
    @GetMapping(value = "/deleteId")
    public int delete(Integer plaId){
        return invoiceService.delete(plaId);
    }


    @GetMapping(value = "selectType")
    public MyResult selectType(String value){
        return invoiceService.state(value);
    }
    @GetMapping(value = "/likeName")
    public MyResult likeName(String name){
        return invoiceService.myResult(name);
    }

}

