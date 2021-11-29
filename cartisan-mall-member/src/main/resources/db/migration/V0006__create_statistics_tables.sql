-- 登录日志表
DROP TABLE IF EXISTS `mbr_login_logs`;

CREATE TABLE `mbr_login_logs` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `ip` varchar(32) NULL COMMENT 'IP',
    `city` varchar(32) NULL COMMENT '城市',
    `login_type` tinyint NOT NULL DEFAULT 0 COMMENT '登录类型[1:Web,2:App]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_login_log_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- 会员统计表
DROP TABLE IF EXISTS `mbr_member_statistics`;

CREATE TABLE `mbr_member_statistics` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `consume_amount` int NOT NULL DEFAULT 0 COMMENT '累计消费金额',
    `coupon_amount` int NOT NULL DEFAULT 0 COMMENT '累计优惠金额',
    `order_count` int NOT NULL DEFAULT 0 COMMENT '订单数量',
    `coupon_count` int NOT NULL DEFAULT 0 COMMENT '优惠券数量',
    `comment_count` int NOT NULL DEFAULT 0 COMMENT '评价数量',
    `return_order_count` int NOT NULL DEFAULT 0 COMMENT '退货数量',
    `attend_count` int NOT NULL DEFAULT 0 COMMENT '关注数量',
    `fans_count` int NOT NULL DEFAULT 0 COMMENT '粉丝数量',
    `collect_product_count` int NOT NULL DEFAULT 0 COMMENT '收藏的商品数量',
    `collect_subject_count` int NOT NULL DEFAULT 0 COMMENT '收藏的专题活动数量',
    `collect_comment_count` int NOT NULL DEFAULT 0 COMMENT '收藏的评论数量',
    `invite_friend_count` int NOT NULL DEFAULT 0 COMMENT '邀请的朋友数量',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_statistics_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员统计';


