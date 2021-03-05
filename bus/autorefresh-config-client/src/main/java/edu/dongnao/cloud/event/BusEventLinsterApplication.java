package edu.dongnao.cloud.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("busListener")
public class BusEventLinsterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEventApplication.class, args);
    }
}
