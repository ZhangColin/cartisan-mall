package com.cartisan.pay.service.impl;

import com.cartisan.pay.PayApplicationTest;
import com.cartisan.pay.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author colin
 */
public class PayServiceImplTest extends PayApplicationTest {
@Autowired
private PayService payService;

    @Test
    public void create() {
        // BigDecimal.valueOf(0.01)
        // new BigDecimal("0.01") 千万不要用 new BigDecimal(0.01)，否则精度会出现问题
        payService.create("1234567890", BigDecimal.valueOf(0.01), BestPayTypeEnum.WXPAY_NATIVE);
    }
}
