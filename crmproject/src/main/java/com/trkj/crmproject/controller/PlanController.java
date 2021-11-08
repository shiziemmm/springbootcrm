package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.Plan;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.OrderFromService;
import com.trkj.crmproject.service.PlanService;
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
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanService planService;
    @Autowired
    OrderFromService order;

    /**
     * 查询回款信息
     * @return
     */
    @GetMapping(value = "/planList")
    public MyResult planList(){
        return planService.planlist();
    }

    /**
     * 新增/修改回款信息
     * @param plan
     * @return
     */
    @PostMapping(value = "/addPlan")
    public int addPlan(@RequestBody Plan plan){
        System.err.println(plan);
        if(plan.getPlaId()==0 || plan.getPlaId()==null){
            int insert = planService.addplan(plan);
           if(plan.getPlaWhether().equals("已回")){
               order.updateprice(plan.getPlaPrice(),plan.getOdrId());
           }
            if(insert>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            int upd = planService.upd(plan);
            if(upd>0){
                return 1;
            }else{
                return 0;
            }
        }

    }

    /**
     * 删除回款信息
     * @param plaId
     * @return
     */
    @GetMapping(value = "deletId")
    public void delete(Integer plaId,String odrId,Double plaPrice){
        planService.delet(plaId,odrId,plaPrice);
    }

    /**
     * 条件查询回款信息
     * @param value
     * @return
     */
    @GetMapping(value = "state")
    public MyResult state(String value){
        System.err.println(value);
        System.err.println(planService.state(value));
        return planService.state(value);
    }


    @GetMapping("/likeName")
    public MyResult likeName(String name){
    return planService.myResult(name);
    }

}

