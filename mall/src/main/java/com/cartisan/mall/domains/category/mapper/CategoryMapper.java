package com.cartisan.mall.domains.category.mapper;

import com.cartisan.mall.domains.category.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author colin
 */
public interface CategoryMapper {
    @Select("select * from mall_category where id = #{id}")
    Category findById(@Param("id") Long id);

    Category queryById(Integer id);
}
