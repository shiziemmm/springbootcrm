package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ClientMapper extends BaseMapper<Client> {
    List<Client> selectByName(String clientName);

    /**
     * 查询所有数据
     * @return
     */
    public List<Client> findClient();

    public List<Client> findClientPeriod(String clientPeriod);

    public List<Client> findClientName(String clientName);

    public List<Client> selectClient(Client client);

    public void addClient(Client client);

    public void updateClient(Client client);

    public void updateClientSeas(Integer clientId);

    public void updateClientSeas1(Integer clientId);
}
