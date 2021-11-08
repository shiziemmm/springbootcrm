package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.CustomerTransfer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.entity.Emp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface CustomerTransferService extends IService<CustomerTransfer> {

    public List<CustomerTransfer> findByClient();

    public List<CustomerTransfer> findByClientName(String clientName);

    public List<Emp> findByEmpName();

    public List<CustomerTransfer> findOld(String customerTransferOld);

    public List<CustomerTransfer> findNew(String customerTransferNew);

    public void updateOld(Client client);
}
