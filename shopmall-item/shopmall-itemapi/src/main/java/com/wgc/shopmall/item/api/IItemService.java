package com.wgc.shopmall.item.api;


import com.wgc.shopmall.item.entity.Item;

import java.util.List;
import java.util.Set;

/**
 * @Author wgc
 * @Description
 * @Date 3/27/2020
 **/
public interface IItemService {
    int insert(Item record);

    List<Item> selectByItemCodes(Set<String> itemCodes);
}
