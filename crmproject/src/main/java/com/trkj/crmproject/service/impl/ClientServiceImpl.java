package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.dao.ClientMapper;
import com.trkj.crmproject.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ClientMapper clientMapper;
    @Override
    public List<Client> selectByName(String clientName) {
        return clientMapper.selectByName(clientName);
    }
}
