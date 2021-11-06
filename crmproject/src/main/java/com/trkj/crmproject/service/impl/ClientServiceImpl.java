package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.dao.ClientMapper;
import com.trkj.crmproject.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}
