package com.cartisan.mall.content.activity;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityConverter extends Converter<Activity, ActivityDto> {
    ActivityConverter CONVERTER = Mappers.getMapper(ActivityConverter.class);
}
