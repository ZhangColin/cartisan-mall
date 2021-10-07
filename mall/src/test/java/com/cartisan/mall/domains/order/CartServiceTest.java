package com.cartisan.mall.domains.order;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
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
@Slf4j
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @Test
    public void add() {
    }

    @Before
    public void setup() {
        final AddCartCommand command = new AddCartCommand();
        command.setProductId(26L);
        command.setSelected(true);
        command.setUserId(1L);

        cartService.add(command);
    }

    @Test
    public void list() {
        final CartDTO list = cartService.list(1L);

        log.info("list={}", new Gson().toJson(list));
    }

    @Test
    public void update() {
        final UpdateCartCommand command = new UpdateCartCommand();
        command.setProductId(26L);
        command.setQuantity(10);
        command.setUserId(1L);

        cartService.update(command);
    }

    @Test
    public void delete() {
    }

    @After
    public void clean() {
        final DeleteCartCommand command = new DeleteCartCommand();
        command.setProductId(26L);
        command.setUserId(1L);

        cartService.delete(command);
    }

    @Test
    public void selectAll() {
        final CartDTO list = cartService.selectAll(1L);

        log.info("list={}", new Gson().toJson(list));
    }

    @Test
    public void unSelectAll() {
        final CartDTO list = cartService.unSelectAll(1L);

        log.info("list={}", new Gson().toJson(list));
    }

    @Test
    public void sum() {
        final Integer sum = cartService.sum(1L);
        log.info("sum={}", sum);
    }
}
