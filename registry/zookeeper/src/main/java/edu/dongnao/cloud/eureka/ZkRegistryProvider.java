package edu.dongnao.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Profile("provider")
public class ZkRegistryProvider {
    public static void main(String[] args) {
        SpringApplication.run(ZkRegistryProvider.class, args);
    }
    
    
    @RequestMapping("/")
    public String home() {
        return "Welcome to eureka provider!";
    }
    
    @RequestMapping("/goods/{id}")
    public String provider(@PathVariable("id") String id) {
        return "Goods Id : "+id;
    }
}
