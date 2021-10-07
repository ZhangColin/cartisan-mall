package com.cartisan.mall.domains.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

/**
 * @author colin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAllCategories() {
        final List<CategoryDTO> allCategories = categoryService.findAllCategories();

        Assert.isTrue(allCategories.size()>0);
    }

    @Test
    public void findSubCategoryIds() {
        final Set<Long> subCategoryIds = categoryService.findSubCategoryIds(100001L);

        Assert.isTrue(subCategoryIds.size()>0);
    }
}
