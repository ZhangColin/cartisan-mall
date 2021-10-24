package com.cartisan.mall.goods.template.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.template.domain.Specification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpecificationConverter extends Converter<Specification, SpecificationDto> {
    SpecificationConverter CONVERTER = Mappers.getMapper(SpecificationConverter.class);
}
