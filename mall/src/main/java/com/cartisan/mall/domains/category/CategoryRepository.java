package com.cartisan.mall.domains.category;

import com.cartisan.repositories.BaseRepository;

import java.util.List;

/**
 * @author colin
 */

public interface CategoryRepository extends BaseRepository<Category, Long> {
    List<Category> findByStatus(Integer status);
}
