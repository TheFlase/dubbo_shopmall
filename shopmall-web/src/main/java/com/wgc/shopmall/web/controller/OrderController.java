package com.wgc.shopmall.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.entity.Inventory;
import com.wgc.shopmall.order.api.IOrderService;
import com.wgc.shopmall.order.entity.OrderDetail;
import com.wgc.shopmall.order.param.OrderParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/5/2020
 **/
@Controller
@RequestMapping("/order")
public class OrderController {
    @Reference
    private IInventoryService inventoryService;
    @Reference
    private IOrderService orderService;

    @RequestMapping("selectByItemCode")
    @ResponseBody
    public void selectInventoryByItemCode(){
        Inventory inventory = inventoryService.selectByCode("IT000001");
        System.out.println(inventory.getId());
        System.out.println("aaa");
    }

    @RequestMapping("/saveOrderTest")
    @ResponseBody
    public String saveOrderTest(){
        OrderParam orderParam = new OrderParam();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setItemCode("IT000001");
        orderDetail.setItemQty(1);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);
        orderParam.setOrderDetailList(orderDetailList);
        orderService.saveOrderByMQ(orderParam);
        return "true";
    }
}
