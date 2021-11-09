-- 退货退款订单表
DROP TABLE IF EXISTS `ord_return_orders`;

CREATE TABLE `ord_return_orders` (
    `id` bigint NOT NULL COMMENT '订单Id',
    `order_id` bigint NULL COMMENT '订单Id',
    `applied` datetime DEFAULT NULL COMMENT '申请时间',
    `user_id` bigint NULL COMMENT '用户Id',
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `contract` varchar(32) NOT NULL COMMENT '联系人',
    `contract_mobile` varchar(16) NOT NULL COMMENT '联系人手机',
    `type` tinyint NOT NULL DEFAULT 1 COMMENT '类型（1:退货 2：退款）',
    `refund_amount` int NOT NULL COMMENT '退款金额',
    `refund_freight` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否退运费',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0:申请 1：同意 2：驳回）',
    `processed` datetime DEFAULT NULL COMMENT '处理时间',
    `return_cause` bigint NOT NULL COMMENT '退货退款原因',
    `evidence` varchar(1000) NULL COMMENT '凭证图片',
    `description` varchar(1000) NULL COMMENT '问题描述',
    `remark` varchar(1000) NULL COMMENT '处理备注',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货退款订单';

-- 退货退款订单明细表
DROP TABLE IF EXISTS `ord_return_order_items`;

CREATE TABLE `ord_return_order_items` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细Id',
    `return_order_id` bigint NULL COMMENT '退货退款订单Id',
    `order_id` bigint NULL COMMENT '订单Id',
    `order_item_id` bigint NULL COMMENT '订单明细Id',
    `spu_id` bigint NULL COMMENT 'SPUId',
    `sku_id` bigint NULL COMMENT 'SKUId',
    `name` varchar(100) NOT NULL COMMENT '商品名称',
    `price` int NOT NULL COMMENT '单价（分）',
    `quantity` int NOT NULL COMMENT '数量',
    `weight` int NOT NULL COMMENT '重量（克）',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货退款订单明细';

-- 退货原因表
DROP TABLE IF EXISTS `ord_return_causes`;

CREATE TABLE `ord_return_causes` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '退货原因Id',
    `cause` varchar(100) NOT NULL COMMENT '原因',
    `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货原因表';
