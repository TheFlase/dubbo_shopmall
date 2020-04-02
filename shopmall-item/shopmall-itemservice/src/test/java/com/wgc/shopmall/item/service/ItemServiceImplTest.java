package com.wgc.shopmall.item.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.entity.Inventory;
import start.MallShopItemStart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Author wgc
 * @Description //TODO
 * @Date 3/30/2020
 **/
@SpringBootTest(classes = MallShopItemStart.class)
class ItemServiceImplTest {
    @Reference
    private IInventoryService inventoryService;

    @Test
    void selectByItemCodes() {
        Inventory inventory = inventoryService.selectByCode("IT000001");
        System.out.println(inventory.toString());
    }
}