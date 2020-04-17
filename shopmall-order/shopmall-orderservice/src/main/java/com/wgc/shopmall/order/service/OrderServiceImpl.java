package com.wgc.shopmall.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.wgc.shopmall.common.exception.BusinessException;
import com.wgc.shopmall.common.utils.IdUtil;
import com.wgc.shopmall.inventory.api.IInventoryService;
import com.wgc.shopmall.inventory.entity.Inventory;
import com.wgc.shopmall.item.api.IItemService;
import com.wgc.shopmall.item.entity.Item;
import com.wgc.shopmall.order.api.IOrderDetailService;
import com.wgc.shopmall.order.api.IOrderService;
import com.wgc.shopmall.order.dao.OrderDao;
import com.wgc.shopmall.order.entity.Order;
import com.wgc.shopmall.order.entity.OrderDetail;
import com.wgc.shopmall.order.param.OrderParam;
import com.wgc.shopmall.order.util.RedisService;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/5/2020
 **/
@Service
@Component
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private IOrderDetailService orderDetailService;
    @Reference
    private IItemService itemService;
    @Reference
    private IInventoryService inventoryService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private RedisService redisService;

    @Value("${rocketmq.order.topic}")
    private String orderTopic;
    @Value("${rocketmq.producer.group}")
    private String productMqGroup;


    @Override
    public int insert(Order record) {
        record.setOrderCode("MD"+ IdUtil.nextId());
        return orderDao.insert(record);
    }

    @Override
    @Transactional
    public void saveAndSubtractInventory(OrderParam orderParam) {
        /**
         * 1.生成订单;2.扣减库存。
         * todo 扣费并生成账单;生成库存扣减流水;记录操作日志等等
         */
        BigDecimal totalPrice = new BigDecimal(0);
        Map<String,Integer> orderItemInfoMap = new HashMap<>();
        Map<String,Item> itemMap = new HashMap<>();

        //生成订单
        List<OrderDetail> orderDetailList = orderParam.getOrderDetailList();
        HashSet<String> itemCodeSet = new HashSet<>();
        for(OrderDetail item:orderDetailList){
            itemCodeSet.add(item.getItemCode());
            orderItemInfoMap.put(item.getItemCode(),item.getItemQty());
        }
        List<Item> itemList = itemService.selectByItemCodes(itemCodeSet);
        for(Item item:itemList){
            itemMap.put(item.getItemCode(),item);
        }
        for(OrderDetail item:orderDetailList){
            Item itemTemp = itemMap.get(item.getItemCode());
            if(item.getItemQty() != 0 && itemTemp.getItemPrice() != null){
                totalPrice =totalPrice.add(new BigDecimal(item.getItemQty()).multiply(itemTemp.getItemPrice()));
            }
        }
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        this.insert(order);

        for(OrderDetail item:orderDetailList){
            item.setOrderCode(order.getOrderCode());
        }
        orderDetailService.batchInsert(orderDetailList);


        //扣减库存
        for(OrderDetail item:orderDetailList){
            Inventory inventory = new Inventory();
            inventory.setItemCode(item.getItemCode());
            inventory.setStockQty(-item.getItemQty());
            //check if has enough stockQty
            Inventory inventory1 = inventoryService.selectByCode(item.getItemCode());
            if(inventory1.getStockQty()<item.getItemQty()){
                throw new BusinessException("库存不足！");
            }
            inventoryService.updateByItemCode(inventory);
        }

    }

    @Override
    public void saveOrderByMQ(OrderParam orderParam) throws RuntimeException {
        Message msg = null;
        try {
            redisService.incr("order:save:request",new Long(60*10));
            msg = MessageBuilder.withPayload(JSON.toJSONString(orderParam).getBytes(RemotingHelper.DEFAULT_CHARSET)).build();
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException("创建订单,MQ序列化失败");
        }
        rocketMQTemplate.send(orderTopic,msg);
//        rocketMQTemplate.sendMessageInTransaction(mqGroup, springTopic, msg, null);
    }

}
