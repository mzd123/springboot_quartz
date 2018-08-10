package com.mzd.my_boot_quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.mzd.my_boot_quartz.mapper")
@SpringBootApplication
public class MyBootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBootQuartzApplication.class, args);
    }

}
