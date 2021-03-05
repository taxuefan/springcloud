package edu.dongnao.cloud.bus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
// 表示当前类是一个可刷新区域
@RefreshScope
public class AutoRefreshConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(AutoRefreshConfigClient.class, args);
    }
    @Value("${user.name}")
    private String name;

    @RequestMapping("/")
    public String home() {
        return "Hello "+name+"! Welcome to config client.";
    }
}
