package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Demand;
import com.trkj.crmproject.service.DemandService;
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
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private DemandService demandService;
    @PostMapping("/adddemand")
    public MyResult addDemand(@RequestBody Demand demand){
        return MyResult.SUCCESS_Object(demandService.addDemand(demand));
    }
    @GetMapping("/del")
    public MyResult del(@RequestParam(value = "deid" ,required = false) Integer deid){
        System.out.println("传过来的id"+deid);
        return MyResult.SUCCESS_Object(demandService.del(deid));
    }
}

