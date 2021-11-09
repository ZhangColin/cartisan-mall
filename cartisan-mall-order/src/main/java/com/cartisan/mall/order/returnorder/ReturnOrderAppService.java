package com.cartisan.mall.order.returnorder;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cartisan.util.SnowflakeIdWorker;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class ReturnOrderAppService {
    private final ReturnOrderRepository repository;
    private final SnowflakeIdWorker idWorker;

    private final ReturnOrderConverter converter = ReturnOrderConverter.CONVERTER;

    public ReturnOrderAppService(ReturnOrderRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<ReturnOrderDto> searchReturnOrders(@NonNull ReturnOrderQuery returnOrderQuery, @NonNull Pageable pageable) {
        final Page<ReturnOrder> searchResult = repository.findAll(querySpecification(returnOrderQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ReturnOrderDto getReturnOrder(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnOrderDto addReturnOrder(ReturnOrderParam returnOrderParam) {
        final ReturnOrder returnOrder = new ReturnOrder(idWorker.nextId(),
        returnOrderParam.getOrderId(),
        returnOrderParam.getApplied(),
        returnOrderParam.getUserId(),
        returnOrderParam.getUsername(),
        returnOrderParam.getContract(),
        returnOrderParam.getContractMobile(),
        returnOrderParam.getType(),
        returnOrderParam.getRefundAmount(),
        returnOrderParam.getRefundFreight(),
        returnOrderParam.getStatus(),
        returnOrderParam.getProcessed(),
        returnOrderParam.getReturnCause(),
        returnOrderParam.getEvidence(),
        returnOrderParam.getDescription(),
        returnOrderParam.getRemark());

        return converter.convert(repository.save(returnOrder));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnOrderDto editReturnOrder(Long id, ReturnOrderParam returnOrderParam) {
        final ReturnOrder returnOrder = requirePresent(repository.findById(id));

        returnOrder.describe(returnOrderParam.getOrderId(),
        returnOrderParam.getApplied(),
        returnOrderParam.getUserId(),
        returnOrderParam.getUsername(),
        returnOrderParam.getContract(),
        returnOrderParam.getContractMobile(),
        returnOrderParam.getType(),
        returnOrderParam.getRefundAmount(),
        returnOrderParam.getRefundFreight(),
        returnOrderParam.getStatus(),
        returnOrderParam.getProcessed(),
        returnOrderParam.getReturnCause(),
        returnOrderParam.getEvidence(),
        returnOrderParam.getDescription(),
        returnOrderParam.getRemark());

        return converter.convert(repository.save(returnOrder));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeReturnOrder(long id) {
        repository.deleteById(id);
    }
}
