package com.wgc.shopmall.inventory.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.dao.InventoryDao;
import com.wgc.shopmall.inventory.entity.Inventory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wgc
 * @Description
 * @Date 3/28/2020
 **/
@Service(interfaceClass = IInventoryService.class)
@org.springframework.stereotype.Service
public class InventoryServiceImpl implements IInventoryService {

    @Resource
    private InventoryDao inventoryDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return inventoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Inventory record) {
        return inventoryDao.insert(record);
    }

    @Override
    public int insertSelective(Inventory record) {
        return inventoryDao.insertSelective(record);
    }

    @Override
    public Inventory selectByPrimaryKey(Long id) {
        return inventoryDao.selectByPrimaryKey(id);
    }

    @Override
    public Inventory selectByCode(String itemCode) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("itemcode",itemCode);
        return inventoryDao.selectByCode(paramMap);
    }

    @Override
    public int updateByPrimaryKeySelective(Inventory record) {
        return inventoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Inventory record) {
        return inventoryDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByItemCode(Inventory inventory) {
        return inventoryDao.updateByItemCode(inventory);
    }
}
