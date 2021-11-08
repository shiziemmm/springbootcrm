package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Plan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.util.MyResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface PlanService extends IService<Plan> {
    MyResult planlist();
    int addplan(Plan plan);
    void delet(Integer id,String odrId,Double plaPrice);
    int upd(Plan plan);
    MyResult state(String value);
    MyResult myResult(String name);

}
