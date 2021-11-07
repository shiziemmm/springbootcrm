package com.trkj.crmproject.controller;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import com.trkj.crmproject.util.MyResult;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Client> findClientName(String clientName) {
        return clientService.findClientName(clientName);
    }

    @PostMapping("/selectbyname")
    public MyResult selectByName(@RequestParam(value = "clientName",required = false) String clientName) {
            System.out.println("获取的名字：" + clientName);
            return MyResult.SUCCESS_DATA(clientService.selectByName(clientName));
    }

}

