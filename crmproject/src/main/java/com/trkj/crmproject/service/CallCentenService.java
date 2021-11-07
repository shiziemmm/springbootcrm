package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.CallCenten;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface CallCentenService extends IService<CallCenten> {
    public List<CallCenten> findCallCenten();

    public List<CallCenten> findCallCentenTheme(String callCentenTheme);

    public List<CallCenten> selectCallCenten(CallCenten callCenten);

    public void saveCallCenten(CallCenten callCenten);

}
