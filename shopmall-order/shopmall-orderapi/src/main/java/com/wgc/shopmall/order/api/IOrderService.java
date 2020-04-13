package com.wgc.shopmall.order.api;

import com.wgc.shopmall.order.entity.Order;
import com.wgc.shopmall.order.param.OrderParam;

import java.io.UnsupportedEncodingException;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/5/2020
 **/
public interface IOrderService {
    int insert(Order record);

    /**
     * 生成订单并且扣减库存
     * @param orderParam
     */
    void saveAndSubtractInventory(OrderParam orderParam);

    /**
     * 通过MQ保存订单
     * @param orderParam
     */
    void saveOrderByMQ(OrderParam orderParam) throws RuntimeException;
}
