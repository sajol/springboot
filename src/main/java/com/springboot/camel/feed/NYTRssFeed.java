package com.springboot.camel.feed;

/**
 * Author: sazal
 * Date: 5/25/17
 */
public class NYTRssFeed extends Feed {
    public NYTRssFeed(String title, String description, String url) {
        super(title, description, url, RSS.NYT.getName());
    }
}
