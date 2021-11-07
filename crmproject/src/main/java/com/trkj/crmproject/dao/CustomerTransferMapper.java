package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.CustomerTransfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trkj.crmproject.entity.Emp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface CustomerTransferMapper extends BaseMapper<CustomerTransfer> {

    public List<CustomerTransfer> findByClient();

    public List<Emp> findByEmpName();

    public void updateOld(Client client);

    public void addCustomer(CustomerTransfer customerTransfer);
}
