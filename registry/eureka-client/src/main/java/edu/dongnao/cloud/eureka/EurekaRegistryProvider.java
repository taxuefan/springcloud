package edu.dongnao.cloud.eureka;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
@EnableEurekaClient
@Profile("provider")
public class EurekaRegistryProvider {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistryProvider.class, args);
    }
    
    
    @RequestMapping("/")
    public String home() {
        return "Welcome to eureka provider!";
    }
    
    @RequestMapping("/goods/{id}")
    public String provider(@PathVariable("id") String id) {
        return "Goods Id : "+id;
    }

    @RequestMapping("/server/info")
    public String serverInfo(ServletRequest request) {
        return "Server info, "+request.getLocalAddr()+":"+request.getLocalPort();
    }
    
    @RequestMapping("/sleep/{time}")
    public String sleeep(@PathVariable("time") long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Request cost "+time+" milliseconds.";
    }
}
