package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Complaint;
import com.trkj.crmproject.service.ComplaintService;
import com.trkj.crmproject.util.MyResult;
import jdk.nashorn.internal.runtime.logging.Logger;
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
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @GetMapping(value = "myResult")
    public MyResult myResult(){
        return complaintService.myResult();
    }
    @PostMapping (value = "add")
    public int add(@RequestBody Complaint complaint){
        if(complaint.getComId()==0){
            return complaintService.add(complaint);
        }else{
            return complaintService.upa(complaint);
        }

    }
    @GetMapping(value = "myResults")
    public MyResult myResult1(String value){
            return complaintService.state1(value);
    }

    @GetMapping(value = "shanChu")
    public int shanChu(Integer plaId){
            return complaintService.shanchu(plaId);
    }
    @GetMapping(value = "/likeName")
    public MyResult likeName(String name){
        return complaintService.myResults(name);
    }

}

