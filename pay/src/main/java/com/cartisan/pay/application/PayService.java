package com.cartisan.pay.application;

import com.cartisan.pay.domain.PayClient;
import com.cartisan.pay.domain.PaymentOrder;
import com.cartisan.pay.domain.PaymentOrderRepository;
import com.cartisan.pay.domain.PaymentResponse;
import com.cartisan.pay.domain.primitive.PaymentPlatform;
import com.cartisan.pay.domain.primitive.PaymentStatus;
import com.cartisan.pay.domain.primitive.PaymentType;
import com.cartisan.utils.SnowflakeIdWorker;
import com.google.gson.Gson;
import com.lly835.bestpay.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Service
@Slf4j
public class PayService {
    private final PaymentOrderRepository paymentOrderRepository;

    private final PayClient payClient;
    private final SnowflakeIdWorker idWorker;
    private final AmqpTemplate amqpTemplate;

    public PayService(PaymentOrderRepository paymentOrderRepository,
                      PayClient payClient,
                      SnowflakeIdWorker idWorker,
                      AmqpTemplate amqpTemplate) {
        this.paymentOrderRepository = paymentOrderRepository;
        this.payClient = payClient;
        this.idWorker = idWorker;
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * 业务系统         支付系统        支付平台
     * 1111     ->     2222     ->
     * 1111     ->     3333     ->
     *
     * 发起第2次支付前，把上一次的支付订单关闭（调用支付平台api）
     * 创建/发起支付
     * @param orderId
     * @param amount
     * @param paymentType
     * @return
     */
    public String prePay(Long orderId, Long userId, BigDecimal amount, PaymentType paymentType) {
        if (paymentType != PaymentType.WXPAY_NATIVE
                && paymentType != PaymentType.ALIPAY_PC) {
            throw new RuntimeException("暂不支付的支付类型。");
        }

        // 写入数据库
        final PaymentOrder paymentOrder = new PaymentOrder(idWorker.nextId(), orderId, userId,
                amount, paymentType);

        paymentOrderRepository.save(paymentOrder);

        return payClient.pay(paymentOrder);
    }

    /**
     * 异步通知处理
     * @param notifyData
     */
    public String asyncNotify(String notifyData) {
        // 1. 签名检验
        final PaymentResponse paymentResponse = payClient.resolveNotifyData(notifyData);
        log.info("异步通知 response={}", paymentResponse);

        // 2. 金额检验（从数据库查订单）
        // 比较严重（正常情况下是不会发生的）发出告警：钉钉，短信
        final PaymentOrder payOrder = paymentOrderRepository
                .findById(paymentResponse.getOrderId())
                .orElseThrow(()->new RuntimeException("通过orderNo查询到的结果是null"));


        // 如果订单支付状态不是"已支付"
        if (!payOrder.getPlatformStatus().equals(OrderStatusEnum.SUCCESS.name())) {
            // Double 类型比较大小，精度不好控制
            if (payOrder.getAmount().compareTo(BigDecimal.valueOf(paymentResponse.getOrderAmount())) != 0) {
                // 告警
                throw new RuntimeException("异步通知中的金额和数据库里的不一致, orderNo="+paymentResponse.getOrderId());
            }

            // 3. 修改订单支付状态
            payOrder.paid(paymentResponse.getOutTradeNo(), PaymentStatus.SUCCESS.name());
            paymentOrderRepository.save(payOrder);
        }

        // pay发送MQ消息，mall接受MQ消息
        amqpTemplate.convertAndSend("payNotify", new Gson().toJson(payOrder));

        // 4. 告诉微信不要再通知了
        if (payOrder.getPaymentPlatform() == PaymentPlatform.WX) {
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        } else if (payOrder.getPaymentPlatform() == PaymentPlatform.ALIPAY) {
            return "success";
        }

        throw new RuntimeException("异步通知中错误的支付平台");
    }

    /**
     * 根据订单号查询支付记录
     * @param orderId
     * @return
     */
    public PaymentOrder queryByOrderId(String orderId) {
        return paymentOrderRepository
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
