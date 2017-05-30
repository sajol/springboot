package com.springboot.camel.feed.handler;

import com.springboot.camel.feed.BaseRssFeedFactory;
import com.springboot.camel.feed.Feed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: sazal
 * Date: 5/25/17
 */
@Component(value = "rssFeedHandler")
public class RssFeedHandler {

    private final SimpMessageSendingOperations msgTemplate;

    private final BaseRssFeedFactory feedFactory;

    private static Map<String, Object> defaultHeaders;

    static {
        defaultHeaders = new HashMap<>();
        defaultHeaders.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
    }

    @Autowired
    public RssFeedHandler(SimpMessageSendingOperations msgTemplate, BaseRssFeedFactory feedFactory) {
        this.msgTemplate = msgTemplate;
        this.feedFactory = feedFactory;
    }

    public void sendBody(@Body SyndFeed syndFeed) {
        syndFeed
                .getEntries()
                .stream()
                .map(entry -> feedFactory.createFeed(syndFeed.getTitle(), (SyndEntry) entry))
                .filter(f -> f != null)
                .forEach(f -> msgTemplate.convertAndSend("/topic/feeds", f));
    }
}
