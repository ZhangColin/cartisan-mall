-- SPU表
DROP TABLE IF EXISTS `gds_spus`;

CREATE TABLE `gds_spus` (
    `id` bigint NOT NULL COMMENT 'SPUId',
    `sn` varchar(60) NOT NULL COMMENT '货号',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `caption` varchar(100) NOT NULL COMMENT '副标题',
    `brand_id` bigint NULL COMMENT '品牌Id',
    `category1_id` bigint NULL COMMENT '一级分类Id',
    `category2_id` bigint NULL COMMENT '二级分类Id',
    `category3_id` bigint NULL COMMENT '三级分类Id',
    `template_id` bigint NULL COMMENT '模板Id',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `images` varchar(1000) DEFAULT '' COMMENT '图片列表',
    `sale_service` varchar(50) DEFAULT '' COMMENT '售后服务',
    `introduction` text NULL COMMENT '介绍',
    `specification_items` varchar(3000) DEFAULT '' COMMENT '规格',
    `parameter_items` varchar(3000) DEFAULT '' COMMENT '参数',
    `is_marketable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否上架',
    `enable_specification` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用规格',
    `audit_status` tinyint NOT NULL DEFAULT 1 COMMENT '审核状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SPU';

-- SKU表
DROP TABLE IF EXISTS `gds_skus`;

CREATE TABLE `gds_skus` (
    `id` bigint NOT NULL COMMENT 'SKUId',
    `spu_id` bigint NULL COMMENT 'SPUId',
    `sn` varchar(60) NOT NULL COMMENT '商品条码',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `specification` varchar(1000) DEFAULT '' COMMENT '规格',
    `price` int NOT NULL COMMENT '价格（分）',
    `weight` int NOT NULL COMMENT '重量（克）',
    `quantity` int NOT NULL COMMENT '库存数量',
    `alert_quantity` int NOT NULL COMMENT '库存预警数量',
    `image` varchar(256) DEFAULT '' COMMENT '商品图片',
    `images` varchar(1000) DEFAULT '' COMMENT '商品图片列表',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU';
