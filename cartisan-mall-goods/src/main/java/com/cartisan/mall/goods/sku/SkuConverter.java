package com.cartisan.mall.goods.sku;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkuConverter extends Converter<Sku, SkuDto> {
    SkuConverter CONVERTER = Mappers.getMapper(SkuConverter.class);
}
