package edu.dongnao.cloud.stream.rabbit;

import edu.dongnao.cloud.stream.rabbit.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    MessageProvider mp;
    
    @RequestMapping("/send")
    public void send(){
        mp.send();
    }
}
