-- 出库单表
DROP TABLE IF EXISTS `whs_delivery_orders`;

CREATE TABLE `whs_delivery_orders` (
    `id` bigint NOT NULL COMMENT '出库单ID',
    `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `order_sn` varchar(64) NOT NULL COMMENT '订单号',
    `consignee` varchar(32) NOT NULL COMMENT '收货人',
    `consignee_phone` varchar(16) NOT NULL COMMENT '收货人电话',
    `delivery_address` varchar(128) NOT NULL COMMENT '配送地址',
    `payment_way` tinyint NOT NULL DEFAULT 0 COMMENT '付款方式[1:在线付款,2:货到付款]',
    `order_remark` varchar(256) DEFAULT '' COMMENT '订单备注',
    `order_body` varchar(256) DEFAULT '' COMMENT '订单描述',
    `tracking_no` varchar(64) DEFAULT '' COMMENT '物流单号',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_delivery_order_order_id`(`order_id`) USING BTREE,
    INDEX `index_delivery_order_warehouse_id`(`warehouse_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单';

-- 出库单明细表
DROP TABLE IF EXISTS `whs_delivery_order_items`;

CREATE TABLE `whs_delivery_order_items` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '出库单明细ID',
    `delivery_order_id` bigint NOT NULL COMMENT '出库单ID',
    `sku_id` bigint NOT NULL COMMENT '商品ID',
    `sku_name` varchar(64) NOT NULL COMMENT '商品名称',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '数量',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态[1:已锁定,2:已解锁,3:已扣减]',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_delivery_order_item_delivery_order_id`(`delivery_order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单明细';
