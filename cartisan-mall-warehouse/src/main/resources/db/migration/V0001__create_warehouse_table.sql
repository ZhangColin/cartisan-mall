-- 仓库表
DROP TABLE IF EXISTS `whs_warehouses`;

CREATE TABLE `whs_warehouses` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
    `name` varchar(64) NOT NULL COMMENT '仓库名',
    `address` varchar(256) DEFAULT '' COMMENT '仓库地址',
    `area_code` varchar(16) DEFAULT '' COMMENT '区域编码',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库';
