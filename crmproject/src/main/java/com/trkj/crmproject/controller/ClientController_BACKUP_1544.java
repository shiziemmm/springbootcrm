package com.trkj.crmproject.controller;


<<<<<<< HEAD
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
=======
import com.trkj.crmproject.service.ClientService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> c5a4e1c0a514cb89b421cac0a10a7d6970471dd3

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
<<<<<<< HEAD
public class ClientController {
    @Resource
    ClientService clientService;

    @RequestMapping("/find_client")
    public List<Client> findClient(){
        return clientService.findClient();
    }

    @RequestMapping("/find_client_period")
    public List<Client> findClientPeriod(String clientPeriod){
        return clientService.findClientPeriod(clientPeriod);
    }

    @RequestMapping("/find_client_name")
    public List<Client> findClientName(String clientName){
        return clientService.findClientName(clientName);
=======
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/selectbyname")
    public MyResult selectByName(@RequestParam(value = "clientName",required = false) String clientName){
        System.out.println("获取的名字："+clientName);
        return MyResult.SUCCESS_DATA(clientService.selectByName(clientName));
>>>>>>> c5a4e1c0a514cb89b421cac0a10a7d6970471dd3
    }
}

