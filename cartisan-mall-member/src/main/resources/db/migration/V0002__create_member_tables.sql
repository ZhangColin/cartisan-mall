-- 会员表
DROP TABLE IF EXISTS `mbr_members`;

CREATE TABLE `mbr_members` (
    `id` bigint NOT NULL COMMENT '会员ID',
    `level_id` bigint NOT NULL COMMENT '会员等级ID',
    `username` varchar(32) NOT NULL COMMENT '用户名',
    `password` varchar(64) NOT NULL COMMENT '密码',
    `nickname` varchar(32) NOT NULL COMMENT '昵称',
    `phone` varchar(16) NOT NULL COMMENT '手机',
    `email` varchar(64) DEFAULT '' COMMENT '邮箱',
    `avatar` varchar(256) DEFAULT '' COMMENT '头像',
    `gender` tinyint NOT NULL DEFAULT 1 COMMENT '性别[1:男,2:女]',
    `birthday` date COMMENT '生日',
    `city` varchar(32) DEFAULT '' COMMENT '所在城市',
    `job` varchar(32) DEFAULT '' COMMENT '职业',
    `personalized_signature` varchar(256) DEFAULT '' COMMENT '个性签名',
    `source_type` tinyint NOT NULL DEFAULT 0 COMMENT '用户来源',
    `integration` int NOT NULL DEFAULT 0 COMMENT '积分',
    `growth` int NOT NULL DEFAULT 0 COMMENT '成长值',
    `is_enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `index_member_username`(`username`) USING BTREE,
    UNIQUE KEY `index_member_phone`(`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员';

-- 成长值变化历史记录表
DROP TABLE IF EXISTS `mbr_member_growth_change_histories`;

CREATE TABLE `mbr_member_growth_change_histories` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `change_type` tinyint NOT NULL DEFAULT 0 COMMENT '来源[1:增加,2:减少]',
    `change_count` int NOT NULL DEFAULT 0 COMMENT '积分改变数量',
    `operator` varchar(32) DEFAULT '' COMMENT '操作人员',
    `note` varchar(256) DEFAULT '' COMMENT '备注',
    `source_type` tinyint NOT NULL DEFAULT 0 COMMENT '来源[1:购物,2:管理员修改]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_growth_change_history_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成长值变化历史记录';

-- 积分变化历史记录表
DROP TABLE IF EXISTS `mbr_member_integration_change_histories`;

CREATE TABLE `mbr_member_integration_change_histories` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `change_type` tinyint NOT NULL DEFAULT 0 COMMENT '来源[1:增加,2:减少]',
    `change_count` int NOT NULL DEFAULT 0 COMMENT '积分改变数量',
    `operator` varchar(32) DEFAULT '' COMMENT '操作人员',
    `note` varchar(256) DEFAULT '' COMMENT '备注',
    `source_type` tinyint NOT NULL DEFAULT 0 COMMENT '来源[1:购物,2:管理员修改]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `index_member_integration_change_history_member_id`(`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分变化历史记录';
