package com.springboot.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: sazal
 * Date: 5/22/17.
 */
public class TestJmsRoute extends RouteBuilder{

    static final Logger LOG = LoggerFactory.getLogger(TestJmsRoute.class.getName());

    @Override
    public void configure() throws Exception {
        from("{{inbound.endpoint}}")
                .transacted()
                .log(LoggingLevel.INFO, LOG, "Received message")
                .process(exchange -> LOG.info("Exchange=>", exchange))
                .loop()
                .simple("{{outbound.loop.count}}")
                .to("{{outbound.endpoint}}")
                .log(LoggingLevel.INFO, LOG, "Message sent. Loop : ${property.CamelLoopIndex}")
                .end();
    }
}
