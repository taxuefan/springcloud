package edu.dongnao.cloud.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

/**
 * Springcloud bus 内置的事件实际上就是基于ApplicationEvent进行自定义实现的。
 */
@SpringBootApplication
@Profile("myEvent")
public class ApplicationEventApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEventApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyEventConfig.class);
        context.refresh();
        ApplicationEventPublisher publisher = context;
        publisher.publishEvent(new MyEvent("开始学习springcloud Bus 事件"));
    }
    
    // 自定义发布事件
    public static class MyEvent extends ApplicationEvent {
        public MyEvent(String source) {
            super(source);
        }
    }
    
    public static class MyEventConfig {
        @EventListener
        public void onEvent(MyEvent event) {
            System.out.println("接收到了事件："+event);
        }
    }
}
