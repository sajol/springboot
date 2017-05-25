package com.springboot.camel.feed;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * Author: sazal
 * Date: 5/25/17
 */
public abstract class BaseRssFeedFactory {
    public abstract Feed createFeed(String title, SyndEntry entry);
}
