package com.cartisan.mall.goods.brand;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface BrandConverter extends Converter<Brand, BrandDto> {
    BrandConverter CONVERTER = Mappers.getMapper(BrandConverter.class);
}
