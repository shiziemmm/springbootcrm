package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Qa;
import com.trkj.crmproject.service.QaService;
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
public class QaController {
    @Resource
    QaService qaService;

    @RequestMapping("/find_qa")
    public List<Qa> findQa(){
        return qaService.findQa();
    }

    @RequestMapping("/find_qa_problem")
    public List<Qa> findQaProblem(String qaProblem){
        return qaService.findQaProblem(qaProblem);
    }

    @RequestMapping("/find_qa_classify")
    public List<Qa> findQaClassify(String qaClassify){
        return qaService.findQaClassify(qaClassify);
    }

    @RequestMapping("/select_qa")
    public List<Qa> selectQa(Qa qa){
        return qaService.selectQa(qa);
    }

    @PostMapping("/save_qa")
    public void saveQa(@RequestBody Qa qa){
        qaService.saveQa(qa);
    }
}

