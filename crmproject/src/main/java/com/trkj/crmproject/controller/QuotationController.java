package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Quotation;
import com.trkj.crmproject.service.QuotationService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}

