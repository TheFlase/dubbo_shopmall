package com.wgc.shopmall.order.param;

import com.wgc.shopmall.order.entity.OrderDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author wgc
 * @Description 订单
 * @Date 3/28/2020
 **/
public class OrderParam implements Serializable {
    private String orderCode;

    private BigDecimal totalPrice;

    private List<OrderDetail> orderDetailList;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
