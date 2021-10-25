package com.cartisan.mall.goods.goods.request;

import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class GoodsParam {
    private SpuParam spu;
    private List<SkuParam> skus;
}
