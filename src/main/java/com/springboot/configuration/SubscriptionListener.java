package com.springboot.configuration;

import com.springboot.camel.feed.RSS;
import com.springboot.camel.route.feed.TestRssRoute;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * Author: sazal
 * Date: 5/28/17.
 */
@Component
public class SubscriptionListener implements ApplicationListener<SessionSubscribeEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionListener.class.getName());
    private final CamelContext context;

    @Autowired
    public SubscriptionListener(CamelContext context) {
        this.context = context;
    }

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        Message<byte[]> message = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (command.equals(StompCommand.SUBSCRIBE)) {
            String sessionId = accessor.getSessionId();
            String stompSubscriptionId = accessor.getSubscriptionId();
            String destination = accessor.getDestination();
            LOGGER.info("Subscribed: " + sessionId +
                    " subscription id: " + stompSubscriptionId +
                    " destination: " + destination);
            synchronized (this) {
                try {
                    configureRssRoute();
                    LOGGER.info("initialized rss routes");
                } catch (Exception e) {
                    LOGGER.info("error initializing rss routes");
                }
            }
        }
    }

    private void configureRssRoute() throws Exception {
        if (context.getRoute(RSS.BBC.getName()) == null &&
                context.getRoute(RSS.NYT.getName()) == null) {
            context.addRoutes(new TestRssRoute());
        }
    }
}
