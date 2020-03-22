package com.example.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * yangsan
 */
@Configuration
@EnableJms
public class ActiveMQPoolConfig {

    /**
     * 使用连接池需要注入这个bean
     */
    @Autowired
    JmsPoolConnectionFactory jmsPoolConnectionFactory;

    /**
     *  创建队列  和  Topic
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("springboot.queue");
    }

    @Bean
    public Queue queue2() {
        return new ActiveMQQueue("springboot.queue.2");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("springboot.topic");
    }


    /**
     *  springboot默认只配置queue类型消息，如果要使用topic类型的消息，则需要配置该bean
     *  使用非持久订阅者
     */
    @Bean
    public JmsListenerContainerFactory jmsTopicListenerContainerFactory(JmsPoolConnectionFactory jmsPoolConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(jmsPoolConnectionFactory);
        //这里必须设置为true，false则表示是queue类型
        factory.setPubSubDomain(true);
        return factory;
    }


    /**
     *  持久订阅者配置
     * @param jmsPoolConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory jmsTopicPersistentListenerContainerFactory(JmsPoolConnectionFactory jmsPoolConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(jmsPoolConnectionFactory);
        // 必须设置clientId才能生效  必须设置factory.setSubscriptionDurable(true) 必须设置为true
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        factory.setClientId("poolDoorAccessRecord");
        return factory;
    }
}
