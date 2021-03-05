package edu.dongnao.cloud.eureka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class LoadBalanceConsummer {
    private static final String ORDER_SERVICE_URL = "http://GOODS-SERVICE";
    public static void main(String[] args) {
        SpringApplication.run(LoadBalanceConsummer.class, args);
    }
    // 使用eurekaClient也能使用Loadbalancer
    @Autowired
    private EurekaClient eurekaClient;
    
    @Autowired
    private LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    // 带上负载均衡的RestTemplate
    @LoadBalanced
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
        // 带有负载均衡的eurekaClient
        InstanceInfo info = eurekaClient.getNextServerFromEureka("goods-service",false);
        return info.getHomePageUrl();
    }

    // 带有负载均衡效果的restTemplate
    @RequestMapping("/provider/info")
    public String providerInfo() {
        // 通过Eureka服务标识符(VIP) ORDER_SERVICE_URL 访问服务
        System.out.println(lb);
        return restTemplate.getForObject(ORDER_SERVICE_URL+"/server/info", String.class);
    }
}
