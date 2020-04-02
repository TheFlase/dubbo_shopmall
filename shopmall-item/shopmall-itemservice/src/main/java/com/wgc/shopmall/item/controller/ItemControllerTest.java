package com.wgc.shopmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.entity.Inventory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 3/30/2020
 **/
@Controller
@RequestMapping("/itemtest/")
public class ItemControllerTest {
    @Reference(check = false)
    private IInventoryService inventoryService;

    @RequestMapping("selectByItemCode")
    @ResponseBody
    public void selectInventoryByItemCode(){
        Inventory inventory = inventoryService.selectByCode("IT000001");
        System.out.println(inventory.getId());
    }
}
