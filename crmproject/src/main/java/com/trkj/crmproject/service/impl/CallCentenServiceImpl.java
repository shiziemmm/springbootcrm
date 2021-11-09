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

    /**
     * 查询所有的客户服务
     * @return
     */
    public List<CallCenten> findCallCenten(){
        return callCentenMapper.findCallCenten();
    }

    /**
     * 根据服务主题查询
     * @param callCentenTheme
     * @return
     */
    public List<CallCenten> findCallCentenTheme(String callCentenTheme){
        return callCentenMapper.findCallCentenTheme(callCentenTheme);
    }

    /**
     * 客户服务的高级查询
     * @param callCenten
     * @return
     */
    public List<CallCenten> selectCallCenten(CallCenten callCenten){
        return callCentenMapper.selectCallCenten(callCenten);
    }

    /**
     * 客户服务的新增和修改
     * @param callCenten
     */
    public void saveCallCenten(CallCenten callCenten){
        if (callCenten.getCallCentenId()==null || callCenten.getCallCentenId().equals("")){
            System.err.println(callCenten);
            callCentenMapper.addCallCenten(callCenten);
        }else{
            callCentenMapper.updateCallCenten(callCenten);
        }
    }

    /**
     * 客服控制台中 新增客户服务（加上QA库）
     * @param callCenten
     */
    public void addCallCentenQa(CallCenten callCenten){
        callCentenMapper.addCallCentenQa(callCenten);
    }

}
