package edu.dongnao.cloud.stream.rabbit.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

//@Service  // 不需要用，使用@EnableBinding
// 通过Source表明是消息的源头，即生产者
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {
    // 不要用@Autowried，不能够通过字段名获得容器实例
    @Resource
    MessageChannel output;
    
    @Override
    public void send() {
        String message = "time: "+System.currentTimeMillis();
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("Send message: "+message);
    }
}
