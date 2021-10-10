package com.cartisan.pay.domain;

import com.cartisan.repositories.BaseRepository;

import java.util.Optional;

/**
 * @author colin
 */

public interface PaymentOrderRepository extends BaseRepository<PaymentOrder, Long> {
    Optional<PaymentOrder> findByOrderNo(Long orderNo);
}
