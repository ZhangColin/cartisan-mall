package com.cartisan.mall.goods.goods.repository;

import com.cartisan.mall.goods.goods.domain.Sku;
import com.cartisan.repository.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface SkuRepository extends BaseRepository<Sku, Long> {
    List<Sku> findBySpuId(Long spuId);

    void deleteBySpuId(Long spuId);
}
