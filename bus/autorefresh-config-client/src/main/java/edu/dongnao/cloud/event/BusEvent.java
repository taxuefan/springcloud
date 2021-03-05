package edu.dongnao.cloud.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.annotation.Profile;

@Profile({"bus", "busListener"})
public class BusEvent extends RemoteApplicationEvent {
    private BusEvent(){}

    /**
     * 
     * @param source 事件内容
     * @param originService 给指定的服务
     */
    public BusEvent(Object source, String originService){
        super(source, originService , "**");
    }

}
