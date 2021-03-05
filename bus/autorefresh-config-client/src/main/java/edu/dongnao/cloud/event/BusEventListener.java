package edu.dongnao.cloud.event;

import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("busListener")
public class BusEventListener {
    @EventListener
    public void onEvent(BusEvent event) {
        System.out.println("接收到了事件："+event);
    }
}
