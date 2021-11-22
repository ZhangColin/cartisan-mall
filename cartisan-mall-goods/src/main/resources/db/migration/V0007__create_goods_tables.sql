-- SPU表
DROP TABLE IF EXISTS `gds_spus`;

CREATE TABLE `gds_spus` (
    `id` bigint NOT NULL COMMENT '商品ID',
    `brand_id` bigint NOT NULL COMMENT '品牌ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(255) NOT NULL COMMENT '商品名称',
    `description` varchar(1000) DEFAULT '' COMMENT '商品描述',
    `marketable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否上架',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_spu_brand_id`(`brand_id`) USING BTREE,
    INDEX `index_spu_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SPU';

-- SPU介绍信息表
DROP TABLE IF EXISTS `gds_spu_introductions`;

CREATE TABLE `gds_spu_introductions` (
    `id` bigint NOT NULL COMMENT '商品ID',
    `introduction` longtext NULL COMMENT '介绍信息',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SPU介绍信息';

-- SPU图片表
DROP TABLE IF EXISTS `gds_spu_images`;

CREATE TABLE `gds_spu_images` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `spu_id` bigint NOT NULL COMMENT '商品ID',
    `name` varchar(255) NOT NULL COMMENT '图片名称',
    `url` varchar(255) NOT NULL COMMENT '图片地址',
    `default_image` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认图',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_spu_image_spu_id`(`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SPU图片';

-- SPU属性值表
DROP TABLE IF EXISTS `gds_spu_attributes`;

CREATE TABLE `gds_spu_attributes` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性值ID',
    `spu_id` bigint NOT NULL COMMENT '商品ID',
    `attribute_id` bigint NOT NULL COMMENT '属性ID',
    `name` varchar(255) NOT NULL COMMENT '属性名',
    `value` varchar(255) NOT NULL COMMENT '属性值',
    `quick_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '快速展示',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_spu_attribute_spu_id`(`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SPU属性值';

-- SKU表
DROP TABLE IF EXISTS `gds_skus`;

CREATE TABLE `gds_skus` (
    `id` bigint NOT NULL COMMENT 'SKUID',
    `spu_id` bigint NOT NULL COMMENT 'SPUID',
    `brand_id` bigint NOT NULL COMMENT '品牌ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(255) NOT NULL COMMENT '名称',
    `title` varchar(255) NOT NULL COMMENT '标题',
    `subtitle` varchar(255) DEFAULT '' COMMENT '副标题',
    `description` varchar(2000) DEFAULT '' COMMENT '描述',
    `default_image` varchar(255) DEFAULT '' COMMENT '默认图片',
    `price` int NOT NULL COMMENT '价格（分）',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_sku_spu_id`(`spu_id`) USING BTREE,
    INDEX `index_sku_brand_id`(`brand_id`) USING BTREE,
    INDEX `index_sku_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU';

-- SKU图片表
DROP TABLE IF EXISTS `gds_sku_images`;

CREATE TABLE `gds_sku_images` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `name` varchar(255) NOT NULL COMMENT '图片名称',
    `url` varchar(255) NOT NULL COMMENT '图片地址',
    `default_image` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认图',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_sku_image_sku_id`(`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU图片';

-- SKU属性值表
DROP TABLE IF EXISTS `gds_sku_attributes`;

CREATE TABLE `gds_sku_attributes` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性值ID',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `attribute_id` bigint NOT NULL COMMENT '属性ID',
    `name` varchar(255) NOT NULL COMMENT '属性名',
    `value` varchar(255) NOT NULL COMMENT '属性值',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_sku_attribute_sku_id`(`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU属性值';
