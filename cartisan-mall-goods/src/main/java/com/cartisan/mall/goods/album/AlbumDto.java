package com.cartisan.mall.goods.album;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class AlbumDto {
    @ApiModelProperty(value = "相册Id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面")
    private String coverImage;

}
