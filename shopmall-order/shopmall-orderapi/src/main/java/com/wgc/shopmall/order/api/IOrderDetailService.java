package com.wgc.shopmall.order.api;


import com.wgc.shopmall.order.entity.OrderDetail;

import java.util.List;

/**
 * @Author wgc
 * @Description
 * @Date 3/28/2020
 **/
public interface IOrderDetailService {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    int batchInsert(List<OrderDetail> orderDetailList);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

}
