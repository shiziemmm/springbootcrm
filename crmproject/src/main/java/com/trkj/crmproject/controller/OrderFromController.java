package com.trkj.crmproject.controller;


import com.alibaba.fastjson.JSON;
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import com.trkj.crmproject.service.OrderFromService;
import com.trkj.crmproject.util.MyResult;
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
    public MyResult selectByCliName(String name){
        System.out.println(name);
        return MyResult.SUCCESS_DATA(orderFromService.selectByName(name));
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


    /**
     * 多条件查询订单
     */
    @RequestMapping("selectOrderByTj")
    public MyResult selectOrderByTj(@RequestBody SelectWhere selectWhere){
        System.err.println(selectWhere);
        return MyResult.SUCCESS_DATA(orderFromService.selectOrderByTj(selectWhere));
    }

    /**
     * 根据订单编号查询订单
     */
    @RequestMapping("selectOrderByOdrId")
    public MyResult selectOrderByOdrId(String odrOn){
        return MyResult.SUCCESS_Object(orderFromService.selectOrderByOdrId(odrOn));
    }
}

