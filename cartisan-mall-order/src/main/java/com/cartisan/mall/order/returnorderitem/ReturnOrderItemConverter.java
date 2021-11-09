package com.cartisan.mall.order.returnorderitem;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReturnOrderItemConverter extends Converter<ReturnOrderItem, ReturnOrderItemDto> {
    ReturnOrderItemConverter CONVERTER = Mappers.getMapper(ReturnOrderItemConverter.class);
}
