package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.Plan;
import com.trkj.crmproject.entity.Returned;
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
    OrderFromMapper orderFromMapper;

    @GetMapping(value = "/planlist")
    public MyResult planlist(){
        return planService.planlist();
    }

    @PostMapping(value = "/addplan")
    public int addplan(@RequestBody Plan plan){
        System.err.println(plan);
        if(plan.getPlaId()==0 || plan.getPlaId()==null){
            int insert = planService.addplan(plan);
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
    @GetMapping(value = "deletid")
    public int delete(Integer plaId){
        return planService.delet(plaId);
    }
    @GetMapping(value = "state")
    public MyResult state(String value){
        System.err.println(value);
        System.err.println(planService.state(value));
        return planService.state(value);
    }

//    //修改订单表回款信息
//
//    @GetMapping("/upaodrpirce")
//    public void upaodrpirce(@RequestBody Plan plan){
//
//    }

}

