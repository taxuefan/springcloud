package edu.dongnao.cloud.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@RestController
public class AutoRefreshConfigServer {
    
    public static void main(String[] args) {
        SpringApplication.run(AutoRefreshConfigServer.class, args);
    }
    
    @RequestMapping("/")
    public String home() {
        return "Hello, welcome to config server";
    }
}
