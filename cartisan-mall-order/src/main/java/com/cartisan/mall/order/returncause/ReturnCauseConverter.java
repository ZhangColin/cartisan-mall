package com.cartisan.mall.order.returncause;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface ReturnCauseConverter extends Converter<ReturnCause, ReturnCauseDto> {
    ReturnCauseConverter CONVERTER = Mappers.getMapper(ReturnCauseConverter.class);
}
