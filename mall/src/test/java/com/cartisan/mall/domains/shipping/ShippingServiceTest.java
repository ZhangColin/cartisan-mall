package com.cartisan.mall.domains.shipping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author colin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShippingServiceTest {
    @Autowired
    private ShippingService shippingService;

    @Test
    public void add() {
        ShippingParam shippingParam = new ShippingParam();
        shippingParam.setReceiverName("colin");
        shippingParam.setReceiverAddress("address");
        shippingParam.setReceiverCity("shanghai");
        shippingParam.setReceiverMobile("18001828301");
        shippingParam.setReceiverPhone("57677720");
        shippingParam.setReceiverDistrict("pudong");

        shippingService.add(1L, shippingParam);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void list() {
    }
}
