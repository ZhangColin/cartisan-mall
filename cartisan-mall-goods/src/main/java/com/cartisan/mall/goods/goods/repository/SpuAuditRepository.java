package com.cartisan.mall.goods.goods.repository;

import com.cartisan.mall.goods.goods.domain.SpuAudit;
import com.cartisan.repository.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface SpuAuditRepository extends BaseRepository<SpuAudit, Long> {
    List<SpuAudit> findBySpuId(Long spuId);
}
