package com.cartisan.mall.goods.goods.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.goods.domain.SpuLog;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpuLogConverter extends Converter<SpuLog, SpuLogDto> {
    SpuLogConverter CONVERTER = Mappers.getMapper(SpuLogConverter.class);

    @Override
    @InheritConfiguration
    @Mapping(source = "createDateTime", target = "operateDate")
    SpuLogDto convert(SpuLog spuLog);
}
