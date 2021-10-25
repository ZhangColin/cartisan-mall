package com.cartisan.mall.goods.goods.response;

import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class GoodsDto {
    private SpuDto spu;
    private List<SkuDto> skus;
}
