package com.trkj.crmproject.controller;


import com.alibaba.fastjson.JSON;
import com.trkj.crmproject.entity.ShipmentsFrom;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import com.trkj.crmproject.service.ShipmentsFromService;
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
@RequestMapping("/shipmentsFrom")
public class ShipmentsFromController {
    @Autowired
    ShipmentsFromService shipmentsFromService;

    /**
     * 新增发货单
     */
    @RequestMapping("addShipment")
    public boolean addShipment(@RequestBody ShipmentsFrom shipments){
        System.err.println(shipments);
        shipmentsFromService.addShipment(shipments);
        return false;
    }

    /**
     * 条件查询发货单
     */
    @RequestMapping("selectWhereShipment")
    public MyResult selectWhereShipment(@RequestBody SelectWhere selectWhere){

        return MyResult.SUCCESS_DATA(shipmentsFromService.selectWhereShipment(selectWhere));
    }

    /**
     * 修改发货订单状态
     */
    @RequestMapping("updateShipmentState")
    public boolean updateShipmentState(@RequestBody String str){
        Map map = JSON.parseObject(str, Map.class);
        Integer odrId = Integer.parseInt(map.get("odrId").toString());
        Integer sptId = Integer.parseInt(map.get("sptId").toString());

        System.out.println(odrId+"================="+sptId);
       return shipmentsFromService.updateShipmentState(odrId,sptId);
    }

}

