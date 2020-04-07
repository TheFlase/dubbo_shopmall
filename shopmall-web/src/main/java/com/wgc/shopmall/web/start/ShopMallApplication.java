package com.wgc.shopmall.web.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/5/2020
 **/
@SpringBootApplication(scanBasePackages = "com.wgc.shopmall.web")
public class ShopMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopMallApplication.class, args);
    }
}
