package com.cartisan.pay.service;

import com.cartisan.pay.domains.payinfo.PayInfo;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

/**
 * @author colin
 */
public interface PayService {
    /**
     * 创建/发起支付
     * @param orderId
     * @param amount
     */
    PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum);

    /**
     * 异步通知处理
     * @param notifyData
     */
    String asyncNotify(String notifyData);

    /**
     * 根据订单号查询支付记录
     * @param orderId
     * @return
     */
    PayInfo queryByOrderId(String orderId);
}
