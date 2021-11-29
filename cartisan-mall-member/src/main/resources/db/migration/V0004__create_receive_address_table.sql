-- 收货地址表
DROP TABLE IF EXISTS `mbr_receive_addresses`;

CREATE TABLE `mbr_receive_addresses` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `name` varchar(32) NOT NULL COMMENT '收货人姓名',
    `phone` varchar(16) NOT NULL COMMENT '电话',
    `post_code` varchar(8) NOT NULL COMMENT '邮编',
    `province` varchar(32) NOT NULL COMMENT '省份/直辖市',
    `city` varchar(32) NOT NULL COMMENT '城市',
    `region` varchar(32) NOT NULL COMMENT '区',
    `detail_address` varchar(256) NOT NULL COMMENT '详细地址',
    `area_code` varchar(16) DEFAULT '' COMMENT '区域编码',
    `is_default` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_receive_address_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址';


