package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.dao.ClientMapper;
import com.trkj.crmproject.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
    @Resource
    ClientMapper clientMapper;


    public List<Client> findClient(){
        return clientMapper.findClient();
    }

    public List<Client> findClientPeriod(String clientPeriod){
        return clientMapper.findClientPeriod(clientPeriod);
    }

    public List<Client> findClientName(String clientName){
        return clientMapper.findClientName(clientName);
    }

    public List<Client> selectClient(Client client){
        return clientMapper.selectClient(client);
    }

    public void saveClient(Client client){
        if (client.getClientId() == null || client.getClientId().equals("")){
            client.setClientTime(new Timestamp(new Date().getTime()));
            client.setClientSeas(0);
            client.setClientTransfer("nmsl");
            System.out.println(client);
            clientMapper.addClient(client);
        }else{
            clientMapper.updateClient(client);
        }
    }


    public void updateClientSeas(Integer clientId,String clientSeas) {
        clientMapper.updateClientSeas(clientId, clientSeas);

    }

    @Override
    public List<Client> selectByName(String clientName) {
        return clientMapper.selectByName(clientName);
    }
}
