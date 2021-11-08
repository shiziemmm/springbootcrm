package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Quotation;
import com.trkj.crmproject.service.QuotationService;
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
@RequestMapping("/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;
    @PostMapping("/addquotation")
    public MyResult addQuotation(@RequestBody Quotation quotation){
        return MyResult.SUCCESS_Object(quotationService.addQuotation(quotation));
    }
    @PostMapping("/selectall")
    public Page selectAll(@RequestBody Quotation quotation){
        System.out.println("前台数据：quotation:"+quotation);
        return quotationService.selectAll(new Page<>(quotation.getPageNo(),quotation.getPageSize()),quotation);
    }
    @GetMapping("/del")
    public MyResult del(@RequestParam(value = "quid",required = false) Integer quid){
        System.out.println("前台数据：quid:"+quid);
        return MyResult.SUCCESS_Object(quotationService.del(quid));
    }
    @GetMapping("/selectbyid")
    public MyResult selectById(@RequestParam(value = "quid",required = false) Integer quid){
        System.out.println("前台数据：quid:"+quid);
        return MyResult.SUCCESS_Object(quotationService.selectById(quid));
    }

}

