package com.cartisan.mall.domains.order;

import com.cartisan.dto.PageResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author colin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        final OrderDTO orderDTO = orderService.create(1L, 4L);
        log.info("order={}", new Gson().toJson(orderDTO));
    }

    @Test
    public void list() {
        final PageResult<OrderInfo> list = orderService.list(1L, PageRequest.of(0, 10));
        log.info("list={}", new Gson().toJson(list));
    }

    @Test
    public void detail() {
        final OrderDTO orderDTO = orderService.detail(1L, 1590202935462203392L);
        log.info("order={}", new Gson().toJson(orderDTO));
    }

    @Test
    public void cancel() {
        orderService.cancel(1L, 1590202935462203392L);
    }
}
