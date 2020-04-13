package com.wgc.shopmall.order.consumer;

import com.alibaba.fastjson.JSON;
import com.wgc.shopmall.order.api.IOrderService;
import com.wgc.shopmall.order.param.OrderParam;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/10/2020
 **/
@Component
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "consumer-order-group")
public class OrderConsumer implements RocketMQListener<OrderParam> {

    private static Logger logger = (Logger) LoggerFactory.getLogger(OrderConsumer.class);

    @Resource
    private IOrderService orderService;

    @Override
    public void onMessage(OrderParam orderParam) {
        logger.info("MQ消费订单信息开始,订单信息是:{}"+ JSON.toJSONString(orderParam));
        orderService.saveAndSubtractInventory(orderParam);
        logger.info("MQ消费订单信息完毕=========");
    }
}
