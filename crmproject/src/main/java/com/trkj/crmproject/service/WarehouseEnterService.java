package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.entity.WarehouseEnter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.vo.SearchListVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface WarehouseEnterService extends IService<WarehouseEnter> {
   boolean deleteAll(int id);
   WarehouseEnter findById(int id);
   IPage<WarehouseEnter> findList(SearchListVo vo);
   boolean addfind(WarehouseEnter enter);
   boolean updatel(WarehouseEnter enter);
   boolean updateState(WarehouseEnter enter);
}
