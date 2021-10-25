package com.cartisan.mall.goods.goods.application;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.domain.Spu;
import com.cartisan.mall.goods.goods.repository.SpuRepository;
import com.cartisan.mall.goods.goods.request.SpuParam;
import com.cartisan.mall.goods.goods.request.SpuQuery;
import com.cartisan.mall.goods.goods.response.SpuConverter;
import com.cartisan.mall.goods.goods.response.SpuDto;
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
public class SpuAppService {
    private final SpuRepository repository;
    private final SnowflakeIdWorker idWorker;

    private final SpuConverter converter = SpuConverter.CONVERTER;

    public SpuAppService(SpuRepository repository, SnowflakeIdWorker idWorker) {
        this.repository = repository;
        this.idWorker = idWorker;
    }

    public PageResult<SpuDto> searchSpus(@NonNull SpuQuery spuQuery, @NonNull Pageable pageable) {
        final Page<Spu> searchResult = repository.findAll(querySpecification(spuQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public SpuDto getSpu(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpuDto addSpu(SpuParam spuParam) {
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
        spuParam.getParameterItems(),
        spuParam.getIsMarketable(),
        spuParam.getEnableSpecification(),
        spuParam.getAuditStatus());

        return converter.convert(repository.save(spu));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpuDto editSpu(Long id, SpuParam spuParam) {
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
        spuParam.getParameterItems(),
        spuParam.getIsMarketable(),
        spuParam.getEnableSpecification(),
        spuParam.getAuditStatus());

        return converter.convert(repository.save(spu));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpu(long id) {
        repository.deleteById(id);
    }
}
