package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.ClientMapper;
import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.OrderFromDetail;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import com.trkj.crmproject.service.OrderFromService;
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
@Transactional
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
       int insert = 0;
       if(orderFrom.getOdrId() == 0){
           orderFrom.setOdrShipmentsState("未发货");
           insert = orderFromMapper.insert(orderFrom);
       }else{
           insert = orderFromMapper.updateById(orderFrom);
       }
       if(insert > 0){
           return true;
       }
       return false;
   }

    /**
     * 多条件查询订单
     */
   public List<OrderFrom> selectOrderByTj(SelectWhere selectWhere){
        return orderFromMapper.selectOrderByTj(selectWhere);
    }

    /**
     * 根据订单号查询订单数据
     * @param odrOn
     * @return
     */
    @Override
    public OrderFrom selectOrderByOdrId(String odrOn) {
        return orderFromMapper.selectOrderByOdrId(odrOn);
    }
}
