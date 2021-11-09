package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.controller.WarehouseInventoryController;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.dao.WarehouseInventoryDao;
import com.trkj.crmproject.dao.WarehouseLeaveGoodsDao;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.WarehouseInventory;
import com.trkj.crmproject.entity.WarehouseLeave;
import com.trkj.crmproject.dao.WarehouseLeaveDao;
import com.trkj.crmproject.entity.WarehouseLeaveGoods;
import com.trkj.crmproject.service.WarehouseInventoryService;
import com.trkj.crmproject.service.WarehouseLeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@Service
public class WarehouseLeaveServiceImpl extends ServiceImpl<WarehouseLeaveDao, WarehouseLeave> implements WarehouseLeaveService {
    @Resource
    private WarehouseLeaveDao warehouseLeaveDao;
    @Resource
    private WarehouseLeaveGoodsDao leaveGoodsDao;
    @Resource
    private OrderFromMapper orderFromMapper;
    @Resource
    private WarehouseInventoryService inventoryService;
    @Resource
    private WarehouseInventoryDao inventoryDao;
    @Override
    public IPage<Map> findList(SearchListVo vo) {
        if (vo.getPageSize()==null){
            vo.setPageSize(10);
        }
        if (vo.getPageNum()==null){
            vo.setPageNum(1);
        }
        Page<Map> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<Map> wrapper=new QueryWrapper<>();
        if (vo.getKeyword()!=null){
            wrapper.like("e.wl_theme",vo.getKeyword())
                   .or().like("t.wa_name",vo.getKeyword())
                    .or().like("l.customer_name",vo.getKeyword());
        }
        //wrapper.eq("wl_timeliness",1);
        return warehouseLeaveDao.selectList(page,wrapper);
    }

    @Override
    public WarehouseLeave findByIde(int id) {
        return warehouseLeaveDao.findByIde(id);
    }

    @Override
    public Boolean updateAll(int id) {
        WarehouseLeave warehouseLeave = warehouseLeaveDao.selectById(id);
        QueryWrapper<WarehouseLeaveGoods> wrapper=new QueryWrapper<>();
        wrapper.eq("wlg_wl_id",id);
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setOdrId(warehouseLeave.getWlInId());
        orderFrom.setOdrShipmentsState("出库中");
        orderFromMapper.updateById(orderFrom);
        leaveGoodsDao.delete(wrapper);
        warehouseLeaveDao.deleteById(id);
        return true;
    }

    @Override
    public Boolean update(WarehouseLeave warehouseLeave) {
        for (int i=0;i<warehouseLeave.getWarehouseLeaveGoods().size();i++){
            QueryWrapper<WarehouseInventory> wrapper=new QueryWrapper<>();
            wrapper.eq("wi_pr_id",warehouseLeave.getWarehouseLeaveGoods().get(i).getWlgPrId())
                    .ge("wi_count",warehouseLeave.getWarehouseLeaveGoods().get(i).getWlgCount());
            int in = inventoryDao.selectCount(wrapper);
            if (in==0){
                return false;
            }
        }
        QueryWrapper<WarehouseLeaveGoods> wrapper=new QueryWrapper<>();
        wrapper.eq("wlg_wl_id",warehouseLeave.getWlId());
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setOdrId(warehouseLeave.getWlInId());
        orderFrom.setOdrShipmentsState("待发货");
        warehouseLeave.setWlState(1);
        warehouseLeave.setWlLeavetime(new Timestamp(new Date().getTime()));
        if (saveOrUpdate(warehouseLeave)){
            List<WarehouseLeaveGoods> warehouseLeaveGoods = leaveGoodsDao.selectList(wrapper);
            List<WarehouseInventory> inventories = new ArrayList<>();
            WarehouseInventory win=new WarehouseInventory();
            if( warehouseLeaveGoods!= null && warehouseLeaveGoods.size() > 0) {
                for (int i = 0; i < warehouseLeaveGoods.size(); i++) {
                    win.setWiPrId(warehouseLeaveGoods.get(i).getWlgPrId());
                    win.setWiCount(warehouseLeaveGoods.get(i).getWlgCount());
                    inventories.add(win);
                }
            }
            inventoryService.updateAll(inventories);
            orderFromMapper.updateById(orderFrom);
            return true;
        }
        return false;
    }
}
