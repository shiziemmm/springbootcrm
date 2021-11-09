package com.trkj.crmproject.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.trkj.crmproject.entity.WarehouseLeave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface WarehouseLeaveDao extends BaseMapper<WarehouseLeave> {
    IPage<Map> selectList(IPage<Map> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    WarehouseLeave findByIde(int id);
}
