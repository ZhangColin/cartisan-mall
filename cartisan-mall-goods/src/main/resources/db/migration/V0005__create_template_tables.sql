-- 模板表
DROP TABLE IF EXISTS `gds_templates`;

CREATE TABLE `gds_templates` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '模板Id',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板';

-- 规格表
DROP TABLE IF EXISTS `gds_specifications`;

CREATE TABLE `gds_specifications` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规格Id',
    `template_id` bigint NULL COMMENT '模板Id',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `options` varchar(100) NOT NULL COMMENT '选项',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格';

-- 参数表
DROP TABLE IF EXISTS `gds_parameters`;

CREATE TABLE `gds_parameters` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '参数Id',
    `template_id` bigint NULL COMMENT '模板Id',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `options` varchar(100) NOT NULL COMMENT '选项',
    `sequence` int NOT NULL DEFAULT 0 COMMENT '排序',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数';
