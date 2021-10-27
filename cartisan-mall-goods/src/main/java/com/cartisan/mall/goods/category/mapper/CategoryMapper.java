package com.cartisan.mall.goods.category.mapper;

import com.cartisan.dp.IdName;
import com.cartisan.mall.goods.category.CategoryDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface CategoryMapper {
    @Select("select gc.id, gc.parent_id, gc.name, gc.is_show, gc.is_menu, gc.template_id, gt.name as template_name, gc.sequence \n" +
            "from gds_categories as gc\n" +
            "    left join gds_templates gt on gc.template_id = gt.id\n" +
            "where gc.parent_id = ${parentId}\n" +
            "order by gc.sequence asc")
    List<CategoryDto> searchCategories(@Param(value = "parentId") Long parentId);

    @Select("select id, name \n" +
            "from gds_categories \n" +
            "where parent_id = ${parentId}\n" +
            "order by sequence asc")
    List<IdName<Long, String>> getCategoryIdNames(@Param(value = "parentId") Long parentId);
}
