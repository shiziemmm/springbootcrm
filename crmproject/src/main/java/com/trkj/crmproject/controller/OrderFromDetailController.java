package com.trkj.crmproject.controller;


import com.alibaba.fastjson.JSON;
import com.trkj.crmproject.entity.OrderFromDetail;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import com.trkj.crmproject.service.OrderFromDetailService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        System.err.println(str);
        Map map = JSON.parseObject(str, Map.class);
        List<OrderFromDetail> orderFromDetails = JSON.parseArray(map.get("orderDetails").toString(), OrderFromDetail.class);
        Integer odrId = JSON.parseObject(map.get("odrId").toString(),Integer.class);
        System.out.println(orderFromDetails);
        return orderFromDetailService.addOrderDetails(orderFromDetails,map.get("odrOn").toString(),odrId);
    }

    /**
     * 添加出库单
     */
    @RequestMapping("addwWrehouseLeave")
    public boolean addwWrehouseLeave(@RequestBody String str){
        Map map = JSON.parseObject(str, Map.class);
        List<OrderFromDetail> orderFromDetails = JSON.parseArray(map.get("orderFromDetail").toString(),OrderFromDetail.class);
        Integer odrId = JSON.parseObject(map.get("odrId").toString(),Integer.class);

        orderFromDetailService.addWrehouseLeave(orderFromDetails,odrId);
        return false;
    }

    /**
     * 根据条件查询所有订单详情
     */
    @RequestMapping("selectOrderDetailsWhere")
    public MyResult selectOrderDetailsWhere(@RequestBody SelectWhere selectWhere){
        System.out.println(selectWhere);
       return MyResult.SUCCESS_DATA(orderFromDetailService.selectOrderDetailsWhere(selectWhere));
    }
}

