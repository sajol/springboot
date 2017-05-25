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

        from("rss:" + RSS.BBC.getUrl() + "?consumer.delay=5000&splitEntries=true")
                .routeId(RSS.BBC.getName())
                .to("bean:rssFeedHandler");
                /*.marshal().rss() // convert from Rome's model to XML
                .marshal().xmljson() // convert from XML to JSON
                .to("log:test"); // output the result to the log*/


        /*from("rss:" + RSS.NYT.getUrl() + "?consumer.delay=15000")
                .marshal().rss()
                .marshal().xmljson()
                .to("log:test");*/
    }
}