package com.cartisan.mall.portal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PreDestroy;

/**
 * @author colin
 */
@SpringBootApplication(scanBasePackages = {"com.cartisan", "com.cartisan.mall"})
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cartisan.mall.portal.**.gateway")
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("System application shutdown");
    }
}
