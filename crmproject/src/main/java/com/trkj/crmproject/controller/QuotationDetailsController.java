package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.QuotationDetails;
import com.trkj.crmproject.service.QuotationDetailsService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/quotationDetails")
public class QuotationDetailsController {
    @Autowired
    private QuotationDetailsService service;
    @PostMapping("/adddetails")
    public MyResult addDetails(@RequestBody List<QuotationDetails> quotationDetails){
        return MyResult.SUCCESS_Object(service.addDetails(quotationDetails));
    }
    @PostMapping("/selectall")
    public Page addDetails(@RequestBody QuotationDetails quotationDetails){
        return service.selectAll(new Page<>(quotationDetails.getPageNo(),quotationDetails.getPageSize()),quotationDetails);
    }
}

