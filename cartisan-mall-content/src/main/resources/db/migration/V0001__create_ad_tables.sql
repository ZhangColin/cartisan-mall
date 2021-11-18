-- 广告表
DROP TABLE IF EXISTS `cnt_ads`;

CREATE TABLE `cnt_ads` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '广告ID',
    `name` varchar(64) NULL COMMENT '名称',
    `position` varchar(32) NULL COMMENT '广告位置',
    `start` datetime DEFAULT NULL COMMENT '开始时间',
    `end` datetime DEFAULT NULL COMMENT '结束时间',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    `url` varchar(256) DEFAULT '' COMMENT '跳转链接',
    `remarks` varchar(1000) DEFAULT '' COMMENT '备注',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告';
