package com.cartisan.mall.order.order;

import com.cartisan.dto.PageResult;
import com.cartisan.util.SnowflakeIdWorker;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class OrderAppService {
    private final OrderRepository repository;
    private final SnowflakeIdWorker idWorker;

    private final OrderConverter converter = OrderConverter.CONVERTER;

    public OrderAppService(OrderRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<OrderDto> searchOrders(@NonNull OrderQuery orderQuery, @NonNull Pageable pageable) {
        final Page<Order> searchResult = repository.findAll(querySpecification(orderQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public OrderDto getOrder(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public OrderDto addOrder(OrderParam orderParam) {
        final Order order = new Order(idWorker.nextId(),
        orderParam.getTotalAmount(),
        orderParam.getDiscountAmount(),
        orderParam.getFreight(),
        orderParam.getActualAmount(),
        orderParam.getPayType(),
        orderParam.getPlaced(),
        orderParam.getPaid(),
        orderParam.getDelivered(),
        orderParam.getCompleted(),
        orderParam.getClosed(),
        orderParam.getShippingName(),
        orderParam.getShippingCode(),
        orderParam.getUsername(),
        orderParam.getBuyerMessage(),
        orderParam.getBuyerRate(),
        orderParam.getReceiverContact(),
        orderParam.getReceiverMobile(),
        orderParam.getReceiverAddress(),
        orderParam.getSourceType(),
        orderParam.getOrderStatus(),
        orderParam.getPayStatus());

        return converter.convert(repository.save(order));
    }

    @Transactional(rollbackOn = Exception.class)
    public OrderDto editOrder(Long id, OrderParam orderParam) {
        final Order order = requirePresent(repository.findById(id));

        order.describe(orderParam.getTotalAmount(),
        orderParam.getDiscountAmount(),
        orderParam.getFreight(),
        orderParam.getActualAmount(),
        orderParam.getPayType(),
        orderParam.getPlaced(),
        orderParam.getPaid(),
        orderParam.getDelivered(),
        orderParam.getCompleted(),
        orderParam.getClosed(),
        orderParam.getShippingName(),
        orderParam.getShippingCode(),
        orderParam.getUsername(),
        orderParam.getBuyerMessage(),
        orderParam.getBuyerRate(),
        orderParam.getReceiverContact(),
        orderParam.getReceiverMobile(),
        orderParam.getReceiverAddress(),
        orderParam.getSourceType(),
        orderParam.getOrderStatus(),
        orderParam.getPayStatus());

        return converter.convert(repository.save(order));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeOrder(long id) {
        repository.deleteById(id);
    }
}
