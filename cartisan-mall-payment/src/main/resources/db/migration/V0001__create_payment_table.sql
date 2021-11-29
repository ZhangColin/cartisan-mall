-- 支付订单表
DROP TABLE IF EXISTS `pmt_payment_orders`;

CREATE TABLE pmt_payment_orders (
    `id` bigint NOT NULL COMMENT '支付ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `order_no` varchar(64) NOT NULL COMMENT '订单号',
    `trade_no` varchar(64) NOT NULL COMMENT '交易流水号',
    `total_amount` int NOT NULL DEFAULT 0 COMMENT '支付金额',
    `subject` varchar(64) DEFAULT '' COMMENT '交易内容',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '支付状态',
    `applied` datetime COMMENT '申请时间',
    `confirmed` datetime COMMENT '确认时间',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单';


-- DROP TABLE IF EXISTS `mq_message`;
-- CREATE TABLE `mq_message`  (
--    `message_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
--    `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
--    `to_exchange` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
--    `routing_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
--    `class_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
--    `message_status` int(1) NULL DEFAULT 0 COMMENT '0-新建1-已发送2错误抵达3-已抵达',
--    `create_time` datetime(0) NULL,
--    `update_time` datetime(0) NULL,
--    PRIMARY KEY (`message_id`) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

