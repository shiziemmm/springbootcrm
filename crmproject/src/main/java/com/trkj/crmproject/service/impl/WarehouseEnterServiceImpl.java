package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.entity.WarehouseEnter;
import com.trkj.crmproject.dao.WarehouseEnterDao;
import com.trkj.crmproject.entity.WarehouseEnterGoods;
import com.trkj.crmproject.entity.WarehouseInventory;
import com.trkj.crmproject.service.WarehouseEnterGoodsService;
import com.trkj.crmproject.service.WarehouseEnterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.service.WarehouseInventoryService;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@Service
public class WarehouseEnterServiceImpl extends ServiceImpl<WarehouseEnterDao, WarehouseEnter> implements WarehouseEnterService {
@Resource
private WarehouseEnterGoodsService goodsService;
@Resource
private WarehouseEnterDao enterDao;
@Resource
private WarehouseInventoryService inventoryService;
    @Override
    public boolean deleteAll(int id) {
        QueryWrapper<WarehouseEnterGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("weg_we_id",id);
        if (removeById(id)){
            goodsService.remove(wrapper);
        }else {
            return false;
        }
        return true;
    }

    @Override
    public WarehouseEnter findById(int id) {
        return enterDao.findById(id);
    }

    @Override
    public IPage<WarehouseEnter> findList(SearchListVo vo) {
        if (vo.getPageNum()==null){
            vo.setPageNum(1);
        }
        if (vo.getPageSize()==null){
            vo.setPageSize(10);
        }
        Page<WarehouseEnter> page=new Page<>(vo.getPageNum(),vo.getPageSize());
        QueryWrapper<WarehouseEnter> wrapper=new QueryWrapper<>();
        if (vo.getKeyword()!=null){
            wrapper.like("we_theme",vo.getKeyword())
                    .or().like("we_name",vo.getKeyword());
        }
        IPage<WarehouseEnter> enterIPage = enterDao.findList(page, wrapper);
        return enterIPage;
    }

    @Override
    public boolean addfind(WarehouseEnter enter) {
        enter.setWeTimeliness(true);
        if (save(enter)){
            if (enter.getEnterGoods()!=null){
                enter.getEnterGoods().forEach(e->{
                    e.setWegWeId(enter.getWeId());
                });
                goodsService.saveBatch(enter.getEnterGoods());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatel(WarehouseEnter enter) {
        if (saveOrUpdate(enter)){
            if (enter.getEnterGoods()!=null){
                goodsService.saveOrUpdateBatch(enter.getEnterGoods());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateState(WarehouseEnter enter) {
        boolean add = true;
        enter.setWeState(1);
        enter.setWeEntertime(new Timestamp(new Date().getTime()));
        if (saveOrUpdate(enter)){
            List<WarehouseInventory> inventories = new ArrayList<>();
            if( enter.getEnterGoods()!= null && enter.getEnterGoods().size() > 0) {
                for (int i = 0; i < enter.getEnterGoods().size(); i++) {
                    WarehouseInventory win=new WarehouseInventory();
                    win.setWiWaId(1);
                    win.setWiWaname("全国仓库");
                    win.setWiPrId(enter.getEnterGoods().get(i).getWegPrId());
                    win.setWiCount(enter.getEnterGoods().get(i).getWegCount());
                    inventories.add(win);
                }
                System.out.println("================="+inventories);
                add = inventoryService.add(inventories);
            }
        }
        return add;
    }
}
