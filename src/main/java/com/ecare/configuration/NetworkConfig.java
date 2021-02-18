package com.ecare.configuration;

import com.ecare.network.MsgConverter;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * Configures conecction with ActiveMQ
 */
@Configuration
@EnableJms
public class NetworkConfig {

    /**
     * @return Connection factory for establishing connection with ActiveMQ server
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    /**
     * @return queue for communication with ActiveMQ
     */
    @Bean
    Queue queue() {
        return new ActiveMQQueue("activemq/queue/ecareClients");
    }

    /**
     * @return Default listener factory for establishing connection
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(new MsgConverter());
        return factory;
    }

    /**
     * @return handler for communication
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}
