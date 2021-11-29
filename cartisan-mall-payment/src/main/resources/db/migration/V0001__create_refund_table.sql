-- 退款订单表
DROP TABLE IF EXISTS `pmt_refund_orders`;

CREATE TABLE pmt_refund_orders (
    `id` bigint NOT NULL COMMENT '支付ID',
    `payment_id` bigint NOT NULL COMMENT '支付订单ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `order_no` varchar(64) NOT NULL COMMENT '订单号',
    `trade_no` varchar(64) NOT NULL COMMENT '退款交易流水号',
    `amount` int NOT NULL DEFAULT 0 COMMENT '退款金额',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '支付状态',
    `refund_channel` tinyint NOT NULL DEFAULT 1 COMMENT '退款渠道[1:支付宝,2:微信,3:银联,4:汇款]',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款订单';
