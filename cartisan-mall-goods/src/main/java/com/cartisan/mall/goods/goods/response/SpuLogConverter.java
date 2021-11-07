package com.cartisan.mall.goods.goods.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.goods.domain.SpuLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpuLogConverter extends Converter<SpuLog, SpuLogDto> {
    SpuLogConverter CONVERTER = Mappers.getMapper(SpuLogConverter.class);
}
