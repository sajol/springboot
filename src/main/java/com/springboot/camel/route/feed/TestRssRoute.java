package com.springboot.camel.route.feed;

import com.springboot.camel.feed.RSS;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Author: sazal
 * Date: 5/25/17
 */
@Component
public class TestRssRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("rss:" + RSS.BBC.getUrl() + "?consumer.delay=360000&splitEntries=true")
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