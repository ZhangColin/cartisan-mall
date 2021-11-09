package com.cartisan.mall.order.orderitem;

import com.cartisan.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {

}
