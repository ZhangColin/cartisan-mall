-- 第三方系统用户绑定表
DROP TABLE IF EXISTS `mbr_social_users`;

CREATE TABLE `mbr_social_users` (
    `id` bigint NOT NULL COMMENT 'ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '系统类型',
    `social_user_id` varchar(64) NOT NULL COMMENT '社交登录ID',
    `access_token` varchar(64) NOT NULL COMMENT '社交登录Token',
    `expires_in` DateTime NULL COMMENT '社交登录过期时间',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方系统用户';
