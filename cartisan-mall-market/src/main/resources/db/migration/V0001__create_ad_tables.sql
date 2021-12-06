-- 广告表
DROP TABLE IF EXISTS `mkt_ads`;

CREATE TABLE `mkt_ads` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '广告ID',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `position` varchar(32) NOT NULL COMMENT '广告位置',
    `start` datetime COMMENT '开始时间',
    `end` datetime COMMENT '结束时间',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `url` varchar(256) DEFAULT '' COMMENT '跳转链接',
    `note` varchar(256) DEFAULT '' COMMENT '备注',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `click_count` int NOT NULL DEFAULT 0 COMMENT '点击数',
    `publisher_id` bigint NOT NULL COMMENT '发布者',
    `auditor_id` bigint NOT NULL COMMENT '审核者',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告';
