package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Linkman;
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
public interface LinkmanMapper extends BaseMapper<Linkman> {
    List<Linkman> selectByClientId(Integer ClientId);

    public List<Linkman> findLinkman();

    public List<Linkman> findLinkmanName(String linkmanName);

    public List<Linkman> selectLinkman(Linkman linkman);

    public void addLinkman(Linkman linkman);

    public void updateLinkman(Linkman linkman);

}
