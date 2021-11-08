package com.trkj.crmproject.controller;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.service.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import com.trkj.crmproject.util.MyResult;

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
        System.out.println(clientPeriod);
        return clientService.findClientPeriod(clientPeriod);
    }

    @RequestMapping("/find_client_name")
    public List<Client> findClientName(String clientName) {
        return clientService.findClientName(clientName);
    }

    @PostMapping("/select_client")
    public List<Client> selectClient(@RequestBody Client client){
        System.out.println(client);
        return clientService.selectClient(client);
    }

    @PostMapping("/save_client")
    public void saveClient(@RequestBody Client client){
        clientService.saveClient(client);
    }

    @RequestMapping("/update_client_seas")
    public void updateClientSeas(Integer clientId) {
        clientService.updateClientSeas(clientId);
    }

    @RequestMapping("/update_client_seas1")
    public void updateClientSeas1(Integer clientId) {
        clientService.updateClientSeas1(clientId);
    }

    @PostMapping("/selectbyname")
    public MyResult selectByName(@RequestParam(value = "clientName",required = false) String clientName) {
            System.out.println("获取的名字：" + clientName);
            return MyResult.SUCCESS_DATA(clientService.selectByName(clientName));
    }

}

