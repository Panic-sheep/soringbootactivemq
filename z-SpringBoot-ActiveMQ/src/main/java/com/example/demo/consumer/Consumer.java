package com.example.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import javax.jms.Queue;
/**
 * 杨三
 */
@Component
@Slf4j
public class Consumer {
    private Log log = LogFactory.getLog(Consumer.class);

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;


    // 接收queue类型消息
    // destination对应配置类中ActiveMQQueue("springboot.queue")设置的名字
    @JmsListener(destination = "springboot.queue")
    public void listenQueue(String msg) {
        log.info("接收到queue消息：" + msg);
        /**
         *  TODO 根据收到的消息处理业务逻辑
         */
        int a = 1/0;    // 模拟抛出一个异常

    }

    @JmsListener(destination = "springboot.queue.2")
    public void listenQueue2(String msg) {
        log.info("接收到queue2消息：" + msg);
        /**
         *  TODO 根据收到的消息处理业务逻辑
         */
       // int a = 1/0;    // 模拟抛出一个异常

    }

    /**
     *   非持久订阅者
     *  接收topic类型消息
     *   destination对应配置类中ActiveMQTopic("springboot.topic")设置的名字
     *   containerFactory对应配置类中注册JmsListenerContainerFactory的bean名称
     * @param msg
     */
    @JmsListener(destination = "springboot.topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void listenTopic(String msg) {
        log.info("topic1 接收到topic消息：" + msg);
        int i = 1 / 0;
    }

    /**
     *   持久订阅者
     *  subscription  订阅者名字
     *   containerFactory对应配置类中注册 jmsTopicPersistentListenerContainerFactory 的bean名称
     * @param msg
     */
    @JmsListener(destination = "springboot.topic", containerFactory = "jmsTopicPersistentListenerContainerFactory",subscription = "poolDoorAccessRecord")
    public void persistentListenTopic(String msg){
        log.info("topic2 接收到topic消息：" + msg);
    }
}