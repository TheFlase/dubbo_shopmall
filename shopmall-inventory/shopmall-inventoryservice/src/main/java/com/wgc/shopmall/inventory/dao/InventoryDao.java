package com.wgc.shopmall.inventory.dao;


import com.wgc.shopmall.inventory.entity.Inventory;

import java.util.Map;

public interface InventoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(Long id);

    Inventory selectByCode(Map<String, Object> paramMap);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);

    int updateByItemCode(Inventory inventory);
}