package com.springboot.controllers;

import com.springboot.camel.feed.RSS;
import com.springboot.camel.feed.route.RssRoute;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: sazal
 * Date: 5/23/17.
 */
@Controller
public class FeedController {

    private final CamelContext context;

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class.getName());

    @Autowired
    public FeedController(CamelContext context) {
        this.context = context;
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feeds() {
        return "feeds";
    }

    @RequestMapping(value = "/irt", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    ResponseEntity<String> irt() throws Exception {
        synchronized (this) {
            if (context.getRoute(RSS.BBC.getName()) == null &&
                    context.getRoute(RSS.NYT.getName()) == null) {
                context.addRoutes(new RssRoute());
                LOGGER.info("initialized irt");
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


