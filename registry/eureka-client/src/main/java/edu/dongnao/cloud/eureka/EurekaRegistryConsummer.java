package edu.dongnao.cloud.eureka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
@Profile("consummer")
public class EurekaRegistryConsummer {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistryConsummer.class, args);
    }
    
    @Autowired
    private EurekaClient eurekaClient;
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
        InstanceInfo info = eurekaClient.getNextServerFromEureka("goods-service",false);

        String url = info.getHomePageUrl()+"/goods/" + 123;
        String data = restTemplate.getForObject(url, String.class);
        return data;
    }

    @RequestMapping("/provider/info")
    public String providerInfo() {
        InstanceInfo info = eurekaClient.getNextServerFromEureka("goods-service",false);
        return info.getHomePageUrl();
    }
    @RequestMapping("/user/{userName}")
    public User getUserDetail(@PathVariable("userName0") String userName) {
        InstanceInfo info = eurekaClient.getNextServerFromEureka("goods-service",false);
        userName= StringUtils.isEmpty(userName)?"":userName;
        String url = info.getHomePageUrl()+"/api/user/" + userName;
        User user=restTemplate.getForObject(url,User.class);
        return user;
    }
    @RequestMapping("/users")
    public List<User> getUsers() {
        InstanceInfo info = eurekaClient.getNextServerFromEureka("goods-service",false);
        String url = info.getHomePageUrl()+"/api/users" ;
        User[] users=restTemplate.getForObject(url,User[].class);
        List<User> returnList=Arrays.asList(users);
        return returnList;
    }
}
