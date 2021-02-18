package com.ecare.network;

import com.ecare.controllers.AuthController;
import com.ecare.dto.DataChangeNotification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


/**
 * Sender is responsible for communication with ActiveMQ and delivering notifications
 * to the clients
 */
@Component
public class Sender {

    private static Logger logger = LogManager.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    /**
     * Notifies clients about need of reloading the data
     */
    public void notifyClients() {
        try {
            final DataChangeNotification message = new DataChangeNotification();
            message.setSender("Ecare Home Server");
            jmsTemplate.setMessageConverter(new MsgConverter());
            jmsTemplate.convertAndSend(queue, message);
            logger.trace("Sent notification to the clients");
        } catch (Exception e) {
            logger.error("Failed to send notification to the clients: " + e.toString());
        }
    }

    /**
     * Receive ack
     * @return
     */
    public String receiveAck() {
        return (String) jmsTemplate.receiveAndConvert("ackQueue");
    }
}