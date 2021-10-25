package com.cartisan.mall.goods.goods.application;

import com.cartisan.mall.goods.brand.BrandAppService;
import com.cartisan.mall.goods.goods.domain.SkuService;
import com.cartisan.mall.goods.goods.domain.Spu;
import com.cartisan.mall.goods.goods.domain.SpuService;
import com.cartisan.mall.goods.goods.request.GoodsParam;
import com.cartisan.mall.goods.goods.response.GoodsDto;
import com.cartisan.mall.goods.goods.response.SkuConverter;
import com.cartisan.mall.goods.goods.response.SpuConverter;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author colin
 */
@Service
public class GoodsAppService {
    private final SkuConverter skuConverter = SkuConverter.CONVERTER;
    private final SpuConverter spuConverter = SpuConverter.CONVERTER;

    private final SpuService spuService;
    private final SkuService skuService;
    private final BrandAppService brandAppService;

    public GoodsAppService(SpuService spuService, SkuService skuService,
                           BrandAppService brandAppService) {
        this.spuService = spuService;
        this.skuService = skuService;
        this.brandAppService = brandAppService;
    }

    public GoodsDto getGoodsBySpuId(Long spuId) {
        final GoodsDto goodsDto = new GoodsDto();

        goodsDto.setSpu(spuConverter.convert(spuService.getSpu(spuId)));
        goodsDto.setSkus(skuConverter.convert(skuService.getSkusBySpuId(spuId)));

        return goodsDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public void addGoods(GoodsParam goodsParam) {
        final Spu spu = spuService.addSpu(goodsParam.getSpu());

        saveSkus(goodsParam, spu);

        saveBrandCategory(spu);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editGoods(Long spuId, GoodsParam goodsParam) {
        final Spu spu = spuService.editSpu(spuId, goodsParam.getSpu());

        skuService.removeBySpuId(spuId);

        saveSkus(goodsParam, spu);

        saveBrandCategory(spu);
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

    private void saveSkus(GoodsParam goodsParam, Spu spu) {
        goodsParam.getSkus().forEach(skuParam -> {
            skuParam.setSpuId(spu.getId());

            String name = spu.getName();
            final Gson gson = new Gson();

            if (StringUtils.isEmpty(skuParam.getSpecification())) {
                skuParam.setSpecification("{}");
            }

            final Map<String, String> specificationMap = gson.fromJson(skuParam.getSpecification(), Map.class);
            name += " " + String.join(" ", specificationMap.values());
            skuParam.setName(name);

            skuService.addSku(skuParam);
        });
    }
}
