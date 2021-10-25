package com.cartisan.mall.goods.goods.application;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.domain.Sku;
import com.cartisan.mall.goods.goods.domain.SkuService;
import com.cartisan.mall.goods.goods.request.SkuParam;
import com.cartisan.mall.goods.goods.request.SkuQuery;
import com.cartisan.mall.goods.goods.response.SkuConverter;
import com.cartisan.mall.goods.goods.response.SkuDto;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author colin
 */
@Service
public class SkuAppService {
    private final SkuConverter converter = SkuConverter.CONVERTER;
    private final SkuService skuService;

    public SkuAppService(SkuService skuService) {
        this.skuService = skuService;
    }

    public PageResult<SkuDto> searchSkus(@NonNull SkuQuery skuQuery, @NonNull Pageable pageable) {
        final PageResult<Sku> searchResult = skuService.searchSkus(skuQuery, pageable);

        return new PageResult<>(searchResult.getTotal(), searchResult.getPages(),
                converter.convert(searchResult.getRows()));
    }

    public SkuDto getSku(Long id) {
        return converter.convert(skuService.getSku(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public SkuDto addSku(SkuParam skuParam) {
        return converter.convert(skuService.addSku(skuParam));
    }

    @Transactional(rollbackOn = Exception.class)
    public SkuDto editSku(Long id, SkuParam skuParam) {
        return converter.convert(skuService.editSku(id, skuParam));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSku(long id) {
        skuService.removeSku(id);
    }
}
