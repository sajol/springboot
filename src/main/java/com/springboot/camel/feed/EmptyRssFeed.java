package com.springboot.camel.feed;

/**
 * Author: sazal
 * Date: 5/26/17.
 */
public class EmptyRssFeed extends Feed {

    private static final Feed instance;

    static {
        instance = new EmptyRssFeed("", "", "", "");
    }

    private EmptyRssFeed(String title, String description, String url, String type) {
        super(title, description, url, type);
    }

    public static Feed getInstance() {
        return instance;
    }
}
