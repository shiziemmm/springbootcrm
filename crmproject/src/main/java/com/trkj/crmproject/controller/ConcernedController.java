package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.Concerned;
import com.trkj.crmproject.service.ClientService;
import com.trkj.crmproject.service.ConcernedService;
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
@RequestMapping("/concerned")
public class ConcernedController {

    @Autowired
    ConcernedService service;
    @Autowired
    ClientService clientService;


    @GetMapping("myResult")
    public MyResult myResult(){
        return service.myResult();
    }
    @GetMapping(value = "clients")
    public List<Client> clients(){
        return clientService.findClient();
    }

    @RequestMapping(value = "addCon")
    public int  addcCon(@RequestBody Concerned concerned){
        if(concerned.getConId()==0){
            int addcon = service.addcon(concerned);
            if(addcon>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            int upacon = service.upacon(concerned);
            if(upacon>0){
                return 1;
            }else{
                return 0;
            }
        }

    }
    @GetMapping(value = "myResult1")
    public MyResult myResult1(String value){
    return service.state1(value);
    }

    @GetMapping(value = "/deleteId")
    public int delete(Integer plaId){
//        System.err.println(plaId);
        return service.delete(plaId);
    }
    @GetMapping(value = "/likeName")
    public MyResult likeName(String name){
        return service.result(name);
    }
}

