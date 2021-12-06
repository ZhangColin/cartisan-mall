-- 商品表
DROP TABLE IF EXISTS `gds_products`;

CREATE TABLE `gds_products` (
    `id` bigint NOT NULL COMMENT '商品ID',
    `brand_id` bigint NOT NULL COMMENT '品牌ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(64) NOT NULL COMMENT '商品名称',
    `product_sn` varchar(64) NOT NULL COMMENT '货号',
    `specifications` varchar(512) NOT NULL COMMENT '规格',
    `introduction` longtext NULL COMMENT '介绍信息',
    `marketable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否上架',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_product_brand_id`(`brand_id`) USING BTREE,
    INDEX `index_product_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

-- 商品参数值表
DROP TABLE IF EXISTS `gds_product_attributes`;

CREATE TABLE `gds_product_attributes` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性值ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `group_id` bigint NOT NULL COMMENT '属性分组ID',
    `group_name` varchar(32) NOT NULL COMMENT '属性名',
    `attribute_id` bigint NOT NULL COMMENT '属性ID',
    `attribute_name` varchar(32) NOT NULL COMMENT '属性名',
    `value` varchar(256) NOT NULL COMMENT '手动添加或选择的参数值',
    `quick_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '快速展示',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_product_attribute_product_id`(`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品参数值';

-- SKU表
DROP TABLE IF EXISTS `gds_product_skus`;

CREATE TABLE `gds_product_skus` (
    `id` bigint NOT NULL COMMENT 'ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `sku_code` varchar(64) NOT NULL COMMENT '条形码',
    `title` varchar(64) NOT NULL COMMENT '标题',
    `subtitle` varchar(64) DEFAULT '' COMMENT '副标题',
    `specifications` varchar(512) NOT NULL COMMENT '规格',
    `default_image` varchar(256) DEFAULT '' COMMENT '默认图片',
    `price` int NOT NULL COMMENT '价格（分）',
    `stock` int NOT NULL DEFAULT 0 COMMENT '库存数',
    `low_stock` int NOT NULL DEFAULT 0 COMMENT '预警库存',
    `stock_locked` int NOT NULL DEFAULT 0 COMMENT '锁定库存',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_product_sku_product_id`(`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU';

-- SKU图片表
DROP TABLE IF EXISTS `gds_product_sku_images`;

CREATE TABLE `gds_product_sku_images` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `name` varchar(256) NOT NULL COMMENT '图片名称',
    `url` varchar(256) NOT NULL COMMENT '图片地址',
    `default_image` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认图',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_product_sku_image_product_id_sku_id`(`product_id`, `sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU图片';

