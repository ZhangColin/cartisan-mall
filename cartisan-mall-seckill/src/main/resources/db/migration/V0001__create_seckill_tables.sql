-- 秒杀活动表
DROP TABLE IF EXISTS `sck_seckill_activities`;

CREATE TABLE sck_seckill_activities (
    `id` bigint NOT NULL COMMENT '秒杀活动ID',
    `name` varchar(32) NOT NULL COMMENT '活动名称',
    `title` varchar(64) NOT NULL COMMENT '活动标题',
    `subtitle` varchar(64) NULL COMMENT '活动副标题',
    `start` datetime NOT NULL COMMENT '开始时间',
    `end` datetime NOT NULL COMMENT '结束时间',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动';

-- 秒杀活动场次表
DROP TABLE IF EXISTS `sck_seckill_sessions`;

CREATE TABLE sck_seckill_sessions (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀场次ID',
    `activity_id` bigint NOT NULL COMMENT '秒杀活动ID',
    `name` varchar(32) NOT NULL COMMENT '场次名称',
    `start` datetime NOT NULL COMMENT '开始时间',
    `end` datetime NOT NULL COMMENT '结束时间',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_seckill_session_activity_id`(`activity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动场次';

-- 秒杀商品表
DROP TABLE IF EXISTS `sck_seckill_skus`;

CREATE TABLE sck_seckill_notifications (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `activity_id` bigint NOT NULL COMMENT '秒杀活动ID',
    `session_id` bigint NOT NULL COMMENT '活动场次ID',
    `sku_id` bigint NOT NULL COMMENT '商品ID',
    `price` int NOT NULL DEFAULT 0 COMMENT '价格',
    `count` int NOT NULL DEFAULT 0 COMMENT '秒杀总量',
    `limit` int NOT NULL DEFAULT 0 COMMENT '每人限购数量',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_seckill_sku_activity_id_session_id`(`activity_id`, `session_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品';


-- 秒杀提醒表
DROP TABLE IF EXISTS `sck_seckill_notifications`;

CREATE TABLE sck_seckill_notifications (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `session_id` bigint NOT NULL COMMENT '活动场次ID',
   `member_id` bigint NOT NULL COMMENT '会员ID',
   `subscribed` datetime NOT NULL COMMENT '订阅时间',
   `sended` datetime COMMENT '发送时间',
   `notice_type` tinyint NOT NULL DEFAULT 1 COMMENT '通知方式[0:短信,1:邮件]',
   `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `index_seckill_notification_session_id`(`session_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀提醒';
