package com.trkj.crmproject.controller;


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
//@RequestMapping("/orderFrom")
public class OrderFromController {

    @RequestMapping("select")
    public String selectAll(){
        return "成功";
    }

}

