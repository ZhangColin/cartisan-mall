package com.cartisan.mall.goods.template;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateConverter extends Converter<Template, TemplateDto> {
    TemplateConverter CONVERTER = Mappers.getMapper(TemplateConverter.class);
}
