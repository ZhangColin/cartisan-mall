package com.cartisan.mall.order.orderitem;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class OrderItemAppService {
    private final OrderItemRepository repository;

    private final OrderItemConverter converter = OrderItemConverter.CONVERTER;

    public OrderItemAppService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public PageResult<OrderItemDto> searchOrderItems(@NonNull OrderItemQuery orderItemQuery, @NonNull Pageable pageable) {
        final Page<OrderItem> searchResult = repository.findAll(querySpecification(orderItemQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public OrderItemDto getOrderItem(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public OrderItemDto addOrderItem(OrderItemParam orderItemParam) {
        final OrderItem orderItem = new OrderItem(orderItemParam.getOrderId(),
        orderItemParam.getSpuId(),
        orderItemParam.getSkuId(),
        orderItemParam.getName(),
        orderItemParam.getPrice(),
        orderItemParam.getQuantity(),
        orderItemParam.getWeight(),
        orderItemParam.getImage(),
        orderItemParam.getIsReturn());

        return converter.convert(repository.save(orderItem));
    }

    @Transactional(rollbackOn = Exception.class)
    public OrderItemDto editOrderItem(Long id, OrderItemParam orderItemParam) {
        final OrderItem orderItem = requirePresent(repository.findById(id));

        orderItem.describe(orderItemParam.getOrderId(),
        orderItemParam.getSpuId(),
        orderItemParam.getSkuId(),
        orderItemParam.getName(),
        orderItemParam.getPrice(),
        orderItemParam.getQuantity(),
        orderItemParam.getWeight(),
        orderItemParam.getImage(),
        orderItemParam.getIsReturn());

        return converter.convert(repository.save(orderItem));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeOrderItem(long id) {
        repository.deleteById(id);
    }
}
