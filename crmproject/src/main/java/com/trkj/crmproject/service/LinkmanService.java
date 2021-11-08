package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Linkman;
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
public interface LinkmanService extends IService<Linkman> {

    public List<Linkman> findLinkman();

    public List<Linkman> findLinkmanName(String linkmanName);

    public List<Linkman> selectLinkman(Linkman linkman);

    public void saveLinkman(Linkman linkman);

}
