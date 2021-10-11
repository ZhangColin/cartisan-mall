package com.cartisan.mall.domains.order;

import com.cartisan.dto.PageResult;
import org.springframework.data.domain.Pageable;

/**
 * @author colin
 */
public interface OrderService {
    OrderDTO create(Long userId, Long shippingId);

    PageResult<OrderInfo> list(Long userId, Pageable pageable);

    OrderDTO detail(Long userId, Long orderNo);

    void cancel(Long userId, Long orderNo);
    void paid(Long orderNo);
}
