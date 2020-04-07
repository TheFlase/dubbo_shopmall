package com.wgc.shopmall.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.entity.Inventory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("selectByItemCode")
    @ResponseBody
    public void selectInventoryByItemCode(){
        Inventory inventory = inventoryService.selectByCode("IT000001");
        System.out.println(inventory.getId());
        System.out.println("aaa");
    }
}
