package com.cartisan.mall.goods.template.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.template.domain.Parameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface ParameterConverter extends Converter<Parameter, ParameterDto> {
    ParameterConverter CONVERTER = Mappers.getMapper(ParameterConverter.class);
}
