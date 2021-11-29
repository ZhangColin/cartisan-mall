-- 库存表
DROP TABLE IF EXISTS `whs_sku_stocks`;

CREATE TABLE `whs_sku_stocks` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
    `sku_id` bigint NOT NULL COMMENT '商品ID',
    `sku_name` varchar(64) NOT NULL COMMENT '商品名称',
    `stock` int NOT NULL DEFAULT 0 COMMENT '库存数',
    `stock_locked` int NOT NULL DEFAULT 0 COMMENT '锁定库存',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_sku_stock_sku_id`(`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存';
