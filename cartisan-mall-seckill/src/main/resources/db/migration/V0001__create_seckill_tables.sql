-- 秒杀活动表
DROP TABLE IF EXISTS `sck_seckill_promotions`;

CREATE TABLE sck_seckill_promotions (
    `id` bigint NOT NULL COMMENT '秒杀活动ID',
    `name` varchar(32) NOT NULL COMMENT '活动名称',
    `title` varchar(64) NOT NULL COMMENT '活动标题',
    `subtitle` varchar(64) NULL COMMENT '活动副标题',
    `start` date NOT NULL COMMENT '开始日期',
    `end` date NOT NULL COMMENT '结束日期',
    `is_enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动';

-- 秒杀活动场次表
DROP TABLE IF EXISTS `sck_seckill_promotion_sessions`;

CREATE TABLE sck_seckill_promotion_sessions (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀场次ID',
    `seckill_id` bigint NOT NULL COMMENT '秒杀活动ID',
    `name` varchar(32) NOT NULL COMMENT '场次名称',
    `start_time` time NOT NULL COMMENT '每日开始时间',
    `end_time` time NOT NULL COMMENT '每日结束时间',
    `is_enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_seckill_session_seckill_id`(`seckill_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动场次';

-- 秒杀商品表
DROP TABLE IF EXISTS `sck_seckill_products`;

CREATE TABLE sck_seckill_products (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `seckill_id` bigint NOT NULL COMMENT '秒杀活动ID',
    `session_id` bigint NOT NULL COMMENT '活动场次ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `sku_id` bigint NOT NULL COMMENT '商品ID',
    `price` int NOT NULL DEFAULT 0 COMMENT '价格',
    `count` int NOT NULL DEFAULT 0 COMMENT '秒杀总量',
    `limit` int NOT NULL DEFAULT 0 COMMENT '每人限购数量',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_seckill_product_seckill_id_session_id`(`seckill_id`, `session_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品';


-- 秒杀提醒表
DROP TABLE IF EXISTS `sck_seckill_notifications`;

CREATE TABLE sck_seckill_notifications (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `session_id` bigint NOT NULL COMMENT '活动场次ID',
   `member_id` bigint NOT NULL COMMENT '会员ID',
   `subscribed` datetime NOT NULL COMMENT '订阅时间',
   `send` datetime COMMENT '发送时间',
   `notice_type` tinyint NOT NULL DEFAULT 1 COMMENT '通知方式[0:短信,1:邮件]',
   `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `index_seckill_notification_session_id`(`session_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀提醒';
