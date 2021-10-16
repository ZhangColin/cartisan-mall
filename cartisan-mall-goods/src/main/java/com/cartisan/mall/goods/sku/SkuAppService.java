package com.cartisan.mall.goods.sku;

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
public class SkuAppService {
    private final SkuRepository repository;
    private final SnowflakeIdWorker idWorker;

    private final SkuConverter converter = SkuConverter.CONVERTER;

    public SkuAppService(SkuRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<SkuDto> searchSkus(@NonNull SkuQuery skuQuery, @NonNull Pageable pageable) {
        final Page<Sku> searchResult = repository.findAll(querySpecification(skuQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public SkuDto getSku(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public SkuDto addSku(SkuParam skuParam) {
        final Sku sku = new Sku(idWorker.nextId(),
        skuParam.getSpuId(),
        skuParam.getSn(),
        skuParam.getName(),
        skuParam.getSpecification(),
        skuParam.getPrice(),
        skuParam.getWeight(),
        skuParam.getQuantity(),
        skuParam.getAlertQuantity(),
        skuParam.getImage(),
        skuParam.getImages());

        return converter.convert(repository.save(sku));
    }

    @Transactional(rollbackOn = Exception.class)
    public SkuDto editSku(Long id, SkuParam skuParam) {
        final Sku sku = requirePresent(repository.findById(id));

        sku.describe(skuParam.getSpuId(),
        skuParam.getSn(),
        skuParam.getName(),
        skuParam.getSpecification(),
        skuParam.getPrice(),
        skuParam.getWeight(),
        skuParam.getQuantity(),
        skuParam.getAlertQuantity(),
        skuParam.getImage(),
        skuParam.getImages());

        return converter.convert(repository.save(sku));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSku(long id) {
        repository.deleteById(id);
    }
}
