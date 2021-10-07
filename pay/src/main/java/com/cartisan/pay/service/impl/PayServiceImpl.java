package com.cartisan.pay.service.impl;

import com.cartisan.pay.domains.payinfo.PayInfo;
import com.cartisan.pay.domains.payinfo.PayInfoRepository;
import com.cartisan.pay.domains.payinfo.PayPlatformEnum;
import com.cartisan.pay.service.PayService;
import com.lly835.bestpay.enums.BestPayPlatformEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.enums.OrderStatusEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private final BestPayService bestPayService;
    private final PayInfoRepository payInfoRepository;

    public PayServiceImpl(BestPayService bestPayService, PayInfoRepository payInfoRepository) {
        this.bestPayService = bestPayService;
        this.payInfoRepository = payInfoRepository;
    }

    /**
     * 业务系统         支付系统        支付平台
     * 1111     ->     2222     ->
     * 1111     ->     3333     ->
     *
     * 发起第2次支付前，把上一次的支付订单关闭（调用支付平台api）
     * @param orderId
     * @param amount
     * @param bestPayTypeEnum
     * @return
     */
    @Override
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) {
        if (bestPayTypeEnum != BestPayTypeEnum.WXPAY_NATIVE
                && bestPayTypeEnum != BestPayTypeEnum.ALIPAY_PC) {
            throw new RuntimeException("暂不支付的支付类型。");
        }
        // 写入数据库
        final PayInfo payInfo = new PayInfo(Long.parseLong(orderId),
                PayPlatformEnum.getByBestPayTypeEnum(bestPayTypeEnum).getCode(),
                OrderStatusEnum.NOTPAY.name(),
                amount);
        payInfoRepository.save(payInfo);

        final PayRequest payRequest = new PayRequest();
        payRequest.setOrderName("colin-支付");
        payRequest.setOrderId(orderId);
        payRequest.setOrderAmount(amount.doubleValue());
        payRequest.setPayTypeEnum(bestPayTypeEnum);

        if (bestPayTypeEnum == BestPayTypeEnum.ALIPAY_PC) {
            final PayResponse payResponse = bestPayService.pay(payRequest);

            log.info("response={}", payResponse);
            return payResponse;
        } else {
            final PayResponse payResponse = new PayResponse();
            payResponse.setCodeUrl("weixin://wxpay/bizpayurl?pr=Ep579SY");
            return payResponse;
        }
    }

    @Override
    @ResponseBody
    public String asyncNotify(String notifyData) {
        // 1. 签名检验
        final PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("异步通知 response={}", payResponse);

        // 2. 金额检验（从数据库查订单）
        // 比较严重（正常情况下是不会发生的）发出告警：钉钉，短信
        final PayInfo payInfo = payInfoRepository
                .findByOrderNo(Long.parseLong(payResponse.getOrderId()))
                .orElseThrow(()->new RuntimeException("通过orderNo查询到的结果是null"));


        // 如果订单支付状态不是"已支付"
        if (!payInfo.getPlatformStatus().equals(OrderStatusEnum.SUCCESS.name())) {
            // Double 类型比较大小，精度不好控制
            if (payInfo.getPayAmount().compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0) {
                // 告警
                throw new RuntimeException("异步通知中的金额和数据库里的不一致, orderNo="+payResponse.getOrderId());
            }
            // 3. 修改订单支付状态
            payInfo.setPlatformNumber(payResponse.getOutTradeNo());
            payInfo.setPlatformStatus(OrderStatusEnum.SUCCESS.name());
            payInfoRepository.save(payInfo);
        }

        // TODO: pay发送MQ消息，mall接受MQ消息
        
        // 4. 告诉微信不要再通知了
        if (payResponse.getPayPlatformEnum() == BestPayPlatformEnum.WX) {
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        } else if (payResponse.getPayPlatformEnum() == BestPayPlatformEnum.ALIPAY) {
            return "success";
        }

        throw new RuntimeException("异步通知中错误的支付平台");
    }

    @Override
    public PayInfo queryByOrderId(String orderId) {
        return payInfoRepository
                .findByOrderNo(Long.parseLong(orderId)).orElse(null);
    }
}
/**
 * 商户应用私钥   Java 应用需要
 * 商户应用公钥
 * <p>
 * 支付宝公钥    Java 应用需要
 * 支付宝私钥（看不到）
 * <p>
 * RSA 非对称签名
 * 发起支付：商户（商户应用私钥签名） -> 支付宝（商户应用公钥验签）
 * <p>
 * 异步通知：支付宝（支付宝私钥签名） -> 商户（支付宝公钥验签）
 * <p>
 * RSA签名不等于加密（Java代码不一样）
 * 一般加解密使用AES
 */
