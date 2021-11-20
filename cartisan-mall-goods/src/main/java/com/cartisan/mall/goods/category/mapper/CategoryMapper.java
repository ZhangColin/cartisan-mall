package com.cartisan.mall.goods.category.mapper;

import com.cartisan.dto.TreeNode;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface CategoryMapper {
    @Select("select id, name, parent_id \n" +
            "from gds_categories \n" +
            "order by sequence asc")
    List<TreeNode> getCategoryTree();
}
