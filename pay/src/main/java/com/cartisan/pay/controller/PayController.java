package com.cartisan.pay.controller;

import com.cartisan.pay.application.PayService;
import com.cartisan.pay.domain.PaymentOrder;
import com.cartisan.pay.domain.primitive.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author colin
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") Long orderId,
                               @RequestParam("userId") Long userId,
                               @RequestParam("orderAmount") BigDecimal orderAmount,
                               @RequestParam("payType") PaymentType paymentType) {
//        if (paymentType != PaymentType.WXPAY_NATIVE
//                && paymentType != PaymentType.ALIPAY_PC) {
//            throw new RuntimeException("暂不支付的支付类型。");
//        }

        final String response = payService.prePay(orderId, userId, orderAmount, paymentType);

        // 支付方式不同，渲染就不同，WXPAY_NATIVE使用codeUrl，ALIPay_PC使用body
        final Map<String, String> map = new HashMap<>();
        if (paymentType == PaymentType.WXPAY_NATIVE) {
            map.put("codeUrl", response);
            return new ModelAndView("createForWxNative", map);
        } else {
            map.put("body", response);
            return new ModelAndView("createForAlipayPc", map);
        }
    }

    @PostMapping("notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData) {
        log.info("notifyData={}", notifyData);

        return payService.asyncNotify(notifyData);
    }

    @GetMapping("/queryByOrderId")
    @ResponseBody
    public PaymentOrder queryByOrderId(@RequestParam String orderId) {
        return payService.queryByOrderId(orderId);
    }
}
