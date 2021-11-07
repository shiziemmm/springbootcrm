package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.CallCenten;
import com.trkj.crmproject.dao.CallCentenMapper;
import com.trkj.crmproject.service.CallCentenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
public class CallCentenServiceImpl extends ServiceImpl<CallCentenMapper, CallCenten> implements CallCentenService {
    @Resource
    CallCentenMapper callCentenMapper;

    public List<CallCenten> findCallCenten(){
        return callCentenMapper.findCallCenten();
    }

    public List<CallCenten> findCallCentenTheme(String callCentenTheme){
        return callCentenMapper.findCallCentenTheme(callCentenTheme);
    }

    public List<CallCenten> selectCallCenten(CallCenten callCenten){
        return callCentenMapper.selectCallCenten(callCenten);
    }

    public void saveCallCenten(CallCenten callCenten){
        if (callCenten.getCallCentenId()==null || callCenten.getCallCentenId().equals("")){
            callCentenMapper.addCallCenten(callCenten);
        }else{
            callCentenMapper.updateCallCenten(callCenten);
        }
    }

}
