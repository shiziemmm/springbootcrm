package com.trkj.crmproject.controller;


import com.alibaba.fastjson.JSON;
import com.trkj.crmproject.entity.OrderFromDetail;
import com.trkj.crmproject.service.OrderFromDetailService;
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
@RequestMapping("/orderFromDetail")
public class OrderFromDetailController {
    @Autowired
    OrderFromDetailService orderFromDetailService;//订单详情service

    /**
     * 批量添加订单详情
     */
    @RequestMapping("addBatchOrderDetails")
    public boolean addOrderDetails(@RequestBody String str){
        List<OrderFromDetail> orderFromDetails = JSON.parseArray(str, OrderFromDetail.class);
        System.out.println(orderFromDetails);
        return orderFromDetailService.addOrderDetails(orderFromDetails);
    }

    /**
     * 根据订单号查询所有订单详情
     */

//    public MyResult selectByIdAll(){
//
//    }
}

