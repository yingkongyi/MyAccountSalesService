package com.westlakefinancial.technology;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jiapeng.wu
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.westlakefinancial.technology.dao")
public class MyAccountSalesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAccountSalesServiceApplication.class, args);
    }

}
