package com.cartisan.mall.goods.goods.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.goods.domain.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SkuConverter extends Converter<Sku, SkuDto> {
    SkuConverter CONVERTER = Mappers.getMapper(SkuConverter.class);
}
