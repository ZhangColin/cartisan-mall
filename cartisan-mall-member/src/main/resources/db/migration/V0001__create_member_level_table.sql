-- 会员等级表
DROP TABLE IF EXISTS `mbr_member_levels`;

CREATE TABLE `mbr_member_levels` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '等级ID',
    `name` varchar(64) NOT NULL COMMENT '等级名称',
    `growth_point` int NOT NULL DEFAULT 0 COMMENT '等级需要的成长值',
    `free_freight` int NOT NULL DEFAULT 0 COMMENT '免运费标准',
    `comment_growth_point` int NOT NULL DEFAULT 0 COMMENT '每次评价获取的成长值',
    `default_level` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认等级',
    `privilege_free_freight` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有免邮特权',
    `privilege_member_price` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有会员价格特权',
    `privilege_birthday` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有生日特权',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级';
