package com.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: sazal
 * Date: 5/23/17.
 */
@Controller
public class FeedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class.getName());

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feeds() {
        return "feeds";
    }
}


