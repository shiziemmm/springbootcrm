package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Demand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface DemandService extends IService<Demand> {
    Boolean addDemand(Demand demand);
    Boolean del(Integer deid);
}
