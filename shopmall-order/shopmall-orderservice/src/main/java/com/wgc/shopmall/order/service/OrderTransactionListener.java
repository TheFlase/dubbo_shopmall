package com.wgc.shopmall.order.service;

import com.alibaba.fastjson.JSON;
import com.wgc.shopmall.order.api.IOrderService;
import com.wgc.shopmall.order.param.OrderParam;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/9/2020
 **/
@Component
@RocketMQTransactionListener(txProducerGroup = "order-transaction-group")
public class OrderTransactionListener implements RocketMQLocalTransactionListener{
    protected static final Logger LOG = LoggerFactory.getLogger(OrderTransactionListener.class);
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Resource
    private IOrderService orderService;

    /**
     * 执行本地事务
     * @param message
     * @param arg 订单参数实体
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        LOG.info("MQ:开始执行本地事务");
        String transactionID = (String)message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(transactionID,status);
        try {
            OrderParam orderParam = (OrderParam) message.getPayload();
            orderService.saveAndSubtractInventory(orderParam);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("MQ消费：保存订单异常,订单信息是:{}"+ JSON.toJSONString(arg));
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 事务状态检查，上面是存在Map中的，实际情况可存储到redis
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transetionId = (String)message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        Integer status = localTrans.get(transetionId);
        if(null != status){
            switch (status){
                case 0:
                    return RocketMQLocalTransactionState.UNKNOWN;
                case 1:
                    return RocketMQLocalTransactionState.COMMIT;
                case 2:
                    return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
