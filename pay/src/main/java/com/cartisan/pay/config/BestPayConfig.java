package com.cartisan.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author colin
 */
@Configuration
public class BestPayConfig {
    @Bean
    public BestPayService bestPayService(WxPayConfig wxPayConfig, AliPayConfig aliPayConfig) {

        final BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        bestPayService.setAliPayConfig(aliPayConfig);

        return bestPayService;
    }

    @Bean
    public AliPayConfig aliPayConfig() {
        final AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId("2021000118624705");
        aliPayConfig.setSandbox(true);
        aliPayConfig.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFx/tcB6WPhbeaEb3NqAaTf47Pps+0llEIenrM/9kIhREyMaMsn4rK7SCIC3EvcMPQ16TXAlZNG8WcQ9A1ZKLcH+ghSi2Lcb1wVQDLo8fp+e2HkzyqRGxVmrfCeoIEPjBR2j+WJkEbleCu7r7LXV8x/3zl12IqjmDTgGSz/sZxEVF1yANK98hMNCkW4RkSfXSFWylk3BgzB5cZB2LUFRfmfttT3lHVZoDqPmccEaLCc53cH5SXRnM26B01xB2K03G6PBu9ZTlh1Hp9DUJtIUMhO1Uo83YVpu2jApe/9dLbQBt0+dyplb19tuMZxgolutLXwPtOf5P67BIlKbs7Mft3AgMBAAECggEAKMG6RFlYCReVzexlxMfR6RHNclnBoaTB3zFhRB8WAdB4YrBYo/mfER+Y75l8zBUwsulfoNlnbWpuk6LdRcb5DtLxp2Kv6XGnYxeifGfTOecYhFtI+h6KWrIeFbO49I31rpotERNqdnr8zo6RtecmX1cUt/kcQRbLTOQqyeNulXdeaMaGn6flu2A3cZkK6OTVRB10NXIvra/Ttf9naNohBlGnoDSSYMOTCYZfQreD0QY2eCJsA0KNY8Ie7zw08be5ykLTm6RxVSp2531DgL49xNGoIOQKgop133zJ2XBMWThmqGuJLhCNBBbv2O98e0/3KkYnoVoqGlA6ehP474cjIQKBgQC+i8kt/rfennCIucnM4r5rHDT2SmktTJ2924YWUIt7Vm7+SKGnMkdjKPh6u3YQfp3r19Pr2ALC2Z3dhNpoT4ZgO9RH7mdKWvylPcOOSKqNsv27/yRivbW9yrQ1vmUGWXniU6wuHxbRqUiA0KoJPn9g8LmR/SE4IGJ5FUo9S8l1yQKBgQCzvGovWxcy2WkEYEO7P+4+m+oPrY+mThjMAqoUbZEgcScY5diZB0/KUoAZPV0LoRMRJmpt+eDgNhzm69PHxd7VNLKuxfBoMp6LV80QJszxCdPiHMEOYwiK+6Jzd2vzpS2yVjq17nU9XkQfxIphizENm3dLrh/FEC39JSA0D/mHPwKBgHGM/fux8sLwqmFgTjkR2qYb7hfjqh/7q3kr7BE6OJTsokJY6DSqCs616LXDJw2XVubn/Htsg+C4LJKJDFrjIVIR9WRUVyuBY1PZpcMR3MmKpBi3aGiLY1XSCWiJqMyK386XTGFD16Zj+jgBoYLWwrPzYLL2JSi8a9J6dH5ovoq5AoGAOYQVxsmzhsYj4Q6dHhK7PrXOFO5JhcKAFOjVXGwjgOo0xLbotS+tzOTRsUH9wHbvDxHoBQFtgNJQNWVKBCfBwyB1/Y/B+tYX/ic+q+h+tLOcZkMlVGFEnpUfyytwFCvLj8IgT4/bXKQJ0NCoJ8bPD7esWT+DUyB2VwfGYmKqSykCgYEAiEVInUNfZdP9BVUpNnQvHYa+Pcacnw/BupKdJpmdAB4yeNNsp+YjfiAecxJRkn+wIisFNewn0bS+6JuIYGejZzCtTvzWV0LORZy41JMO2qUY667xIgOFVjOuHLzp/vu8lroLBA8rapmPQO4fTq8P4EtZFrRZMN1HgLJ32/xrrJ4=");
        aliPayConfig.setAliPayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg0sqG9/o/TM80KTDkwKGY2TO/+ntauPcA8+zPZpqFj8XYXfXMPXfj1IxLpQQ8a0V08Fa60fvOOXf6tGB1cSgvvrdTfBXo3pfb3VYKVzykf2CrLXcdifB6e15fzyFLsjsbLu8hFI4GIvaIDu9mLq+mGtlo18hBQ1eHfBrWjCgu8UKAYEl0+yZkHkHZIlv3Bhm7YopPwmaB9qSug58LC/G/0dqO4VDzYpdOC9xBB1ifctbiDZCnbqvpnLYU37ipXi/gmxL4qyLBojt1rb0Z8j6nx6QsJKKA2Fxypp/I3rbMzW860PFQTIBhFurJ8RuY2NPJ3apoewpBRl9BRo4z11cUwIDAQAB");
        aliPayConfig.setReturnUrl("http://0d4a-101-229-207-152.ngrok.io/");
        aliPayConfig.setNotifyUrl("http://0d4a-101-229-207-152.ngrok.io/pay/notify");
        return aliPayConfig;
    }

    @Bean
    public WxPayConfig wxPayConfig() {
        final WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wxd898fcb01713c658");
        wxPayConfig.setMchId("1483469312");
        wxPayConfig.setMchKey("098F6BCD4621D373CADE4E832627B4F6");
        wxPayConfig.setNotifyUrl("http://0d4a-101-229-207-152.ngrok.io/pay/notify");
        return wxPayConfig;
    }
}
