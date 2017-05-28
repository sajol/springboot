package com.springboot.camel.route.feed;

import com.springboot.camel.feed.RSS;
import org.apache.camel.builder.RouteBuilder;

/**
 * Author: sazal
 * Date: 5/25/17
 */
public class TestRssRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("rss:" + RSS.BBC.getUrl() + "?initialDelay=15000&consumer.delay=1000&splitEntries=true")
                .routeId(RSS.BBC.getName())
                .to("bean:rssFeedHandler");


        from("rss:" + RSS.NYT.getUrl() + "?initialDelay=15000&consumer.delay=1000&splitEntries=true")
                .routeId(RSS.NYT.getName())
                .to("bean:rssFeedHandler");
    }
}