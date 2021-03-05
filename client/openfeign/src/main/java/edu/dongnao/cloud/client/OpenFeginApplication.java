package edu.dongnao.cloud.client;

import edu.dongnao.cloud.commons.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@RestController
public class OpenFeginApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeginApplication.class, args);
    }
    
    @Autowired
    StoreService service;
    
    @RequestMapping("store/{storeId}")
    public Store getStore(@PathVariable("storeId") Integer id) {
        return service.getStore(id);
    }

    @RequestMapping("/sleep/{time}")
    public Object sleep(@PathVariable("time") long time) {
        return service.sleep(time);
    }
}
