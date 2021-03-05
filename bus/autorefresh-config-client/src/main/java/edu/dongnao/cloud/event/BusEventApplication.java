package edu.dongnao.cloud.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

/**
 * Springcloud bus 内置的事件实际上就是基于ApplicationEvent进行自定义实现的。
 */
@SpringBootApplication
@Profile("bus")
public class BusEventApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BusEventApplication.class, args);
        // 通过BusProperties获得服务的id
        BusProperties busProperties = context.getBean(BusProperties.class);
        context.publishEvent(new BusEvent("这是springcloud bus事件", busProperties.getId()));
    }
}
