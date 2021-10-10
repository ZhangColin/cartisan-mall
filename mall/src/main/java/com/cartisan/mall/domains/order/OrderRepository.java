package com.cartisan.mall.domains.order;

import com.cartisan.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * @author colin
 */
public interface OrderRepository  extends BaseRepository<Order, Long> {
    Page<Order> findByUserId(Long userId, Pageable pageable);

    Optional<Order> findByUserIdAndOrderNo(Long userIed, Long orderNo);
    Optional<Order> findByOrderNo(Long orderNo);
}
