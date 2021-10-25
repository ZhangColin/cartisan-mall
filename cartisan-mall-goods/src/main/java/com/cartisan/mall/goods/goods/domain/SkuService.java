package com.cartisan.mall.goods.goods.domain;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.repository.SkuRepository;
import com.cartisan.mall.goods.goods.request.SkuParam;
import com.cartisan.mall.goods.goods.request.SkuQuery;
import com.cartisan.util.SnowflakeIdWorker;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class SkuService {
    private final SkuRepository repository;
    private final SnowflakeIdWorker idWorker;

    public SkuService(SkuRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<Sku> searchSkus(@NonNull SkuQuery skuQuery, @NonNull Pageable pageable) {
        final Page<Sku> searchResult = repository.findAll(querySpecification(skuQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.getContent());
    }

    public List<Sku> getSkusBySpuId(Long spuId) {
        return repository.findBySpuId(spuId);
    }

    public Sku getSku(Long id) {
        return requirePresent(repository.findById(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public Sku addSku(SkuParam skuParam) {
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

        return repository.save(sku);
    }

    @Transactional(rollbackOn = Exception.class)
    public Sku editSku(Long id, SkuParam skuParam) {
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

        return repository.save(sku);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSku(long id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeBySpuId(Long spuId) {
        repository.deleteBySpuId(spuId);
    }
}
