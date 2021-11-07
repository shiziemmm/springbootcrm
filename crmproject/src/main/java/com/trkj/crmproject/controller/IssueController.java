package com.trkj.crmproject.controller;


import com.trkj.crmproject.service.IssueService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    IssueService issueService;

    @GetMapping("/issuelist")
    public MyResult issuelist(){
        return issueService.issuelist();
    }

}

