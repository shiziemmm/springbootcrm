package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Opportunity;
import com.trkj.crmproject.service.OpportunityService;
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
@RequestMapping("/opportunity")
public class OpportunityController {
    @Autowired
    private OpportunityService opportunityService;
    @PostMapping("/addopp")
    public MyResult addOpp(@RequestBody Opportunity opportunity){
        System.out.println("数据："+opportunity);
        return MyResult.SUCCESS_Object(opportunityService.addOpp(opportunity));
    }
}

