package com.cartisan.mall.order.returnorder;

import com.cartisan.dto.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

@Service
public class ReturnOrderItemAppService {
    private final ReturnOrderItemRepository repository;

    private final ReturnOrderItemConverter converter = ReturnOrderItemConverter.CONVERTER;

    public ReturnOrderItemAppService(ReturnOrderItemRepository repository) {
        this.repository = repository;
    }

    public PageResult<ReturnOrderItemDto> searchReturnOrderItems(@NonNull ReturnOrderItemQuery returnOrderItemQuery, @NonNull Pageable pageable) {
        final Page<ReturnOrderItem> searchResult = repository.findAll(querySpecification(returnOrderItemQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ReturnOrderItemDto getReturnOrderItem(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnOrderItemDto addReturnOrderItem(ReturnOrderItemParam returnOrderItemParam) {
        final ReturnOrderItem returnOrderItem = new ReturnOrderItem(returnOrderItemParam.getReturnOrderId(),
        returnOrderItemParam.getOrderId(),
        returnOrderItemParam.getOrderItemId(),
        returnOrderItemParam.getSpuId(),
        returnOrderItemParam.getSkuId(),
        returnOrderItemParam.getName(),
        returnOrderItemParam.getPrice(),
        returnOrderItemParam.getQuantity(),
        returnOrderItemParam.getWeight(),
        returnOrderItemParam.getImage());

        return converter.convert(repository.save(returnOrderItem));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnOrderItemDto editReturnOrderItem(Long id, ReturnOrderItemParam returnOrderItemParam) {
        final ReturnOrderItem returnOrderItem = requirePresent(repository.findById(id));

        returnOrderItem.describe(returnOrderItemParam.getReturnOrderId(),
        returnOrderItemParam.getOrderId(),
        returnOrderItemParam.getOrderItemId(),
        returnOrderItemParam.getSpuId(),
        returnOrderItemParam.getSkuId(),
        returnOrderItemParam.getName(),
        returnOrderItemParam.getPrice(),
        returnOrderItemParam.getQuantity(),
        returnOrderItemParam.getWeight(),
        returnOrderItemParam.getImage());

        return converter.convert(repository.save(returnOrderItem));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeReturnOrderItem(long id) {
        repository.deleteById(id);
    }
}
