package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * springboot应用程序
 *
 * @author wangsen
 * @date 2024/05/19
 */
@MapperScan("com.ws.*.mapper")
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.ws")
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
