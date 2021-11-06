package com.trkj.crmproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//配置MyBatis-Plus注解的扫描
@MapperScan("com.trkj.crmproject.dao")
public class CrmprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmprojectApplication.class, args);
    }

}
