package com.cartisan.mall.goods.parameter;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParameterConverter extends Converter<Parameter, ParameterDto> {
    ParameterConverter CONVERTER = Mappers.getMapper(ParameterConverter.class);
}
