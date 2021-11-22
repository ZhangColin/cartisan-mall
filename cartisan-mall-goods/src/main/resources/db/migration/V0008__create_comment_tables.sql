-- 商品评价表
DROP TABLE IF EXISTS `gds_comments`;

CREATE TABLE `gds_comments` (
    `id` bigint NOT NULL COMMENT '商品ID',
    `sku_id` bigint NOT NULL COMMENT 'SKUID',
    `spu_id` bigint NOT NULL COMMENT 'SPUID',
    `reply_id` bigint NULL COMMENT '回复ID',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '评论类型[0:对商品的直接评论，1:对评论的回复]',
    `name` varchar(255) NOT NULL COMMENT '商品名称',
    `member_nick_name` varchar(32) NOT NULL COMMENT '会员昵称',
    `member_avatar` varchar(255) NOT NULL COMMENT '会员头像',
    `member_ip` varchar(255) NOT NULL COMMENT '会员IP',
    `star` tinyint(1) NOT NULL DEFAULT 0 COMMENT '星级',
    `show` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否显示',
    `spu_attributes` varchar(255) NOT NULL COMMENT '购买时属性组合',
    `likes_count` int NOT NULL DEFAULT 0 COMMENT '点赞数',
    `reply_count` int NOT NULL DEFAULT 0 COMMENT '回复数',
    `resources` varchar(1000) NOT NULL COMMENT '评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
    `content` longtext NOT NULL COMMENT '内容',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价';
