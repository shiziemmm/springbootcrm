package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.WarehouseInventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.vo.SearchListVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface WarehouseInventoryService extends IService<WarehouseInventory> {
    Boolean add(List<WarehouseInventory> inventory);
    Boolean updateAll(List<WarehouseInventory> inventory);
    WarehouseInventory findById(int id);
    IPage<Map> findList(SearchListVo vo);
}
