package com.wgc.shopmall.inventory.api;


import com.wgc.shopmall.inventory.entity.Inventory;

/**
 * @Author wgc
 * @Description
 * @Date 3/27/2020
 **/
public interface IInventoryService {
    int deleteByPrimaryKey(Long id);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(Long id);

    Inventory selectByCode(String itemCode);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);

    int updateByItemCode(Inventory inventory);
}
