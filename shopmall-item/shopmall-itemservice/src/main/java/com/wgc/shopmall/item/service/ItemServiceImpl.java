package com.wgc.shopmall.item.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wgc.shopmall.item.api.IItemService;
import com.wgc.shopmall.item.dao.ItemDao;
import com.wgc.shopmall.item.entity.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author wgc
 * @Description
 * @Date 3/27/2020
 **/
@Service
@Component
public class ItemServiceImpl implements IItemService {
    @Resource
    private ItemDao itemDao;
    @Override
    public int insert(Item record) {
        return itemDao.insert(record);
    }

    @Override
    public List<Item> selectByItemCodes(Set<String> itemCodes) {
        return itemDao.selectByItemCodes(itemCodes);
    }
}
