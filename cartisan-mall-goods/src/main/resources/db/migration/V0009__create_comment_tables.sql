-- 商品评价表
DROP TABLE IF EXISTS `gds_comments`;

CREATE TABLE `gds_comments` (
    `id` bigint NOT NULL COMMENT '评论ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `product_name` varchar(256) NOT NULL COMMENT '商品名称',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `nickname` varchar(64) NOT NULL COMMENT '会员昵称',
    `star` tinyint(1) NOT NULL DEFAULT 0 COMMENT '星级',
    `show` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否显示',
    `product_attributes` varchar(256) NOT NULL COMMENT '购买时属性组合',
    `likes_count` int NOT NULL DEFAULT 0 COMMENT '点赞数',
    `reply_count` int NOT NULL DEFAULT 0 COMMENT '回复数',
    `resources` varchar(1024) NOT NULL COMMENT '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
    `content` longtext NOT NULL COMMENT '内容',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_comment_product_id_sku_id`(`product_id`, `sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价';

-- 商品评价回复表
CREATE TABLE `gds_comment_replies` (
    `id` bigint NOT NULL COMMENT '商品ID',
    `comment_id` bigint NOT NULL COMMENT '评论ID',
    `supporter_id` bigint NOT NULL COMMENT '售后人员ID',
    `supporter_name` varchar(64) NOT NULL COMMENT '售后人员',
    `resources` varchar(1024) NOT NULL COMMENT '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
    `content` longtext NOT NULL COMMENT '内容',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_comment_reply_comment_id`(`comment_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价';
