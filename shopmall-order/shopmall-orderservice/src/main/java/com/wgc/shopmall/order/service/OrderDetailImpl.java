package com.wgc.shopmall.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wgc.shopmall.order.api.IOrderDetailService;
import com.wgc.shopmall.order.dao.OrderDetailDao;
import com.wgc.shopmall.order.entity.OrderDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author wgc
 * @Description
 * @Date 3/28/2020
 **/
@Service
@Component
public class OrderDetailImpl implements IOrderDetailService {
    @Resource
    private OrderDetailDao orderDetailDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderDetail record) {
        return orderDetailDao.insert(record);
    }

    @Override
    public int insertSelective(OrderDetail record) {
        return orderDetailDao.insertSelective(record);
    }

    @Override
    public OrderDetail selectByPrimaryKey(Long id) {
        return orderDetailDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderDetail record) {
        return orderDetailDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderDetail record) {
        return orderDetailDao.updateByPrimaryKey(record);
    }

    @Override
    public int batchInsert(List<OrderDetail> orderDetailList) {
        return orderDetailDao.batchInsert(orderDetailList);
    }
}
