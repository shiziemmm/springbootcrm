package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Linkman;
import com.trkj.crmproject.service.LinkmanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
public class LinkmanController {
    @Resource
    LinkmanService linkmanService;

    @RequestMapping("/find_linkman")
    public List<Linkman> findLinkman(){
        return linkmanService.findLinkman();
    }

    @RequestMapping("/find_linkman_name")
    public List<Linkman> findLinkmanName(String linkmanName){
        return linkmanService.findLinkmanName(linkmanName);
    }

    @RequestMapping("/select_linkman")
    public List<Linkman> selectLinkman(Linkman linkman){
        return linkmanService.selectLinkman(linkman);
    }

    @PostMapping("/save_linkman")
    public void saveLinkman(@RequestBody Linkman linkman){
        linkmanService.saveLinkman(linkman);
    }
}

