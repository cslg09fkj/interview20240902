package com.example.jinxun;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.jinxun.mapper")
public class JinxunApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinxunApplication.class, args);
    }

}
