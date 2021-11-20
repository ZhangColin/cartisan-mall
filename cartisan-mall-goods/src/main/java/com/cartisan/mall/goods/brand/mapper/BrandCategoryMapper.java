package com.cartisan.mall.goods.brand.mapper;

import com.cartisan.mall.goods.brand.response.BrandCategoryDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface BrandCategoryMapper {
    @Select("select gbc.brand_id, gb.name as brand_name, gbc.category_id, gc.name as category_name\n" +
            "from gds_brand_categories as gbc\n" +
            "    inner join gds_brands gb on gbc.brand_id = gb.id\n" +
            "    inner join gds_categories gc on gbc.category_id = gc.id\n" +
            "where gbc.brand_id=${brandId}")
    List<BrandCategoryDto> getByBrand(Long brandId);

    @Select("select gbc.brand_id, gb.name as brand_name, gbc.category_id, gc.name as category_name\n" +
            "from gds_brand_categories as gbc\n" +
            "    inner join gds_brands gb on gbc.brand_id = gb.id\n" +
            "    inner join gds_categories gc on gbc.category_id = gc.id\n" +
            "where gbc.category_id=${categoryId}")
    List<BrandCategoryDto> getByCategory(Long categoryId);
}
