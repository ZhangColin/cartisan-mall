-- 订单表
DROP TABLE IF EXISTS `ord_orders`;

CREATE TABLE `ord_orders` (
    `id` bigint NOT NULL COMMENT '订单ID',
    `total_amount` int NOT NULL COMMENT '总金额',
    `discount_amount` int NOT NULL COMMENT '优惠金额',
    `freight` int NOT NULL COMMENT '运费',
    `actual_amount` int NOT NULL COMMENT '实付金额',
    `pay_type` tinyint NOT NULL DEFAULT 1 COMMENT '支付类型（0:货到付款 1：在线支付）',
    `placed` datetime DEFAULT NULL COMMENT '下单时间',
    `paid` datetime DEFAULT NULL COMMENT '支付时间',
    `delivered` datetime DEFAULT NULL COMMENT '发货时间',
    `completed` datetime DEFAULT NULL COMMENT '交易完成时间',
    `closed` datetime DEFAULT NULL COMMENT '交易关闭时间',
    `shipping_name` varchar(32) NULL COMMENT '物流名称',
    `shipping_code` varchar(32) NULL COMMENT '物流单号',
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `buyer_message` varchar(1000) NULL COMMENT '买家留言',
    `buyer_rate` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否评价',
    `receiver_contact` varchar(32) NOT NULL COMMENT '收货人',
    `receiver_mobile` varchar(32) NOT NULL COMMENT '收货人手机',
    `receiver_address` varchar(256) NOT NULL COMMENT '收货人地址',
    `source_type` tinyint NOT NULL DEFAULT 1 COMMENT '订单来源（1:Web 2：APP 3：微信公众号 4：微信小程序 5：H5）',
    `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态',
    `pay_status` tinyint NOT NULL DEFAULT 1 COMMENT '支付状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

-- 订单明细表
DROP TABLE IF EXISTS `ord_order_items`;

CREATE TABLE `ord_order_items` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
    `order_id` bigint NULL COMMENT '订单ID',
    `spu_id` bigint NULL COMMENT 'SPUID',
    `sku_id` bigint NULL COMMENT 'SKUID',
    `name` varchar(100) NOT NULL COMMENT '商品名称',
    `price` int NOT NULL COMMENT '单价（分）',
    `quantity` int NOT NULL COMMENT '数量',
    `weight` int NOT NULL COMMENT '重量（克）',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `is_return` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否退货',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细';

-- -- 订单日志表
-- DROP TABLE IF EXISTS `ord_order_logs`;
--
-- CREATE TABLE `ord_order_logs` (
--     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
--     `order_id` bigint NULL COMMENT '订单ID',
--     `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态',
--     `pay_status` tinyint NOT NULL DEFAULT 1 COMMENT '支付状态',
--     `operator` varchar(32) DEFAULT '' COMMENT '操作人',
--     `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--     PRIMARY KEY (`id`) USING BTREE,
--     INDEX `index_order_log_order_id`(`order_id`) USING BTREE
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单日志表';
