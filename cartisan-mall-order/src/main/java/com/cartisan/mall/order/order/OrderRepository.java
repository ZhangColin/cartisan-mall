package com.cartisan.mall.order.order;

import com.cartisan.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends BaseRepository<Order, Long> {

}
