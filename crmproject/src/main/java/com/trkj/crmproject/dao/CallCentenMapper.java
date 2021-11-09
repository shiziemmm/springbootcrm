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

    /**
     * 查询所有客户服务
     * @return
     */
    public List<CallCenten> findCallCenten();

    /**
     * 根据服务主题查询
     * @param callCentenTheme
     * @return
     */
    public List<CallCenten> findCallCentenTheme(String callCentenTheme);

    /**
     * 客户服务的高级查询
     * @param callCenten
     * @return
     */
    public List<CallCenten> selectCallCenten(CallCenten callCenten);

    /**
     * 客户服务的新增
     * @param callCenten
     */
    public void addCallCenten(CallCenten callCenten);

    /**
     * 客户服务的新增（QA库）
     * @param callCenten
     */
    public void addCallCentenQa(CallCenten callCenten);

    /**
     * 客户服务的修改
     * @param callCenten
     */
    public void updateCallCenten(CallCenten callCenten);

}
