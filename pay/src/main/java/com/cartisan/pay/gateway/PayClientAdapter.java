package com.cartisan.pay.gateway;

import com.cartisan.pay.domain.PayClient;
import com.cartisan.pay.domain.PaymentOrder;
import com.cartisan.pay.domain.PaymentResponse;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author colin
 */
@Service
@Slf4j
public class PayClientAdapter implements PayClient {
    private final BestPayService bestPayService;

    public PayClientAdapter(BestPayService bestPayService) {
        this.bestPayService = bestPayService;
    }

    @Override
    public String pay(PaymentOrder paymentOrder) {
        final BestPayTypeEnum bestPayTypeEnum = BestPayTypeEnum.getByName(paymentOrder.getPaymentType().getCode());

        final PayRequest payRequest = new PayRequest();
        payRequest.setOrderName("colin-支付");
        payRequest.setOrderId(paymentOrder.getPaymentOrderNo().toString());
        payRequest.setOrderAmount(paymentOrder.getAmount().doubleValue());
        payRequest.setPayTypeEnum(bestPayTypeEnum);

        if (bestPayTypeEnum == BestPayTypeEnum.ALIPAY_PC) {
            final PayResponse payResponse = bestPayService.pay(payRequest);

            log.info("response={}", payResponse);
            return payResponse.getBody();
        } else {
            final PayResponse payResponse = new PayResponse();
            payResponse.setCodeUrl("weixin://wxpay/bizpayurl?pr=Ep579SY");
            return payResponse.getCodeUrl();
        }
    }

    @Override
    public PaymentResponse resolveNotifyData(String notifyData) {
        final PayResponse payResponse = bestPayService.asyncNotify(notifyData);

        return new PaymentResponse(Long.parseLong(payResponse.getOrderId()),
                payResponse.getOutTradeNo(), payResponse.getOrderAmount());
    }
}
