package com.cartisan.mall.system;

import com.cartisan.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

/**
 * @author colin
 */
@SpringBootApplication(scanBasePackages = {"com.cartisan", "com.cartisan.mall"})
@MapperScan("com.cartisan.mall.**.mapper")
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cartisan.mall.system.**.gateway")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("System application shutdown");
    }
}