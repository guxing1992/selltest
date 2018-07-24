package com.itjava.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages ="com.itjava.sell.bean.mapper" )
public class SelltestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelltestApplication.class, args);
    }
}
