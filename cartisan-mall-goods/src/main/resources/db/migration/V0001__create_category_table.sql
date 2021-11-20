-- 商品分类表
DROP TABLE IF EXISTS `gds_categories`;

CREATE TABLE `gds_categories` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级分类ID',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `is_show` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示',
    `is_menu` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否导航',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_category_parent_id`(`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类';
