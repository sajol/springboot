package com.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: sazal
 * Date: 11/7/16
 */
@Controller
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        logger.info("Inside " + this.getClass().getName());
        logger.debug("Inside " + this.getClass().getName());
        model.addAttribute("greetings", "Hello!!!!");
        return "hello";
    }
}
