package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.OrderFromService;
import com.trkj.crmproject.service.ReturnedService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/returned")
public class ReturnedController {
    @Autowired
    ReturnedService service;

    @Autowired
    OrderFromService order;

    /**
     * 查询计划回款单
     * @return
     */
    @PostMapping(value = "/returnList")
    public MyResult returnList(){
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
       return service.state(value);
    }

    /**
     * 查询客户
     * @return
     */
    @GetMapping(value = "/principal")
    public List<Emp> emp(){
        return  service.emp();
    }

//    /**
//     * 查询订单
//     * @return
//     */
    @GetMapping(value = "/oder")
    public List<OrderFrom> order(){
        return service.select();
    }

    /**
     * 新增/修改计划回款信息
     * @param returned
     * @return
     */
    @PostMapping(value = "/insertReturn")
    public int insert(@RequestBody Returned returned){
        if(returned.getRetId()==0){
            int insert = service.insert(returned);
            if(insert>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            int upa = service.upa(returned);
            if(upa>0){
                return 1;
            }else{
                return 0;
            }
        }
    }
    @GetMapping(value = "/deleteId")
    public int delete(Integer reId){
      return service.delete(reId);
    }

    /**
     * 模糊查询
     */
    @GetMapping(value = "likeName")
    public MyResult myResults(String name){
        MyResult myResult = service.myResult(name);
        System.err.println(myResult);
        return myResult;
    }

}

