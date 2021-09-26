package com.cartisan.mall;

import com.cartisan.mall.domains.category.Category;
import com.cartisan.mall.domains.category.CategoryRepository;
import com.cartisan.mall.domains.category.mapper.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author colin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void contextLoads() {
        final Optional<Category> category = categoryRepository.findById(100001L);
        System.out.println(category);

        final Category c = categoryMapper.findById(100001L);
        System.out.println(c);
    }
}
