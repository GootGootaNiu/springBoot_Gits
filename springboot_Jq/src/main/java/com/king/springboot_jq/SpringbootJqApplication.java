package com.king.springboot_jq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.king.springboot_jq.mapper")
public class SpringbootJqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJqApplication.class, args);
    }

}
