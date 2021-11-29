-- 商品成长设置表
DROP TABLE IF EXISTS `mkt_spu_bounds`;

CREATE TABLE `mkt_member_prices` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `spu_id` bigint NULL COMMENT 'SPUID',
    `grow_bounds` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '成长值',
    `buy_bounds` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '积分',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品成长设置';
