package com.cartisan.pay.domains.payinfo;

import com.cartisan.repositories.BaseRepository;

import java.util.Optional;

/**
 * @author colin
 */

public interface PayInfoRepository extends BaseRepository<PayInfo, Long> {
    Optional<PayInfo> findByOrderNo(Long orderNo);
}
