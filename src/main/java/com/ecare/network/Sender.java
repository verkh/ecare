package com.ecare.network;

import com.ecare.controllers.AuthController;
import com.ecare.dto.DataChangeNotification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


@Component
public class Sender {

    private static Logger logger = LogManager.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    public void notifyClients() {
        try {
            final DataChangeNotification message = new DataChangeNotification();
            jmsTemplate.convertAndSend(queue, message);
            logger.trace("Sent notification to the clients");
        } catch (Exception e) {
            logger.error("Failed to send notification to the clients: " + e.toString());
        }
    }

    public String receiveAck() {
        return (String) jmsTemplate.receiveAndConvert("ackQueue");
    }
}