package com.cartisan.mall.domains.product;

import com.cartisan.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author colin
 */
public interface ProductRepository  extends BaseRepository<Product, Long> {
    Page<Product> findByCategoryIdIn(Set<Long> categoryIds, Pageable pageable);

    Set<Product> findByIdIn(List<Long> productIds);
}
