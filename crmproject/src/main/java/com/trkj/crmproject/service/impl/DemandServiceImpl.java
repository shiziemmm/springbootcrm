package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Demand;
import com.trkj.crmproject.dao.DemandMapper;
import com.trkj.crmproject.service.DemandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements DemandService {

    /**
     * @param demand 添加需求
     * @return
     */
    @Override
    public Boolean addDemand(Demand demand) {
        if(demand.getClientId()!=null && demand.getEmpId()!=null && demand.getOpId()!=null){
            return saveOrUpdate(demand);
        }
        return false;
    }

    /**
     * @param deid 删除需求（修改状态）
     * @return
     */
    @Override
    public Boolean del(Integer deid) {
        if(deid!=null){
            Demand demand=getById(deid);
            if(demand!=null){
                demand.setDeTimeliness(true);
                return updateById(demand);
            }
        }
        return false;
    }
}
