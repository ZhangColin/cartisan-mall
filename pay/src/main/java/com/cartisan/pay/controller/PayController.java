package com.cartisan.pay.controller;

import com.cartisan.pay.domains.payinfo.PayInfo;
import com.cartisan.pay.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
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
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("orderAmount") BigDecimal orderAmount,
                               @RequestParam("payType") BestPayTypeEnum bestPayTypeEnum) {
        if (bestPayTypeEnum != BestPayTypeEnum.WXPAY_NATIVE
                && bestPayTypeEnum != BestPayTypeEnum.ALIPAY_PC) {
            throw new RuntimeException("暂不支付的支付类型。");
        }

        final PayResponse payResponse = payService.create(orderId, orderAmount, bestPayTypeEnum);

        // 支付方式不同，渲染就不同，WXPAY_NATIVE使用codeUrl，ALIPay_PC使用body
        final Map map = new HashMap<>();
        if (bestPayTypeEnum == BestPayTypeEnum.WXPAY_NATIVE) {
            map.put("codeUrl", payResponse.getCodeUrl());
            return new ModelAndView("createForWxNative", map);
        } else {
            map.put("body", payResponse.getBody());
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
    public PayInfo queryByOrderId(@RequestParam String orderId) {
        return payService.queryByOrderId(orderId);
    }
}
