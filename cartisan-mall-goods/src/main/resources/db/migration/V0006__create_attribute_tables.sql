-- 属性分组表
DROP TABLE IF EXISTS `gds_attribute_groups`;

CREATE TABLE `gds_attribute_groups` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分组ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `description` varchar(256) DEFAULT '' COMMENT '描述',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_attribute_group_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性分组';

-- 商品属性表
DROP TABLE IF EXISTS `gds_attributes`;

CREATE TABLE `gds_attributes` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '属性ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `group_id` bigint NOT NULL COMMENT '分组ID',
    `name` varchar(32) NOT NULL COMMENT '属性名',
    `need_search` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需要检索',
    `multiple_value` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否多值',
    `value_options` varchar(255) DEFAULT '' COMMENT '可选值列表',
    `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `quick_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '快速展示',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_attribute_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性';

-- 商品规格表
DROP TABLE IF EXISTS `gds_specifications`;

CREATE TABLE `gds_specifications` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规格ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `group_id` bigint NOT NULL COMMENT '分组ID',
    `name` varchar(32) NOT NULL COMMENT '规格名',
    `need_search` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需要检索',
    `multiple_value` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否多值',
    `value_options` varchar(255) DEFAULT '' COMMENT '可选值列表',
    `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `quick_show` bit(1) NOT NULL DEFAULT b'0' COMMENT '快速展示',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_attribute_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品规格';
