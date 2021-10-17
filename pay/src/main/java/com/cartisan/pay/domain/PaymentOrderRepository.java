package com.cartisan.pay.domain;

import com.cartisan.repository.BaseRepository;

import java.util.Optional;

/**
 * @author colin
 */

public interface PaymentOrderRepository extends BaseRepository<PaymentOrder, Long> {
    Optional<PaymentOrder> findByOrderNo(Long orderNo);
}
