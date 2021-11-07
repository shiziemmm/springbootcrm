package com.trkj.crmproject.controller;


import com.trkj.crmproject.service.ClientService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/selectbyname")
    public MyResult selectByName(@RequestParam(value = "clientName",required = false) String clientName){
        System.out.println("获取的名字："+clientName);
        return MyResult.SUCCESS_DATA(clientService.selectByName(clientName));
    }
}

