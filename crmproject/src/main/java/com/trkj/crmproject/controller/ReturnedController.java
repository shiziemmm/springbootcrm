package com.trkj.crmproject.controller;


import com.trkj.crmproject.service.ReturnedService;
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
@RequestMapping("/returned")
public class ReturnedController {
    @Autowired
    ReturnedService service;

    /**
     * 查询计划回款单
     * @return
     */
    @PostMapping(value = "/returnlist")
    public MyResult returnlist(){
//        System.err.println(service.returneds());
        return service.returneds();
    }

    /**
     * 根据下拉框的值查询计划回款单
     * @param value
     * @return
     */
    @GetMapping(value = "/state")
    public MyResult state(String value){
        System.err.println(value);
        System.err.println(service.state(value));
       return service.state(value);
    }

}

