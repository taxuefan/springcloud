package edu.dongnao.cloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@Profile("consummer")
public class ZkRegistryConsummer {

    public static void main(String[] args) {
        SpringApplication.run(ZkRegistryConsummer.class, args);
    }
    
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    
    @Bean
    public RestTemplate bean(){
        return new RestTemplate();
    }
    
    @RequestMapping("/")
    public String home() {
        return "Welcome to eureka consummer!";
    }
    
    @RequestMapping("/consummer")
    public String consummer() {
        List<ServiceInstance> list = discoveryClient.getInstances("goods-service");
        String url = list.get(0).getUri().toString()+"/goods/" + 123;
        String data = restTemplate.getForObject(url, String.class);
        return data;
    }

}
