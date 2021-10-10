package com.cartisan.mall.domains.shipping;

import com.cartisan.dtos.PageResult;
import org.springframework.data.domain.Pageable;

/**
 * @author colin
 */
public interface ShippingService {
    Long add(Long userId, ShippingParam param);

    void delete(Long userId, Long shippingId);

    void update(Long userId, Long shippingId, ShippingParam param);

    PageResult<Shipping> list(Long userId, Pageable pageable);
}
