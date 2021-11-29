-- 用户优惠券表
DROP TABLE IF EXISTS `mkt_member_coupons`;

CREATE TABLE `mkt_member_coupons` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
    `member_id` bigint NOT NULL COMMENT '会员ID',
    `nick_name` varchar(64) NOT NULL COMMENT '会员昵称',
    `get_type` tinyint NOT NULL DEFAULT 1 COMMENT '获取方式[0:后台赠送,1:主动领取]',
    `use_status` tinyint NOT NULL DEFAULT 0 COMMENT '使用状态[0:未使用,1:已使用,2:已过期]',
    `received` datetime NOT NULL COMMENT '领取时间',
    `used` datetime COMMENT '使用时间',
    `order_id` bigint COMMENT '订单ID',
    `order_no` varchar(64) DEFAULT '' COMMENT '订单号',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `active` bit(1) NOT NULL DEFAULT b'1',
    `deleted` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';