package com.cartisan.mall.domains.shipping;

import com.cartisan.dtos.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author colin
 */
@Service
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public Long add(Long userId, ShippingParam param) {
        final Shipping shipping = new Shipping();
        BeanUtils.copyProperties(param, shipping);

        shipping.setUserId(userId);
        shippingRepository.save(shipping);

        return shipping.getId();
    }

    @Override
    public void delete(Long userId, Long shippingId) {
        shippingRepository.deleteByUserIdAndId(userId, shippingId);
    }

    @Override
    public void update(Long userId, Long shippingId, ShippingParam param) {
        final Shipping shipping = shippingRepository.findById(shippingId).orElseThrow(() -> new RuntimeException("地址没有找到。"));

        BeanUtils.copyProperties(param, shipping);

        shippingRepository.save(shipping);
    }

    @Override
    public PageResult<Shipping> list(Long userId, Pageable pageable) {
        final Page<Shipping> page = shippingRepository.findByUserId(userId, pageable);
        return new PageResult<>(page.getTotalElements(), page.getTotalPages(), page.getContent());
    }
}
