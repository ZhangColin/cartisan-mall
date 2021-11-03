package com.cartisan.mall.goods.goods.application;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.brand.BrandAppService;
import com.cartisan.mall.goods.goods.domain.SkuService;
import com.cartisan.mall.goods.goods.domain.SpecificationsConverter;
import com.cartisan.mall.goods.goods.domain.Spu;
import com.cartisan.mall.goods.goods.domain.SpuService;
import com.cartisan.mall.goods.goods.mapper.SpuMapper;
import com.cartisan.mall.goods.goods.request.SkuParam;
import com.cartisan.mall.goods.goods.request.SpuParam;
import com.cartisan.mall.goods.goods.request.SpuQuery;
import com.cartisan.mall.goods.goods.response.SpuDetailConverter;
import com.cartisan.mall.goods.goods.response.SpuDetailDto;
import com.cartisan.mall.goods.goods.response.SpuDto;
import com.cartisan.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author colin
 */
@Service
public class SpuAppService {
    private final SnowflakeIdWorker idWorker;
    private final SpuMapper mapper;
    private final SpuService spuService;
    private final SkuService skuService;
    private final BrandAppService brandAppService;

    private final SpuDetailConverter converter = SpuDetailConverter.CONVERTER;

    public SpuAppService(SnowflakeIdWorker idWorker, SpuMapper mapper,
                         SpuService spuService, SkuService skuService, BrandAppService brandAppService) {
        this.idWorker = idWorker;
        this.mapper = mapper;
        this.spuService = spuService;
        this.skuService = skuService;
        this.brandAppService = brandAppService;
    }

    public PageResult<SpuDto> searchSpus(@NonNull SpuQuery spuQuery, @NonNull Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        final List<SpuDto> spus = mapper.searchSpus(spuQuery);

        PageInfo<SpuDto> pageSpus = new PageInfo<>(spus);

        return new PageResult<>(pageSpus.getTotal(), pageSpus.getPages(), pageSpus.getList());
    }

    public SpuDetailDto getSpu(Long id) {
        return converter.convert(spuService.getSpu(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpuDetailDto addSpu(SpuParam spuParam) {
        final Spu spu = spuService.addSpu(spuParam);

        saveSkus(spu);

        saveBrandCategory(spu);

        return converter.convert(spu);
    }

    @Transactional(rollbackOn = Exception.class)
    public SpuDetailDto editSpu(Long id, SpuParam spuParam) {
        final Spu spu = spuService.editSpu(id, spuParam);

        skuService.removeBySpuId(id);

        saveSkus(spu);

        saveBrandCategory(spu);

        return converter.convert(spu);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpu(long id) {
        spuService.removeSpu(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void audit(Long spuId, Integer status, String message) {
        spuService.audit(spuId, status, message);
        if (status == 1) {
            spuService.put(spuId);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void pull(Long spuId) {
        spuService.pull(spuId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void put(Long spuId) {
        spuService.put(spuId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void pullMany(List<Long> spuIds) {
        // TODO: 使用 SQL 批量更新
        spuIds.forEach(spuService::pull);
    }

    @Transactional(rollbackOn = Exception.class)
    public void putMany(List<Long> spuIds) {
        // TODO: 使用 SQL 批量更新
        spuIds.forEach(spuId->{
            try {
                spuService.put(spuId);
            }finally {

            }
        });
    }

    private void saveBrandCategory(Spu spu) {
        brandAppService.addCategory(spu.getBrandId(), spu.getCategory3Id());
    }

    private void saveSkus(Spu spu) {
        final Gson gson = new Gson();
        final SpecificationsConverter specificationsConverter = new SpecificationsConverter();

        final List<String> skuSpecifications = specificationsConverter.convert(spu.getSpecificationItems());

        skuSpecifications.forEach(specification-> {
            final SkuParam skuParam = new SkuParam();
            skuParam.setSpecification(specification);
            skuParam.setSpuId(spu.getId());

            String name = spu.getName();
            if (!specification.equals("{}")) {
                final Map<String, String> specificationMap = gson.fromJson(skuParam.getSpecification(), Map.class);
//                name += " " + String.join(" ", specificationMap.values());

                final Set<String> keys = specificationMap.keySet();
                for (Map.Entry<String, String> entry: specificationMap.entrySet()) {
                    name = name.replace("{" + entry.getKey() + "}", entry.getValue());
                }

            }

            skuParam.setSn(spu.getSn());
            skuParam.setName(name);
            skuParam.setQuantity(100);
            skuParam.setWeight(10);
            skuParam.setPrice(100);
            skuParam.setAlertQuantity(10);
            skuService.addSku(skuParam);
        });

    }
}
