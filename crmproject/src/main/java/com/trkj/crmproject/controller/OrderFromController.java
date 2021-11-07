package com.trkj.crmproject.controller;


import com.alibaba.fastjson.JSON;
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.service.OrderFromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/orderFrom")
public class OrderFromController {

    @Autowired
    OrderFromService orderFromService;//订单service接口


    /**
     * 根据客户名称查询信息
     * @param name
     * @return
     */
    @RequestMapping("selectByCliName")
    public List<Client> selectByCliName(String name){
        System.out.println(name);
        return orderFromService.selectByName(name);
    }

    /**
     * 新增订单
     */
    @RequestMapping("addOrder")
    public boolean addOrder(@RequestBody String str){
        System.err.println(str);
        OrderFrom orderFrom = JSON.parseObject(str, OrderFrom.class);
        System.err.println(orderFrom);
        return orderFromService.addOrder(orderFrom);
    }
}

