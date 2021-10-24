package com.cartisan.mall.goods.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;

/**
 * @author colin
 */
@Data
public class TemplateDto {
    @ApiModelProperty(value = "模板Id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "规格数量")
    private Integer specifications;

    @ApiModelProperty(value = "参数数量")
    private Integer parameters;
}
