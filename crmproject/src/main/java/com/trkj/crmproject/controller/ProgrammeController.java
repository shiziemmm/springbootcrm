package com.trkj.crmproject.controller;


import com.trkj.crmproject.entity.Programme;
import com.trkj.crmproject.service.ProgrammeService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/programme")
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;
    @PostMapping("addprogramme")
    private MyResult addProgramme(@RequestBody Programme programme){
        return MyResult.SUCCESS_Object(programmeService.addProgramme(programme));
    }
    @GetMapping("/del")
    public MyResult del(@RequestParam(value = "proid",required = false) Integer proid){
        return MyResult.SUCCESS_Object(programmeService.del(proid));
    }
}

