-- 优惠券表
DROP TABLE IF EXISTS `mkt_coupons`;

CREATE TABLE `mkt_coupons` (
    `id` bigint NOT NULL COMMENT '优惠券ID',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '优惠券类型[0:全场赠券,1:会员赠券,2:购物赠券,3:注册赠券]',
    `code` varchar(64) NOT NULL COMMENT '优惠码',
    `name` varchar(32) NOT NULL COMMENT '名称',
    `image` varchar(32) DEFAULT '' COMMENT '图片',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '数量',
    `amount` int NOT NULL DEFAULT 0 COMMENT '金额',
    `per_limit` int NOT NULL COMMENT '每人限领张数',
    `min_point` int NOT NULL DEFAULT 0 COMMENT '使用门槛（0表示无门槛）',
    `start` datetime COMMENT '开始时间',
    `end` datetime COMMENT '结束时间',
    `use_type` tinyint NOT NULL DEFAULT 0 COMMENT '使用类型[0:全场能用,1:指定分类,2:指定商品]',
    `platform` tinyint NOT NULL DEFAULT 0 COMMENT '使用平台[0:全部,1:移动,2:PC]',
    `publish_count` int NOT NULL DEFAULT 0 COMMENT '发行数量',
    `use_count` int NOT NULL DEFAULT 0 COMMENT '已使用数量',
    `receive_count` int NOT NULL DEFAULT 0 COMMENT '领取数量',
    `receive_start` datetime NOT NULL COMMENT '领取开始时间',
    `receive_end` datetime NOT NULL COMMENT '领取结束时间',
    `member_level` tinyint NOT NULL DEFAULT 0 COMMENT '可以领取的会员等级[0:不限等级,其它:对应等级]',
    `publish_status` tinyint NOT NULL DEFAULT 0 COMMENT '发布状态[0:未发布,1:已发布]',
    `note` varchar(256) DEFAULT '' COMMENT '备注',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';

-- 优惠券产品分类关联表
DROP TABLE IF EXISTS `mkt_coupon_categories`;

CREATE TABLE `mkt_coupon_categories` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `category_name` varchar(64) NOT NULL COMMENT '分类名称',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_coupon_category_coupon_id_category_id`(`coupon_id`, `category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券产品分类';

-- 优惠券产品关联表
DROP TABLE IF EXISTS `mkt_coupon_products`;

CREATE TABLE `mkt_coupon_products` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `coupon_id` bigint NOT NULL COMMENT '订单ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `sku_id` bigint NOT NULL DEFAULT 0 COMMENT 'SKUID',
    `product_name` varchar(64) NOT NULL COMMENT '商品名称',
    `sku_name` varchar(64) NOT NULL COMMENT 'SKU名称',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_coupon_category_coupon_id_product_id`(`coupon_id`, `product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券产品';
