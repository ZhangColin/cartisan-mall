package com.cartisan.mall.order.order;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemConverter extends Converter<OrderItem, OrderItemDto> {
    OrderItemConverter CONVERTER = Mappers.getMapper(OrderItemConverter.class);
}
