package com.cartisan.mall.goods.brand.request;

import com.cartisan.mall.goods.brand.BrandVerify;
import com.cartisan.mall.goods.category.CategoryVerify;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class BuildBrandCategoryRelationCommand {
    @ApiModelProperty(value = "品牌Id")
    @NotNull(message = "品牌不能为空")
    @BrandVerify
    private Long brandId;

    @ApiModelProperty(value = "分类Id")
    @NotNull(message = "分类不能为空")
    @CategoryVerify
    private Long categoryId;
}
