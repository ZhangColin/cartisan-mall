package com.cartisan.mall.goods.goods.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.goods.domain.Spu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpuDetailConverter extends Converter<Spu, SpuDetailDto> {
    SpuDetailConverter CONVERTER = Mappers.getMapper(SpuDetailConverter.class);
}
