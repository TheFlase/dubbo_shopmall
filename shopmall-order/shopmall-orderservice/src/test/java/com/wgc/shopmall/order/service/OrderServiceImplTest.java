package com.wgc.shopmall.order.service;

import com.wgc.shopmall.order.api.IOrderService;
import com.wgc.shopmall.order.start.ShopMallOrderStart;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @Author wgc
 * @Description
 * @Date 4/15/2020
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopMallOrderStart.class)
class OrderServiceImplTest {
    @Resource
    private IOrderService orderService;

    @Test
    void insert() {
    }
}