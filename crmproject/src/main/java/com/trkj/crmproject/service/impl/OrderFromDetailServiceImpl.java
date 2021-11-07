package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.OrderFromDetail;
import com.trkj.crmproject.dao.OrderFromDetailMapper;
import com.trkj.crmproject.service.OrderFromDetailService;
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
public class OrderFromDetailServiceImpl extends ServiceImpl<OrderFromDetailMapper, OrderFromDetail> implements OrderFromDetailService {

    @Autowired
    OrderFromMapper orderFromMapper;

    @Autowired
    OrderFromDetailMapper orderFromDetailMapper;

    /**
     * 批量新增修改订单详情
     * @param detailsList
     * @return
     */
    @Override
    public boolean addOrderDetails(List<OrderFromDetail> detailsList) {

        double price = 0;//订单总价
        int count = 0;//产品数量
        for (OrderFromDetail order : detailsList) {
            count += order.getOdrdlCount();//产品数量
            price += order.getOdrdlUnitPrice() * order.getOdrdlCount();//订单总价
        }
        System.out.println("价格"+price);
        System.out.println("数量"+count);

        //====================修改订单总价格数量
        if(!detailsList.isEmpty()){//判断订单详情集合是否为空
            QueryWrapper<OrderFromDetail> qw = new QueryWrapper<OrderFromDetail>().eq("odr_on",detailsList.get(0).getOdrOn());
            orderFromDetailMapper.delete(qw);//删除原本订单详情里面的数据
            OrderFrom orderFrom = new OrderFrom(detailsList.get(0).getOdrId(),price,count);
            QueryWrapper<OrderFrom> orderQW = new QueryWrapper<OrderFrom>().eq("odr_on",detailsList.get(0).getOdrOn());
            orderFromMapper.update(orderFrom,orderQW);//添加订单详情数据
        }


       return saveBatch(detailsList);
    }
}
