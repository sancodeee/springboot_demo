package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot应用程序
 *
 * @author wangsen
 * @date 2024/05/19
 */
@MapperScan("com.ws.*.mapper")
@SpringBootApplication(scanBasePackages = "com.ws")
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
