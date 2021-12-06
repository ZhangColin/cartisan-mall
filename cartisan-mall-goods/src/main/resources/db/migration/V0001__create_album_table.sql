-- 相册表
DROP TABLE IF EXISTS `gds_albums`;

CREATE TABLE `gds_albums` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '相册ID',
    `title` varchar(64) NOT NULL COMMENT '标题',
    `cover_image` varchar(256) DEFAULT '' COMMENT '封面',
    `description` varchar(256) DEFAULT '' COMMENT '描述',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='相册';

-- 相册图片表
DROP TABLE IF EXISTS `gds_album_images`;

CREATE TABLE `gds_album_images` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `album_id` bigint NOT NULL COMMENT '相册ID',
    `image` varchar(256) DEFAULT '' COMMENT '图片',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_album_image_album_id`(`album_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='相册图片';
