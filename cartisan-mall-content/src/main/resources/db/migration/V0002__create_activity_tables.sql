-- 活动表
DROP TABLE IF EXISTS `cnt_activities`;

CREATE TABLE `cnt_activities` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `title` varchar(256) NULL COMMENT '活动标题',
    `start` datetime DEFAULT NULL COMMENT '开始时间',
    `end` datetime DEFAULT NULL COMMENT '结束时间',
    `content` text COMMENT '活动内容',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动';
