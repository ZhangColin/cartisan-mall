-- 品牌表
DROP TABLE IF EXISTS `gds_brands`;

CREATE TABLE `gds_brands` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '品牌id',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `image` varchar(1000) DEFAULT '' COMMENT '图片地址',
    `letter` char(1) DEFAULT '' COMMENT '首字母',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌';


-- 品牌分类表
DROP TABLE IF EXISTS `gds_brand_categories`;

CREATE TABLE `gds_brand_categories` (
    `brand_id` bigint NOT NULL COMMENT '品牌Id',
    `category_id` bigint NOT NULL COMMENT '分类Id',
    PRIMARY KEY (`brand_id`, `category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌分类';
