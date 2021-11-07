package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.CustomerTransfer;
import com.trkj.crmproject.dao.CustomerTransferMapper;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.service.CustomerTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
public class CustomerTransferServiceImpl extends ServiceImpl<CustomerTransferMapper, CustomerTransfer> implements CustomerTransferService {
    @Resource
    CustomerTransferMapper customerTransferMapper;

    public List<CustomerTransfer> findByClient(){
        return customerTransferMapper.findByClient();
    }

    public List<Emp> findByEmpName(){
        return customerTransferMapper.findByEmpName();
    }

    public void updateOld(Client client){

        CustomerTransfer customerTransfer = new CustomerTransfer();
        customerTransfer.setClientId(client.getClientId());
        customerTransfer.setCustomerTransferOld(client.getClientTransfer());
        customerTransfer.setCustomerTransferNew(client.getEmp().getEmpName());
        customerTransfer.setCustomerTransferRemark(client.getClientRemark());
        customerTransfer.setCustomerTransferHandlers(client.getClientTransfer());
        customerTransfer.setCustomerTransferTime(new Timestamp(new Date().getTime()));
        customerTransferMapper.addCustomer(customerTransfer);

        client.setClientTransfer(client.getEmp().getEmpName());
        customerTransferMapper.updateOld(client);
    }
}
