package com.cartisan.mall.system.resource.response;

import com.cartisan.CartisanContext;
import com.cartisan.dto.Converter;
import com.cartisan.mall.system.resource.application.ResourceCategoryAppService;
import com.cartisan.mall.system.resource.domain.Resource;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Mapper
public interface ResourceConverter extends Converter<Resource, ResourceDto> {
    ResourceConverter CONVERTER = Mappers.getMapper(ResourceConverter.class);
    String ERR_CATEGORY_NOT_EXISTS = "资源分类不存在";

    @Override
    @InheritConfiguration
    @Mapping(source = "categoryId", target = "category")
    ResourceDto convert(Resource resource);

    default ResourceCategoryDto categoryId2CategoryDto(Long categoryId) {
        final ResourceCategoryAppService resourceCategoryAppService =
                CartisanContext.getBean(ResourceCategoryAppService.class);

        return requirePresent(resourceCategoryAppService.getAllResourceCategories().stream()
                .filter(resourceCategoryDto -> resourceCategoryDto.getId().equals(categoryId.toString()))
                .findFirst(), ERR_CATEGORY_NOT_EXISTS);
    }
}