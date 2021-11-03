package com.cartisan.mall.goods.goods.domain;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.repository.SpuRepository;
import com.cartisan.mall.goods.goods.request.SpuParam;
import com.cartisan.mall.goods.goods.request.SpuQuery;
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
public class SpuService {
    private final SpuRepository repository;
    private final SnowflakeIdWorker idWorker;

    public SpuService(SpuRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<Spu> searchSpus(@NonNull SpuQuery spuQuery, @NonNull Pageable pageable) {
        final Page<Spu> searchResult = repository.findAll(querySpecification(spuQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.getContent());
    }

    public Spu getSpu(Long id) {
        return requirePresent(repository.findById(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public Spu addSpu(SpuParam spuParam) {
        final Spu spu = new Spu(idWorker.nextId(),
                spuParam.getSn(),
                spuParam.getName(),
                spuParam.getCaption(),
                spuParam.getBrandId(),
                spuParam.getCategory1Id(),
                spuParam.getCategory2Id(),
                spuParam.getCategory3Id(),
                spuParam.getTemplateId(),
                spuParam.getImage(),
                spuParam.getImages(),
                spuParam.getSaleService(),
                spuParam.getIntroduction(),
                spuParam.getSpecificationItems(),
                spuParam.getParameterItems());

        return repository.save(spu);
    }

    @Transactional(rollbackOn = Exception.class)
    public Spu editSpu(Long id, SpuParam spuParam) {
        final Spu spu = requirePresent(repository.findById(id));

        spu.describe(spuParam.getSn(),
                spuParam.getName(),
                spuParam.getCaption(),
                spuParam.getBrandId(),
                spuParam.getCategory1Id(),
                spuParam.getCategory2Id(),
                spuParam.getCategory3Id(),
                spuParam.getTemplateId(),
                spuParam.getImage(),
                spuParam.getImages(),
                spuParam.getSaleService(),
                spuParam.getIntroduction(),
                spuParam.getSpecificationItems(),
                spuParam.getParameterItems());

        return repository.save(spu);

        // Todo: Save edit log
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpu(long id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void audit(Long spuId, Integer status, String message) {
        final Spu spu = requirePresent(repository.findById(spuId));

        spu.setAuditStatus(status);

        repository.save(spu);

        // TODO: Sava audit log
    }

    @Transactional(rollbackOn = Exception.class)
    public void pull(Long spuId) {
        final Spu spu = requirePresent(repository.findById(spuId));

        spu.setIsMarketable(false);

        repository.save(spu);
        // Todo: Save edit log
    }

    @Transactional(rollbackOn = Exception.class)
    public void put(Long spuId) {
        final Spu spu = requirePresent(repository.findById(spuId));

        if (spu.getAuditStatus() != 1) {
            throw new RuntimeException("此商品未通过审核。");
        }
        spu.setIsMarketable(true);

        repository.save(spu);
        // Todo: Save edit log
    }
}
