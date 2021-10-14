package com.cartisan.mall.goods.album;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;

@Data
public class AlbumDto {
    @ApiModelProperty(value = "相册id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "封面")
    private String image;

    @ApiModelProperty(value = "图片列表")
    private String imageItems;

}
