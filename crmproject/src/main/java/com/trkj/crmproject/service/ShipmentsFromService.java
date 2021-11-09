package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.ShipmentsFrom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.entity.Vo.SelectWhere;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ShipmentsFromService extends IService<ShipmentsFrom> {

    boolean addShipment(ShipmentsFrom shipments);

    List<ShipmentsFrom> selectWhereShipment(SelectWhere selectWhere);

    boolean updateShipmentState(Integer odrId,Integer sptId);

}
