package com.cartisan.mall.goods.specification;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpecificationConverter extends Converter<Specification, SpecificationDto> {
    SpecificationConverter CONVERTER = Mappers.getMapper(SpecificationConverter.class);
}
