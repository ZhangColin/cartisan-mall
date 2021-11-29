-- 退货退款订单表
DROP TABLE IF EXISTS `ord_return_orders`;

CREATE TABLE `ord_return_orders` (
    `id` bigint NOT NULL COMMENT '订单ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `applied` datetime NOT NULL COMMENT '申请时间',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `member_username` varchar(32) NOT NULL COMMENT '用户名称',
    `return_name` varchar(32) NOT NULL COMMENT '退货人姓名',
    `return_phone` varchar(16) NOT NULL COMMENT '联系人电话',
    `type` tinyint NOT NULL DEFAULT 1 COMMENT '类型（1:退货 2：退款）',
    `refund_amount` int NOT NULL COMMENT '退款金额',
    `refund_freight` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否退运费',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0:申请 1：同意 2：驳回）',
    `processed` datetime COMMENT '处理时间',
    `reason` bigint NOT NULL COMMENT '退货退款原因',
    `description` varchar(512) DEFAULT '' COMMENT '问题描述',
    `evidence` varchar(512) DEFAULT '' COMMENT '凭证图片',
    `remark` varchar(512) DEFAULT '' COMMENT '处理备注',
    `operator_id` bigint NULL COMMENT '操操作人[用户；系统；后台管理员]',
    `operator_name` varchar(32) DEFAULT '' COMMENT '操操作人[用户；系统；后台管理员]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_return_order_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货退款订单';

-- 退货退款订单明细表
DROP TABLE IF EXISTS `ord_return_order_items`;

CREATE TABLE `ord_return_order_items` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
    `return_order_id` bigint NOT NULL COMMENT '退货退款订单ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `order_item_id` bigint NULL COMMENT '订单明细ID',
    `spu_id` bigint NOT NULL COMMENT 'SPUID',
    `spu_name` varchar(64) NOT NULL COMMENT 'SPU名称',
    `spu_image` varchar(256) NOT NULL COMMENT 'SPU图片',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `sku_name` varchar(64) NOT NULL COMMENT '商品名称',
    `sku_image` varchar(256) NOT NULL COMMENT '商品图片',
    `price` int NOT NULL COMMENT '单价（分）',
    `quantity` int NOT NULL COMMENT '数量',
    `weight` int NOT NULL COMMENT '重量（克）',
    `sku_attrs_values` varchar(512) DEFAULT '' COMMENT '商品销售属性组合（JSON）',
    `promotion_amount` int NOT NULL DEFAULT 0 COMMENT '促销优惠金额(促销价、满减、阶梯价)',
    `points_deduction_amount` int NOT NULL DEFAULT 0 COMMENT '积分抵扣金额',
    `coupon_amount` int NOT NULL DEFAULT 0 COMMENT '优惠券抵扣金额',
    `pay_amount` int NOT NULL DEFAULT 0 COMMENT '付款金额',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_return_order_item_return_order_id`(`return_order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货退款订单明细';

-- 退货原因表
DROP TABLE IF EXISTS `ord_return_reasons`;

CREATE TABLE `ord_return_reasons` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '退货原因ID',
    `reason` varchar(32) NOT NULL COMMENT '原因',
    `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货原因';
