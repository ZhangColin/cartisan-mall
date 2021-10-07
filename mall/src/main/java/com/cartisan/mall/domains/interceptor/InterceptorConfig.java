//package com.cartisan.mall.domains.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
//import org.springframework.format.support.FormattingConversionService;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
///**
// * @author colin
// */
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new UserLoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/user/register");
//    }
//
//    @Override
//    public FormattingConversionService mvcConversionService() {
//        final FormattingConversionService formattingConversionService = super.mvcConversionService();
//
//        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        registrar.registerFormatters(formattingConversionService);
//
//        return formattingConversionService;
//    }
//
//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//
//        // 注册 Spring data jpa pageable 的参数分解器
//        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
//    }
//}
