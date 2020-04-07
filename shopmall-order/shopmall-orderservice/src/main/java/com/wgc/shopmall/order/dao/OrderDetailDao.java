package com.wgc.shopmall.order.dao;


import com.wgc.shopmall.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    int batchInsert(List<OrderDetail> orderDetailList);
}