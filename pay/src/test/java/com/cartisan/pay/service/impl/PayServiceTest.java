package com.cartisan.pay.service.impl;

import com.cartisan.pay.PayApplicationTest;
import com.cartisan.pay.application.PayService;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author colin
 */
public class PayServiceTest extends PayApplicationTest {
@Autowired
private PayService payService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void create() {
        // BigDecimal.valueOf(0.01)
        // new BigDecimal("0.01") 千万不要用 new BigDecimal(0.01)，否则精度会出现问题
//        payService.prePay("1234567890", BigDecimal.valueOf(0.01), BestPayTypeEnum.WXPAY_NATIVE);
    }

    @Test
    public void sendMQMessage() {
        amqpTemplate.convertAndSend("payNotify", "hello");
    }

}
