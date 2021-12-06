-- 商品成长设置表
DROP TABLE IF EXISTS `mkt_product_bounds`;

CREATE TABLE `mkt_product_bounds` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sku_id` bigint NOT NULL COMMENT '商品ID',
    `grow_bounds` int NOT NULL COMMENT '成长值',
    `buy_bounds` int NOT NULL COMMENT '积分',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_price_sku_id`(`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品成长设置';
