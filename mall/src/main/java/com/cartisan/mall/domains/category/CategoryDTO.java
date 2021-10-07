package com.cartisan.mall.domains.category;

import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class CategoryDTO  {
    private Long id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryDTO> subCategories;
}
