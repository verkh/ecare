package com.ecare.tests;

import com.ecare.dto.DataChangeNotification;
import com.ecare.dto.Option;
import com.ecare.dto.Plan;
import com.ecare.network.MsgConverter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueueConnection;
import com.mockrunner.mock.jms.MockSession;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.jms.Message;
import javax.jms.Session;
import java.io.StringReader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParserComposer {

    @Test
    public void testParseComposeNotification() throws Exception {
        final String sender = "Test";

        DestinationManager destManager = new DestinationManager();
        ConfigurationManager confManager = new ConfigurationManager();
        MockQueueConnection connection = new MockQueueConnection(destManager, confManager);
        MockSession session =(MockSession)connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);

        MsgConverter converter = new MsgConverter();

        DataChangeNotification msg = new DataChangeNotification();
        msg.setSender(sender);

        Message msgSerialized = converter.toMessage(msg, session);
        DataChangeNotification parsed = (DataChangeNotification) converter.fromMessage(msgSerialized);

        assertEquals(msg, parsed);
    }
}
