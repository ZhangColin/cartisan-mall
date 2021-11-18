-- SPU表
DROP TABLE IF EXISTS `gds_spus`;

CREATE TABLE `gds_spus` (
    `id` bigint NOT NULL COMMENT 'SPUID',
    `sn` varchar(60) NOT NULL COMMENT '货号',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `caption` varchar(100) NOT NULL COMMENT '副标题',
    `brand_id` bigint NULL COMMENT '品牌ID',
    `category1_id` bigint NULL COMMENT '一级分类ID',
    `category2_id` bigint NULL COMMENT '二级分类ID',
    `category3_id` bigint NULL COMMENT '三级分类ID',
    `template_id` bigint NULL COMMENT '模板ID',
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
    `id` bigint NOT NULL COMMENT 'SKUID',
    `spu_id` bigint NULL COMMENT 'SPUID',
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
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_sku_spu_id`(`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU';

-- 商品日志
DROP TABLE IF EXISTS `gds_spu_logs`;

CREATE TABLE `gds_spu_logs` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `spu_id` bigint NULL COMMENT 'SPUID',
    `content` varchar(1000) DEFAULT '' COMMENT '日志内容',
    `operator` varchar(32) DEFAULT '' COMMENT '操作人员',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_spu_log_spu_id`(`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品日志';

-- 商品审核详情
DROP TABLE IF EXISTS `gds_spu_audits`;

CREATE TABLE `gds_spu_audits` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核ID',
    `spu_id` bigint NULL COMMENT 'SPUID',
    `result` varchar(16) DEFAULT '' COMMENT '审核结果',
    `feedback` varchar(256) DEFAULT '' COMMENT '反馈详情',
    `auditor` varchar(32) DEFAULT '' COMMENT '审核人员',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_spu_audit_spu_id`(`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品审核详情';
