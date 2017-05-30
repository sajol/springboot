package com.springboot.camel.feed.route;

import com.springboot.camel.feed.RSS;
import org.apache.camel.builder.RouteBuilder;

/**
 * Author: sazal
 * Date: 5/25/17
 */
public class RssRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("rss:" + RSS.BBC.getUrl() + "?initialDelay=15000")
                .routeId(RSS.BBC.getName())
                .to("bean:rssFeedHandler");


        from("rss:" + RSS.NYT.getUrl() + "?initialDelay=15000")
                .routeId(RSS.NYT.getName())
                .to("bean:rssFeedHandler");
    }
}