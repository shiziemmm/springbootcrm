package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Demand;
import com.trkj.crmproject.entity.Programme;
import com.trkj.crmproject.dao.ProgrammeMapper;
import com.trkj.crmproject.service.ProgrammeService;
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
public class ProgrammeServiceImpl extends ServiceImpl<ProgrammeMapper, Programme> implements ProgrammeService {

    @Override
    public Boolean addProgramme(Programme programme) {
        if(programme.getClientId()!=null && programme.getEmpId()!=null && programme.getOpId()!=null){
            return saveOrUpdate(programme);
        }
        return false;
    }

    @Override
    public Boolean del(Integer proid) {
        if(proid!=null){
            Programme programme=getById(proid);
            if(programme!=null){
                programme.setProTimeliness(true);
                return updateById(programme);
            }
        }
        return false;
    }
}
