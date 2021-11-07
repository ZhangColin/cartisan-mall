package com.cartisan.mall.goods.goods.repository;

import com.cartisan.mall.goods.goods.domain.SpuLog;
import com.cartisan.repository.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface SpuLogRepository extends BaseRepository<SpuLog, Long> {
    List<SpuLog> findBySpuId(Long spuId);
}
