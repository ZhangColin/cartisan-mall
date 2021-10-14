package com.cartisan.mall.goods.template;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;

@Data
public class TemplateDto {
    @ApiModelProperty(value = "模板id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

}
