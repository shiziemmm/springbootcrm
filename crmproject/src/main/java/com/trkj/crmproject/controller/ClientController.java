package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    }
}

