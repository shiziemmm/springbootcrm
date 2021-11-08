package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.CustomerTransfer;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.service.CustomerTransferService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
public class CustomerTransferController {
    @Resource
    CustomerTransferService customerTransferService;

    @RequestMapping("/find_by_client")
    public List<CustomerTransfer> findByClient(){
        return customerTransferService.findByClient();
    }

    @RequestMapping("/find_by_client_name")
    public List<CustomerTransfer> findByClientName(String clientName){
        return customerTransferService.findByClientName(clientName);
    }

    @RequestMapping("/find_by_empName")
    public List<Emp> findByEmpName(){
        return customerTransferService.findByEmpName();
    }

    @RequestMapping("/find_old")
    public List<CustomerTransfer> findOld(String customerTransferOld){
        return customerTransferService.findOld(customerTransferOld);
    }

    @RequestMapping("/find_new")
    public List<CustomerTransfer> findNew(String customerTransferNew){
        return customerTransferService.findNew(customerTransferNew);
    }

    @RequestMapping("/update_old")
    public void updateOld(@RequestBody Client client){
        customerTransferService.updateOld(client);
    }
}

