package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.OrderFromDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trkj.crmproject.entity.Vo.SelectWhere;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface OrderFromDetailMapper extends BaseMapper<OrderFromDetail> {

    List<OrderFromDetail> selectOrderDetailsWhere(SelectWhere selectWhere);

}
