package com.trkj.crmproject.controller;


import com.trkj.crmproject.dao.EmpMapper;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.service.EmpService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpService empService;


    @RequestMapping("selectEmpAll")
    public MyResult empSelectAll(){
         return MyResult.SUCCESS_DATA(empService.selectList());
    }

}

