package com.cartisan.mall.goods.goods.request;

import lombok.Data;

/**
 * @author colin
 */
@Data
public class SpuQuery {
    private String nameOrSn;
    private Long category1Id;
    private Long category2Id;
    private Long category3Id;
    private Long brandId;
}
