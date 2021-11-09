package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Warehouse;
import com.trkj.crmproject.entity.WarehouseInventory;
import com.trkj.crmproject.dao.WarehouseInventoryDao;
import com.trkj.crmproject.service.WarehouseInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class WarehouseInventoryServiceImpl extends ServiceImpl<WarehouseInventoryDao, WarehouseInventory> implements WarehouseInventoryService {
    @Resource
    private WarehouseInventoryDao inventoryDao;

    /**
     * 增加库存
     * @param inventory
     * @return
     */
    @Override
    public Boolean add(List<WarehouseInventory> inventory) {
        boolean bl = false;
        System.out.println("------------"+inventory);
        for (int i=0;i<inventory.size();i++){
            System.out.println("111111111"+inventory.get(i));
            QueryWrapper<WarehouseInventory> wrapper=new QueryWrapper<>();
            wrapper.eq("wi_pr_id",inventory.get(i).getWiPrId());
            WarehouseInventory one = getOne(wrapper);
            System.out.println("222222222"+one);
            if (one !=null){
                System.out.println("3333333333"+inventory.get(i));
                one.setWiCount(one.getWiCount()+inventory.get(i).getWiCount());
                bl= saveOrUpdate(one);
            }else {
                System.out.println("444444444444"+inventory.get(i));
                inventory.get(i).setWiTimeliness(true);
                bl = save(inventory.get(i));
            }
        };
        return bl;
    }

    /**
     * 删除库存
     * @param inventory
     * @return
     */
    @Override
    public Boolean updateAll(List<WarehouseInventory> inventory) {
        boolean up = false;
        for (int i=0;i<inventory.size();i++){
            QueryWrapper<WarehouseInventory> wrapper=new QueryWrapper<>();
            wrapper.eq("wi_pr_id",inventory.get(i).getWiPrId());
            WarehouseInventory one = getOne(wrapper);
            if (one !=null){
                one.setWiCount(one.getWiCount()-inventory.get(i).getWiCount());
                up= saveOrUpdate(one);
            }else {
                return false;
            }
        }
        return up;
    }

    @Override
    public WarehouseInventory findById(int id) {
        QueryWrapper<WarehouseInventory> wrapper=new QueryWrapper<>();
        wrapper.eq("wi_pr_id",id);
        WarehouseInventory one = getOne(wrapper);
        return one;
    }

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
            wrapper.like("l.pr_name",vo.getKeyword());
        }
        wrapper.eq("wa_timeliness",1);
        return inventoryDao.selectList(page,wrapper);
    }
}
