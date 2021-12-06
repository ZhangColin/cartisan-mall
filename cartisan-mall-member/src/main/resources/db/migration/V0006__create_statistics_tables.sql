-- 登录日志表
DROP TABLE IF EXISTS `mbr_login_logs`;

CREATE TABLE `mbr_login_logs` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `ip` varchar(64) DEFAULT '' COMMENT 'IP',
    `city` varchar(32) DEFAULT '' COMMENT '城市',
    `province` varchar(32) DEFAULT '' COMMENT '省份/直辖市',
    `login_type` tinyint NOT NULL DEFAULT 0 COMMENT '登录类型[1:PC,2:H5,3:Android,4:ios,5:小程序]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_login_log_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- 会员统计信息表
DROP TABLE IF EXISTS `mbr_member_statistics_infos`;

CREATE TABLE `mbr_member_statistics_infos` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `consume_amount` int NOT NULL DEFAULT 0 COMMENT '累计消费金额',
    `order_count` int NOT NULL DEFAULT 0 COMMENT '订单数量',
    `coupon_count` int NOT NULL DEFAULT 0 COMMENT '优惠券数量',
    `comment_count` int NOT NULL DEFAULT 0 COMMENT '评价数量',
    `return_order_count` int NOT NULL DEFAULT 0 COMMENT '退货数量',
    `login_count` int NOT NULL DEFAULT 0 COMMENT '登录次数',
    `attend_count` int NOT NULL DEFAULT 0 COMMENT '关注数量',
    `fans_count` int NOT NULL DEFAULT 0 COMMENT '粉丝数量',
    `collect_product_count` int NOT NULL DEFAULT 0 COMMENT '收藏的商品数量',
    `collect_subject_count` int NOT NULL DEFAULT 0 COMMENT '收藏的专题活动数量',
    `collect_topic_count` int NOT NULL DEFAULT 0 COMMENT '收藏的话题数量',
    `collect_comment_count` int NOT NULL DEFAULT 0 COMMENT '收藏的评论数量',
    `invite_friend_count` int NOT NULL DEFAULT 0 COMMENT '邀请的朋友数量',
    `recent_order_time` datetime COMMENT '最后一次下订单时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_statistics_info_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员统计信息';


