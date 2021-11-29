-- 商品会员价表
DROP TABLE IF EXISTS `mkt_member_prices`;

CREATE TABLE `mkt_member_prices` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sku_id` bigint NULL COMMENT 'SKUID',
    `member_level_id` bigint NULL COMMENT '会员等级ID',
    `member_level_name` varchar(64) NOT NULL COMMENT '会员等级名称',
    `member_price` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '会员价',
    `add_other` bit(1) NOT NULL DEFAULT b'0' COMMENT '可否叠加其它优惠',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品会员价';

-- 商品满减表
DROP TABLE IF EXISTS `mkt_sku_full_reductions`;

CREATE TABLE `mkt_sku_full_reductions` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sku_id` bigint NULL COMMENT 'SKUID',
    `full_price` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '满多少',
    `reduce_price` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '减多少',
    `add_other` bit(1) NOT NULL DEFAULT b'0' COMMENT '可否叠加其它优惠',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品满减';

-- 商品阶梯价表
DROP TABLE IF EXISTS `mkt_sku_tiered_prices`;

CREATE TABLE `mkt_sku_tiered_prices` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sku_id` bigint NULL COMMENT 'SKUID',
    `full_count` int NOT NULL DEFAULT 0 COMMENT '满几件',
    `discount` decimal(4, 2) NOT NULL DEFAULT 0 COMMENT '打几折',
    `price` decimal(18, 4) NOT NULL DEFAULT 0 COMMENT '折后价',
    `add_other` bit(1) NOT NULL DEFAULT b'0' COMMENT '可否叠加其它优惠',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_order_item_order_id`(`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品满减';
