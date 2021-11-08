package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Opportunity;
import com.trkj.crmproject.entity.Vo.AddOdrVO;
import com.trkj.crmproject.service.OpportunityService;
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
@RequestMapping("/opportunity")
public class OpportunityController {
    @Autowired
    private OpportunityService opportunityService;
    @PostMapping("/addopp")
    public MyResult addOpp(@RequestBody Opportunity opportunity){
        System.out.println("数据："+opportunity);
        return MyResult.SUCCESS_Object(opportunityService.addOpp(opportunity));
    }
    @PostMapping("/selectlist")
    public Page selectList(@RequestBody Opportunity opportunity){
        System.out.println("selectlist数据："+opportunity);
        return opportunityService.selectList(opportunity,new Page<>(opportunity.getPageNo(),opportunity.getPageSize()));
    }
    @GetMapping ("/selectbyid")
    public MyResult selectById(@RequestParam("opid") Integer opid){
        return MyResult.SUCCESS_Object(opportunityService.selectById(opid));
    }
    @PostMapping("/addodr")
    public MyResult addOdr(@RequestBody AddOdrVO addOdrVO){
        return MyResult.SUCCESS_Object(opportunityService.addOdr(addOdrVO));
    }
    @GetMapping("/delete")
    public MyResult delete(@RequestParam("opid") Integer opid){
        return MyResult.SUCCESS_Object(opportunityService.delete(opid));
    }
    @GetMapping("/selectall")
    public MyResult selectall(){
        return MyResult.SUCCESS_DATA(opportunityService.selectAll());
    }
}

