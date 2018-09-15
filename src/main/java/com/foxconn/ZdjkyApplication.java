package com.foxconn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.foxconn.mapper")
public class ZdjkyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZdjkyApplication.class, args);
    }
}
