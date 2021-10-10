package com.cartisan.pay.domain.primitive;

import com.lly835.bestpay.enums.BestPayResultEnum;
import com.lly835.bestpay.exception.BestPayException;
import lombok.Getter;

import static com.cartisan.pay.domain.primitive.PaymentPlatform.ALIPAY;
import static com.cartisan.pay.domain.primitive.PaymentPlatform.WX;

/**
 * 支付方式
 * @author colin
 */
@Getter
public enum PaymentType {
    /**
     * 支付宝app
     */
    ALIPAY_APP("alipay_app", ALIPAY, "支付宝app"),

    /**
     * 支付宝pc
     */
    ALIPAY_PC("alipay_pc", ALIPAY, "支付宝pc"),

    /**
     * 支付宝wap
     */
    ALIPAY_WAP("alipay_wap", ALIPAY, "支付宝wap"),

    /**
     * 支付宝统一下单(h5)
     */
    ALIPAY_H5("alipay_h5", ALIPAY, "支付宝统一下单(h5)"),

    /**
     * 支付宝统一收单线下交易预创建
     */
    ALIPAY_QRCODE("alipay_precreate", ALIPAY, "支付宝统一收单线下交易预创建"),

    /**
     * 支付宝统一收单交易支付接口(付款码)
     */
    ALIPAY_BARCODE("alipay_barcode", ALIPAY, "支付宝统一收单交易支付接口(付款码)"),

    /**
     * 微信公众账号支付
     */
    WXPAY_MP("JSAPI", WX, "微信公众账号支付"),

    /**
     * 微信H5支付
     */
    WXPAY_MWEB("MWEB", WX, "微信H5支付"),

    /**
     * 微信Native支付
     */
    WXPAY_NATIVE("NATIVE", WX, "微信Native支付"),

    /**
     * 微信小程序支付
     */
    WXPAY_MINI("JSAPI", WX, "微信小程序支付"),

    /**
     * 微信APP支付
     */
    WXPAY_APP("APP", WX, "微信APP支付"),

    /**
     * 微信付款码支付
     */
    WXPAY_MICRO("MICRO", WX, "微信付款码支付"),
    ;

    private final String code;
    private final PaymentPlatform platform;
    private final String description;

    PaymentType(String code, PaymentPlatform platform, String description) {
        this.code = code;
        this.platform = platform;
        this.description = description;
    }

    public static PaymentType getByName(String code) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.name().equalsIgnoreCase(code)) {
                return paymentType;
            }
        }
        throw new BestPayException(BestPayResultEnum.PAY_TYPE_ERROR);
    }
}

