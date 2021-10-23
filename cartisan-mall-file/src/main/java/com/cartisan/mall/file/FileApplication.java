package com.cartisan.mall.file;

import com.cartisan.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author colin
 */
@SpringBootApplication(scanBasePackages = {"com.cartisan", "com.cartisan.mall"})
@EnableEurekaClient
@Slf4j
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class);
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }
}
