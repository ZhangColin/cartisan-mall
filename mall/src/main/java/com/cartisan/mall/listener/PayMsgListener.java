package com.cartisan.mall.listener;

import com.cartisan.mall.domains.order.OrderService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author colin
 */
@Component
@RabbitListener(queues = "payNotify")
@Slf4j
public class PayMsgListener {
    private final OrderService orderService;

    public PayMsgListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitHandler
    public void process(String msg) {
        final PayInfo payInfo = new Gson().fromJson(msg, PayInfo.class);
        if (payInfo.getPlatformStatus().equals("SUCCESS")) {
            orderService.paid(payInfo.getOrderNo());
        }
    }
}
