package com.springboot.configuration;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * Author: sazal
 * Date: 5/24/17.
 */

public class ActiveMqConfiguration{


    public BrokerService createBrokerService() throws Exception {
        BrokerService broker = new BrokerService();
        TransportConnector stompConnector = new TransportConnector();
        stompConnector.setUri(new URI("stomp://localhost:61613"));
        broker.addConnector(stompConnector);
        TransportConnector tcpConnector = new TransportConnector();
        tcpConnector.setName("openwire");
        tcpConnector.setUri(new URI("tcp://localhost:61616"));
        broker.addConnector(tcpConnector);
        return broker;
    }
}
