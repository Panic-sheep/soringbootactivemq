package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

/**
 * yangsan
 */
@RestController
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private Queue queue2;

    //发送queue类型消息
    @GetMapping("/queue")
    public void sendQueueMsg(String msg) {
        jmsTemplate.convertAndSend(queue, msg);
        /**
         * 处理业务逻辑
         */
        int i = 1/0; // 模拟抛出异常
    }

    //发送queue类型消息
    @GetMapping("/queue2")
    public void sendQueue2Msg(String msg) {
        jmsTemplate.convertAndSend(queue2, msg);
    }

    //发送topic类型消息
    @GetMapping("/topic")
    public void sendTopicMsg(String msg) {
        jmsTemplate.convertAndSend(topic, msg);
    }

    //发送queue类型消息
    @GetMapping("/queueMore")
    public void sendQueueMsgMore() {
        for (int i = 0; i < 9; i++) {
            jmsTemplate.convertAndSend(queue, String.valueOf(i));
        }
    }

}
