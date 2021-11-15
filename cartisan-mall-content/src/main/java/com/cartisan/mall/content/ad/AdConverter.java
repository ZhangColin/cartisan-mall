package com.cartisan.mall.content.ad;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface AdConverter extends Converter<Ad, AdDto> {
    AdConverter CONVERTER = Mappers.getMapper(AdConverter.class);
}
