package com.trkj.crmproject.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    /**
     * 用户登录
     * @param params
     * @return
     */
    @PostMapping(value = "/login")
    public Map login(@RequestBody Map params) {
//        return userService.login(params);
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("token","admin-token");
        response.put("code",200);
        response.put("message","登录成功");
        response.put("data",responseData);
        return response;
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
