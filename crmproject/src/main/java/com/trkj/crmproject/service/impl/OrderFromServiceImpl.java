package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.ClientMapper;
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.service.OrderFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OrderFromServiceImpl extends ServiceImpl<OrderFromMapper, OrderFrom> implements OrderFromService {

    @Autowired
    ClientMapper clientMapper;//客户mapper

    @Autowired
    OrderFromMapper orderFromMapper;//订单mapper


    /**
     * 根据客户名称查询所有的客户
     */
    public List<Client> selectByName(String name){
        QueryWrapper<Client> qw = new QueryWrapper<Client>().like("client_name",name);
        return clientMapper.selectList(qw);
    }


    /**
     * 新增订单
     */
   public boolean addOrder(OrderFrom orderFrom){
       int insert = orderFromMapper.insert(orderFrom);
       if(insert > 0){
           return true;
       }
       return false;
   }

}
