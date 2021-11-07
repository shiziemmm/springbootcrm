package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Warehouse;
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
public interface WarehouseService extends IService<Warehouse> {
    IPage<Warehouse> findList (SearchListVo vo);
}
