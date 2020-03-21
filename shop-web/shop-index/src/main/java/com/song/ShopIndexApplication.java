package com.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//开启eureka客户端
public class ShopIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopIndexApplication.class, args);
    }

}
