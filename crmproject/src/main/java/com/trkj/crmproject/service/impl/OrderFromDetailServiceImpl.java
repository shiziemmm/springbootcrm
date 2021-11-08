package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.dao.WarehouseLeaveDao;
import com.trkj.crmproject.dao.WarehouseLeaveGoodsDao;
import com.trkj.crmproject.entity.*;
import com.trkj.crmproject.dao.OrderFromDetailMapper;
import com.trkj.crmproject.entity.Vo.SelectWhere;
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

    @Autowired
    WarehouseLeaveDao warehouseLeaveDao;

    @Autowired
    WarehouseLeaveGoodsDao warehouseLeaveGoodsDao;

    /**
     * 批量新增修改订单详情
     * @param detailsList
     * @return
     */
    @Override
    public boolean addOrderDetails(List<OrderFromDetail> detailsList,String odrOn,Integer odrId) {

        double price = 0;//订单总价
        int count = 0;//产品数量
        for (OrderFromDetail order : detailsList) {
            count += order.getOdrdlCount();//产品数量
            price += order.getOdrdlUnitPrice() * order.getOdrdlCount();//订单总价
        }
        System.out.println("价格"+price);
        System.out.println("数量"+count);

        //====================修改订单总价格数量
            QueryWrapper<OrderFromDetail> qw = new QueryWrapper<OrderFromDetail>().eq("odr_on",odrOn);
            orderFromDetailMapper.delete(qw);//删除原本订单详情里面的数据
            OrderFrom orderFrom = new OrderFrom(odrId,price,count);
            orderFromMapper.updateById(orderFrom);//添加订单详情数据


       return saveBatch(detailsList);
    }


    /**
     * 添加出库单
     */
    @Override
    public boolean addWrehouseLeave(List<OrderFromDetail> list, Integer odrId) {

        //================新增出库主表
        WarehouseLeave warehouseLeave = new WarehouseLeave();
        warehouseLeave.setWlInId(odrId);
        warehouseLeave.setWlState(0);
        warehouseLeave.setWlTheme("订单出库");
        warehouseLeave.setWlWaId(1);
        warehouseLeaveDao.insert(warehouseLeave);

        //==============新增出库详表
        for (OrderFromDetail orderFromDetail : list) {
            WarehouseLeaveGoods warehouseEnterGoods = new WarehouseLeaveGoods();
            warehouseEnterGoods.setWlgCount(orderFromDetail.getOdrdlCount());
            warehouseEnterGoods.setWlgPrId(orderFromDetail.getPrId());
            warehouseEnterGoods.setWlgWlId(warehouseLeave.getWlId());
            warehouseEnterGoods.setWlgPrname(orderFromDetail.getOdrdlProductName());
            warehouseLeaveGoodsDao.insert(warehouseEnterGoods);
        }


        //====================修改订单发货状态
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setOdrId(odrId);
        orderFrom.setOdrShipmentsState("出库中");
        orderFromMapper.updateById(orderFrom);//修改
        return false;
    }

    @Override
    public List<OrderFromDetail> selectOrderDetailsWhere(SelectWhere selectWhere) {
        return orderFromDetailMapper.selectOrderDetailsWhere(selectWhere);
    }
}
