-- 订单表
DROP TABLE IF EXISTS `ord_orders`;

CREATE TABLE `ord_orders` (
    `id` bigint NOT NULL COMMENT '订单ID',
    `order_no` varchar(64) NOT NULL COMMENT '订单号',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `member_username` varchar(32) NOT NULL COMMENT '用户名',
    `total_amount` int NOT NULL COMMENT '总金额',
    `pay_amount` int NOT NULL COMMENT '支付金额',
    `freight_amount` int NOT NULL DEFAULT 0 COMMENT '运费',
    `promotion_amount` int NOT NULL DEFAULT 0 COMMENT '促销优惠金额(促销价、满减、阶梯价)',
    `points_deduction_amount` int NOT NULL DEFAULT 0 COMMENT '积分抵扣金额',
    `coupon_amount` int NOT NULL DEFAULT 0 COMMENT '优惠券抵扣金额',
    `discount_amount` int NOT NULL DEFAULT 0 COMMENT '后台调整订单使用的折扣金额',
    `pay_type` tinyint NOT NULL DEFAULT 1 COMMENT '支付类型[0:货到付款,1:支付宝,2:微信,3:银联]',
    `placed` datetime COMMENT '下单时间',
    `paid` datetime COMMENT '支付时间',
    `delivered` datetime COMMENT '发货时间',
    `completed` datetime COMMENT '交易完成时间',
    `closed` datetime COMMENT '交易关闭时间',
    `delivery_company` varchar(32) NULL COMMENT '物流公司(配送方式)',
    `tracking_no` varchar(64) NULL COMMENT '物流单号',
    `auto_confirm_day` tinyint NOT NULL DEFAULT 1 COMMENT '自动确认时间(天)',
    `use_points` int NOT NULL DEFAULT 0 COMMENT '下单使用的积分',
    `gift_points` int NOT NULL DEFAULT 0 COMMENT '可以获得的积分',
    `gift_growth` int NOT NULL DEFAULT 0 COMMENT '可以获得的成长值',
    `coupon_id` bigint NULL COMMENT '使用的优惠券ID',
    `buyer_message` varchar(256) DEFAULT '' COMMENT '买家留言',
    `buyer_rate` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否评价',
    `receiver_name` varchar(32) NOT NULL COMMENT '收货人',
    `receiver_phone` varchar(16) NOT NULL COMMENT '收货人电话',
    `receiver_post_code` varchar(16) NOT NULL COMMENT '收货人邮编',
    `receiver_province` varchar(32) NOT NULL COMMENT '省份/直辖市',
    `receiver_city` varchar(32) NOT NULL COMMENT '城市',
    `receiver_region` varchar(32) NOT NULL COMMENT '区',
    `receiver_detail_address` varchar(256) NOT NULL COMMENT '收货人详细地址',
    `bill_type` tinyint NOT NULL DEFAULT 0 COMMENT '发票类型[0:不开发票,1:电子发票,2:纸质发票]',
    `bill_header` varchar(256) DEFAULT '' COMMENT '发票抬头',
    `bill_content` varchar(256) DEFAULT '' COMMENT '发票内容',
    `bill_phone` varchar(32) DEFAULT '' COMMENT '收票人电话',
    `bill_email` varchar(64) DEFAULT '' COMMENT '收票人邮箱',
    `source_type` tinyint NOT NULL DEFAULT 1 COMMENT '订单来源[1:Web,2:APP,3:微信公众号,4:微信小程序,5:H5]',
    `order_status` tinyint NOT NULL DEFAULT 0 COMMENT '订单状态[0:待付款,1:待发货,2:已发货,3:已完成,4:已关闭,5:无效订单]',
    `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态',
    `remark` varchar(256) DEFAULT '' COMMENT '订单备注',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

-- 订单明细表
DROP TABLE IF EXISTS `ord_order_items`;

CREATE TABLE `ord_order_items` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
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
    `is_return` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否退货',
    `gift_points` int NOT NULL DEFAULT 0 COMMENT '可以获得的积分',
    `gift_growth` int NOT NULL DEFAULT 0 COMMENT '可以获得的成长值',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细';

-- 订单操作历史记录表
DROP TABLE IF EXISTS `ord_order_operate_histories`;

CREATE TABLE `ord_order_operate_histories` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `operator_id` bigint NOT NULL COMMENT '操操作人[用户；系统；后台管理员]',
    `operator_name` varchar(64) NOT NULL COMMENT '操操作人[用户；系统；后台管理员]',
    `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态[0:待付款,1:待发货,2:已发货,3:已完成,4:已关闭,5:无效订单]',
    `remark` varchar(256) NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_operate_history_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单操作历史记录';


