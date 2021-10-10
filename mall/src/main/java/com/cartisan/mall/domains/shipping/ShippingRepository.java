package com.cartisan.mall.domains.shipping;

import com.cartisan.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author colin
 */
public interface ShippingRepository  extends BaseRepository<Shipping, Long> {
    Page<Shipping> findByUserId(Long userId, Pageable pageable);

    Optional<Shipping> findByUserIdAndId(Long userId, Long shippingId);

    void deleteByUserIdAndId(Long userId, Long shippingId);
}
