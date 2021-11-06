package com.trkj.crmproject.controller;

import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.service.EmpService;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    EmpService service;
    /**
     * 用户登录
     * @param params
     * @return
     */
    @PostMapping(value = "/login")
    public MyResult login(@RequestBody Map params) {
//        return userService.login(params);
        System.err.println(params);
        System.err.println(params.get("username"));
        Emp e=new Emp();
        e.setEmpName(params.get("username").toString());
        e.setEmpPass(params.get("password").toString());
        MyResult login = service.login(e);
        return login;
    }

    @GetMapping("/info")
    public Map info(@RequestParam(value = "token") String token) {
//        HashMap<String, Object> response = new HashMap<>();
//        return response;
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("roles","admin");
        responseData.put("name","Super admin");
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",200);
        responseInfo.put("message","登录成功");
        responseInfo.put("data",responseData);
        return responseInfo;
    }
    @PostMapping(value = "/logout")
    @ResponseBody
    public Map logout(HttpServletRequest request){
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",200);
        response.put("message","退出成功");
        return response;
    }
}
