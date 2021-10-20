package com.cartisan.mall.goods.category;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface CategoryConverter extends Converter<Category, CategoryDto> {
    CategoryConverter CONVERTER = Mappers.getMapper(CategoryConverter.class);
}
