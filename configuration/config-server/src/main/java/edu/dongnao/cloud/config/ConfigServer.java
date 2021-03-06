package edu.dongnao.cloud.config;

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
public class ConfigServer {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
    
    @RequestMapping("/")
    public String home() {
        return "Hello, welcome to config server";
    }
}
