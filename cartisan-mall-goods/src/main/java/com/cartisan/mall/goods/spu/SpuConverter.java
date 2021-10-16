package com.cartisan.mall.goods.spu;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpuConverter extends Converter<Spu, SpuDto> {
    SpuConverter CONVERTER = Mappers.getMapper(SpuConverter.class);
}
