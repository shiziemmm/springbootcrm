package com.trkj.crmproject.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.trkj.crmproject.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface WarehouseDao extends BaseMapper<Warehouse> {
   IPage<Warehouse> selectList(IPage<Warehouse> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
