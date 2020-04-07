package com.wgc.shopmall.order.start;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author wgc
 * @Description
 * @Date 4/5/2020
 **/
@SpringBootApplication
@EnableDubbo
@MapperScan("com.wgc.shopmall.order.dao")
@DubboComponentScan("com.wgc.shopmall.order.service")
public class ShopMallOrderStart {
    public static void main(String[] args) {
        SpringApplication.run(ShopMallOrderStart.class, args);
    }
}
