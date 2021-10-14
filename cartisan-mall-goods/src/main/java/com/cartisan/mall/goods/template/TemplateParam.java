package com.cartisan.mall.goods.template;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.lang.String;

@Data
public class TemplateParam {
    @ApiModelProperty(value = "名称")
    private String name;

}
