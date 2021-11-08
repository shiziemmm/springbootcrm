package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.CallCenten;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface CallCentenMapper extends BaseMapper<CallCenten> {

    public List<CallCenten> findCallCenten();

    public List<CallCenten> findCallCentenTheme(String callCentenTheme);

    public List<CallCenten> selectCallCenten(CallCenten callCenten);

    public void addCallCenten(CallCenten callCenten);

    public void addCallCentenQa(CallCenten callCenten);

    public void updateCallCenten(CallCenten callCenten);

}
