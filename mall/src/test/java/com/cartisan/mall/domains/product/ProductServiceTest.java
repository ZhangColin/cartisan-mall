package com.cartisan.mall.domains.product;

import com.cartisan.dtos.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


/**
 * @author colin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void list() {
        final PageResult<ProductDTO> page = productService.list(100002L, PageRequest.of(0, 10));

        Assert.isTrue(page.getTotal()>0);
    }

    @Test
    public void detail() {
        final ProductDetailDTO detail = productService.detail(26L);

        Assert.notNull(detail);
    }
}
