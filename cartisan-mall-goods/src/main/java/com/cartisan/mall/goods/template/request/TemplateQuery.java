package com.cartisan.mall.goods.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class TemplateQuery {
    @ApiModelProperty(value = "名称")
    private String name;
}
