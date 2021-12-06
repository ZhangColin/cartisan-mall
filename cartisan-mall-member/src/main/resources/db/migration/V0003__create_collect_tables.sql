-- 商品收藏表
DROP TABLE IF EXISTS `mbr_member_collect_products`;

CREATE TABLE `mbr_member_collect_products` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `product_name` varchar(256) NOT NULL COMMENT '商品名称',
    `product_image` varchar(256) DEFAULT '' COMMENT '商品图片',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_collect_product_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品收藏';

-- 活动收藏表
DROP TABLE IF EXISTS `mbr_member_collect_activities`;

CREATE TABLE `mbr_member_collect_activities` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `activity_id` bigint NOT NULL COMMENT '活动ID',
    `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
    `activity_image` varchar(256) DEFAULT '' COMMENT '活动图片',
    `activity_url` varchar(256) DEFAULT '' COMMENT '活动Url',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_collect_activity_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动收藏';


