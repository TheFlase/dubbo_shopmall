package com.wgc.shopmall.item.dao;

import com.wgc.shopmall.item.entity.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

public interface ItemDao {

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectByItemCodes(@Param("itemCodes") Set<String> itemCodes);
}