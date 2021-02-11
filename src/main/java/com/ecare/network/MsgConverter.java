package com.ecare.network;

import com.ecare.dto.DataChangeNotification;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class MsgConverter implements MessageConverter {

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        DataChangeNotification notification = (DataChangeNotification) object;
        MapMessage message = session.createMapMessage();
        message.setString("key", notification.getKey());
        message.setString("sender", notification.getSender());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        return new DataChangeNotification(mapMessage.getString("sender"));
    }
}
