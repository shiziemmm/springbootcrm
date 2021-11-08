package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.CallCenten;
import com.trkj.crmproject.service.CallCentenService;
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
public class CallCentenController {
    @Resource
    CallCentenService callCentenService;

    @RequestMapping("/find_call_centen")
    public List<CallCenten> findCallCenten(){
        return callCentenService.findCallCenten();
    }

    @RequestMapping("/find_call_centen_theme")
    public List<CallCenten> findCallCentenTheme(String callCentenTheme){
        return callCentenService.findCallCentenTheme(callCentenTheme);
    }

    @RequestMapping("/select_call_centen")
    public List<CallCenten> selectCallCenten(CallCenten callCenten){
        return callCentenService.selectCallCenten(callCenten);
    }

    @PostMapping("/save_call_centen")
    public void saveCallCenten(@RequestBody CallCenten callCenten){
        callCentenService.saveCallCenten(callCenten);
    }

    @PostMapping("/add_call_centen_qa")
    public void addCallCentenQa(@RequestBody CallCenten callCenten){
        callCentenService.addCallCentenQa(callCenten);
    }
}

