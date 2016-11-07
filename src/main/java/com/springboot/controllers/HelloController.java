package com.springboot.controllers;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("greetings", "Hello!!!!");
        return "hello";
    }
}
