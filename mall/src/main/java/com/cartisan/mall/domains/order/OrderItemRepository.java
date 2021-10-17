package com.cartisan.mall.domains.order;

import com.cartisan.repository.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {
    List<OrderItem> findByOrderNo(Long orderNo);

}
