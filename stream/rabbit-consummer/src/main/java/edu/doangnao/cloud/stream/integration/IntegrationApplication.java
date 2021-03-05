package edu.doangnao.cloud.stream.integration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Profile("integration")
public class IntegrationApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = 
                SpringApplication.run(IntegrationApplication.class, args);
        TimeUnit.SECONDS.sleep(5);
        MessageChannel outBoundChannel = (MessageChannel) context.getBean("amqpOutboundChannel");
        MessagingTemplate template = new MessagingTemplate();
        template.sendAndReceive(outBoundChannel, new GenericMessage<>("我来自amqpoutboundChannel"));
    }
    
    @Bean
    public MessageChannel amqpInputChannel(){
        // 为每个发送消息调用的单个订阅通道，调用是发生在发送方线程中
        return new DirectChannel();
    }
    
    // 定义一个适配器
    @Bean
    public AmqpInboundChannelAdapter inboundChannelAdapter(ConnectionFactory factory,
                                                           @Qualifier("amqpInputChannel") MessageChannel messageChannel) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setQueueNames("queue-1");
        AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(container);
        adapter.setOutputChannel(messageChannel);
        return adapter;
    }
    
    // ----------------假设，下面这是另外一个系统的方法，发送过来一个消息---------------------------
    @Bean
    public MessageChannel amqpOutChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "amqpOutboundChannel")
    public AmqpOutboundEndpoint amqpOutbound(AmqpTemplate amqpTemplate){
        AmqpOutboundEndpoint outBound = new AmqpOutboundEndpoint(amqpTemplate);
        outBound.setRoutingKey("queue-1");
        return outBound;
    }

}
