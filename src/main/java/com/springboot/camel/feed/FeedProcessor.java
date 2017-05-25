package com.springboot.camel.feed;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.builder.xml.XPathBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: sazal
 * Date: 5/23/17
 */
@Component
@Qualifier(value = "feedProcessor")
public class FeedProcessor implements Processor{
    @Override
    public void process(Exchange exchange) throws Exception {
        String title = XPathBuilder.xpath("/rss/channel/title").evaluate(exchange.getContext(), exchange.getIn().getBody());
        exchange.setProperty("fileName", title + "-" + new Date().toString());
        System.out.println("Inside feed processor");
    }
}
