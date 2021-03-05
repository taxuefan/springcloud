package edu.doangnao.cloud.stream.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@SpringBootApplication
public class RabbitConsummerApplication {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private AtomicBoolean semaphore = new AtomicBoolean(true);
    
    public static void main(String[] args) {
        SpringApplication.run(RabbitConsummerApplication.class, args);
    }

    @Bean
    public Consumer<Person> personConsumer() {
        return person -> {
            System.out.println("Received: " + person);
        };
    }

    public static class Person {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String toString() {
            return this.name;
        }
    }
}
