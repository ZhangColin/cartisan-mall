package com.cartisan.pay.domain;

/**
 * @author colin
 */
public interface PayClient {
    String pay(PaymentOrder paymentOrder);

    PaymentResponse resolveNotifyData(String notifyData);
}
