package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.OrderFromDetailMapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.dao.ShipmentsFromDetailMapper;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.OrderFromDetail;
import com.trkj.crmproject.entity.ShipmentsFrom;
import com.trkj.crmproject.dao.ShipmentsFromMapper;
import com.trkj.crmproject.entity.ShipmentsFromDetail;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import com.trkj.crmproject.service.ShipmentsFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
public class ShipmentsFromServiceImpl extends ServiceImpl<ShipmentsFromMapper, ShipmentsFrom> implements ShipmentsFromService {

    @Autowired
    ShipmentsFromMapper shipmentsFromMapper;//发货mapper

    @Autowired
    OrderFromDetailMapper orderFromDetailMapper;//订单详情mapper

    @Autowired
    ShipmentsFromDetailMapper shipmentsFromDetailMapper;

    @Autowired
    OrderFromMapper orderFromMapper;

    /**
     * 新增发货单
     * @return
     */
    @Override
    public boolean addShipment(ShipmentsFrom shipments) {
        //================新增发货单
        shipments.setSptDate(new Timestamp(new Date().getTime()));//当前时间
        shipments.setSptState("运输中");//发货状态
        shipmentsFromMapper.insert(shipments);

        //===============根据订单号查询订单详情
        QueryWrapper<OrderFromDetail> qw = new QueryWrapper<OrderFromDetail>().eq("odr_on",shipments.getOdrOn());
        List<OrderFromDetail> orderFromDetails = orderFromDetailMapper.selectList(qw);
        int insert = 0;
        for (OrderFromDetail order : orderFromDetails) {
            ShipmentsFromDetail spd = new ShipmentsFromDetail();
            spd.setSptOn(shipments.getSptOn());//发货单号
            spd.setMoney(order.getOdrdlMoney());//进价
            spd.setPrId(order.getPrId());//产品编号
            spd.setQuantity(order.getOdrdlCount());//产品数量
            spd.setProductName(order.getOdrdlProductName());//产品名称
            spd.setUnitPrice(order.getOdrdlUnitPrice());//售价
             insert =+ shipmentsFromDetailMapper.insert(spd);//新增
        }

        //===============将订单表里面的状态修改为已发货
        QueryWrapper<OrderFrom> qwo = new QueryWrapper<OrderFrom>().eq("odr_on",shipments.getOdrOn());//根据订单号修改订单状态
        OrderFrom orderFromS = orderFromMapper.selectOne(qwo);
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setOdrShipmentsState("已发货");
        orderFrom.setOdrPrice(orderFromS.getOdrPrice() + shipments.getSptSfPrice());//将运费累加到订单总金额里面
        orderFrom.setOdrFreight(shipments.getSptSfPrice());//运费
        orderFromMapper.update(orderFrom,qwo);

        if (insert > 0)
            return true;
        return false;
    }


    /**
     * 条件查询所有发货订单
     */
    public List<ShipmentsFrom> selectWhereShipment(SelectWhere selectWhere){
      return   shipmentsFromMapper.selectShipmentByWhere(selectWhere);
    }

    /**
     * 修改发货订单状态和订单状态
     */
    public boolean updateShipmentState(Integer odrId,Integer sptId){
        //====== 根据订单编号修改订单状态
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setOdrId(odrId);
        orderFrom.setOdrShipmentsState("已签收");
        orderFromMapper.updateById(orderFrom);//修改订单状态

        //==========根据发货订单修改发货订单状态为已签收
        ShipmentsFrom shipmentsFrom = new ShipmentsFrom();
        shipmentsFrom.setSptId(sptId);
        shipmentsFrom.setSptState("已签收");
        shipmentsFrom.setSptSignDate(new Timestamp(new Date().getTime()));
        int i = shipmentsFromMapper.updateById(shipmentsFrom);

        if (i > 0){
            return true;
        }
        return false;
    }

}
