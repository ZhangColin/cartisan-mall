-- 购物车表
DROP TABLE IF EXISTS `ord_cart_items`;

CREATE TABLE `ord_cart_items` (
    `id` bigint NOT NULL COMMENT 'ID',
    `product_id` bigint NOT NULL COMMENT '产品ID',
    `product_sku_id` bigint NOT NULL COMMENT '商品ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `quantity` int NOT NULL COMMENT '购买数量',
    `price` int NOT NULL COMMENT '添加到购物车的价格',
    `product_image` varchar(256) NOT NULL COMMENT '商品主图',
    `product_name` varchar(64) NOT NULL COMMENT '商品名称',
    `product_sub_title` varchar(64) NOT NULL COMMENT '商品副标题',
    `product_sku_code` varchar(64) NOT NULL COMMENT '商品条码',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_cart_item_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车';
