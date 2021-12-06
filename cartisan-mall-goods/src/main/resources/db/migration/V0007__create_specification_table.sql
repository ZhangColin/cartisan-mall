-- 商品规格
DROP TABLE IF EXISTS `gds_specifications`;

CREATE TABLE `gds_specifications` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规格ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(32) NOT NULL COMMENT '规格名称',
    `value_options` varchar(255) DEFAULT '' COMMENT '可选值列表',
    `manual_add` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否支持手动新增',
    `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_specification_category_id`(`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品规格';
