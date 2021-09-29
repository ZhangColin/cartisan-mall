package com.cartisan.mall.domains.category.mapper;

import com.cartisan.mall.MallApplicationTest;
import com.cartisan.mall.domains.category.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author colin
 */
public class CategoryMapperTest extends MallApplicationTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        final Category category = categoryMapper.findById(100001L);
        System.out.println(category);
    }

    @Test
    public void queryById() {
        final Category category = categoryMapper.queryById(100001);
        System.out.println(category);
    }
}
