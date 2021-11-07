package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ClientService extends IService<Client> {

    public List<Client> findClient();

    public List<Client> findClientPeriod(String clientPeriod);

    public List<Client> findClientName(String clientName);

    public List<Client> selectClient(Client client);

    public void saveClient(Client client);

    public void updateClientSeas(Integer clientId,String clientSeas);
}
