-- 相册表
DROP TABLE IF EXISTS `gds_albums`;

CREATE TABLE `gds_albums` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '相册id',
    `title` varchar(100) NOT NULL COMMENT '名称',
    `image` varchar(256) DEFAULT '' COMMENT '封面',
    `image_items` text NULL COMMENT '图片列表',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='相册';
