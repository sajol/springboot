package com.springboot.camel.route.feed;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Author: sazal
 * Date: 5/15/17
 */
@Component
public class NewsFeedExample extends RouteBuilder {

    private final XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();


    @Autowired
    @Qualifier(value = "feedProcessor")
    private Processor newFeedProcessor;


    @Override
    public void configure() throws Exception {
        configureRouteFor(RSS.BBC);
        configureRouteFor(RSS.NYT);
    }


    private RouteDefinition configureRouteFor(RSS rss) {
        return from("quartz2://" + rss.getLowerCaseName() + "?cron=0/30 * * * * ?")
                .to(rss.getUrl())
                .wireTap("file:data/inbox/feed/xml/" + rss.getName())
                .end()
                .streamCaching()
                .process(newFeedProcessor)
                .log("Downloaded " + rss.getName() + " xml feed")
                .marshal(xmlJsonFormat)
                .log(rss.getName() + " xml feed to json conversion completed")
                .to("file:data/inbox/feed/json/" + rss.getLowerCaseName())
                .log("Saved " + rss.getName() + " json file successfully");
    }
}