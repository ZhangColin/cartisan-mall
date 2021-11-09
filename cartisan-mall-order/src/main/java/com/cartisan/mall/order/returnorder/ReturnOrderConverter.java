package com.cartisan.mall.order.returnorder;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReturnOrderConverter extends Converter<ReturnOrder, ReturnOrderDto> {
    ReturnOrderConverter CONVERTER = Mappers.getMapper(ReturnOrderConverter.class);
}
