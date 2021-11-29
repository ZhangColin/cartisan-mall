-- 活动表
DROP TABLE IF EXISTS `mkt_activities`;

CREATE TABLE `mkt_activities` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `name` varchar(256) NOT NULL COMMENT '活动名称',
    `title` varchar(256) NOT NULL COMMENT '活动标题',
    `subtitle` varchar(256) DEFAULT '' COMMENT '活动副标题',
    `start` datetime COMMENT '开始时间',
    `end` datetime COMMENT '结束时间',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `content` longtext COMMENT '活动内容',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动';


-- 活动商品表
DROP TABLE IF EXISTS `mkt_activity_spus`;

CREATE TABLE `mkt_activity_spus` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `activity_id` bigint NOT NULL COMMENT '活动ID',
    `spu_id` bigint NOT NULL COMMENT 'SPUID',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_activity_spu_activity_id`(`activity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动商品';
