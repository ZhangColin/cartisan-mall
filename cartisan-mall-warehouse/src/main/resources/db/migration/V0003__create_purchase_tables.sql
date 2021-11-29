-- 采购单表
DROP TABLE IF EXISTS `whs_purchase_orders`;

CREATE TABLE `whs_purchase_orders` (
    `id` bigint NOT NULL COMMENT '采购单ID',
    `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
    `assignee_id` bigint NOT NULL COMMENT '采购人ID',
    `assignee_name` varchar(64) NOT NULL COMMENT '采购人',
    `phone` varchar(16) NOT NULL COMMENT '电话',
    `amount` int NOT NULL DEFAULT 0 COMMENT '总金额',
    `priority` tinyint NOT NULL DEFAULT 0 COMMENT '优先级',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_purchase_order_warehouse_id`(`warehouse_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单';

-- 采购请求表
DROP TABLE IF EXISTS `whs_purchase_requests`;

CREATE TABLE `whs_purchase_requests` (
    `id` bigint NOT NULL COMMENT '采购请求ID',
    `purchase_order_id` bigint NULL COMMENT '采购单ID',
    `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
    `sku_id` bigint NOT NULL COMMENT '采购商品ID',
    `sku_name` varchar(64) NOT NULL COMMENT '商品名称',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '数量',
    `price` int NOT NULL DEFAULT 0 COMMENT '采购价',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态[0:新建,1:已分配,2:正在采购,3:已完成,4:采购失败]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_purchase_request_sku_id`(`sku_id`) USING BTREE,
    INDEX `index_purchase_request_purchase_order_id`(`purchase_order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购请求';
