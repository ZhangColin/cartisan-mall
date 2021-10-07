package com.cartisan.mall.domains.category;

import java.util.List;
import java.util.Set;

/**
 * @author colin
 */
public interface CategoryService {
    List<CategoryDTO> findAllCategories();

    Set<Long> findSubCategoryIds(Long id);
}
