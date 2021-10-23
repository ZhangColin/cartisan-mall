package com.cartisan.mall.goods.album;

import com.cartisan.repository.Condition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class AlbumQuery {
    @ApiModelProperty(value = "相册标题")
    @Condition(propName = "title", type = Condition.Type.INNER_LIKE)
    private String title;
}
