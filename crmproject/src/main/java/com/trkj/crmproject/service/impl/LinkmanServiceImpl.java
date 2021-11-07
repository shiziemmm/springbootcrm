package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Linkman;
import com.trkj.crmproject.dao.LinkmanMapper;
import com.trkj.crmproject.service.LinkmanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LinkmanServiceImpl extends ServiceImpl<LinkmanMapper, Linkman> implements LinkmanService {
    @Resource
    LinkmanMapper linkmanMapper;

    public List<Linkman> findLinkman(){
        return linkmanMapper.findLinkman();
    }

    public List<Linkman> findLinkmanSex(String linkmanSex){
        return linkmanMapper.findLinkmanSex(linkmanSex);
    }

    public List<Linkman> selectLinkman(Linkman linkman){
        return linkmanMapper.selectLinkman(linkman);
    }

    public void saveLinkman(Linkman linkman){
        if (linkman.getLinkmanId() == null || linkman.getLinkmanId().equals("")){
            linkman.setClientId(linkman.getClient().getClientId());
            linkmanMapper.addLinkman(linkman);
        }else {
            linkmanMapper.updateLinkman(linkman);
        }
    }

}
