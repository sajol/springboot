package com.springboot.websocket;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: sazal
 * Date: 5/24/17.
 */
@Component(value = "outboundHandler")
public class OutBoundQueueHandler {

    @Autowired
    private SimpMessageSendingOperations msgTemplate;

    private static Map<String, Object> defaultHeaders;

    static {
        defaultHeaders = new HashMap<>();
        defaultHeaders.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
    }

    public void handle(Exchange exchange) {
        Message camelMessage = exchange.getIn();
        System.out.println(camelMessage.getBody());
        msgTemplate.convertAndSend("/topic/greetings", new Greetings(camelMessage.getBody(String.class)));
    }
}
