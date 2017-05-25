package com.springboot.camel.feed;

import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;

/**
 * Author: sazal
 * Date: 5/25/17
 */

@Component
public class RssFeedFactory extends BaseRssFeedFactory {

    @Override
    public Feed createFeed(String title, SyndEntry entry) {
        Feed feed = null;
        if (title != null && entry != null) {
            if (title.toLowerCase().contains(RSS.BBC.getLowerCaseName())) {
                feed = new BBCRssFeed(entry.getTitle(), entry.getDescription().getValue(), entry.getUri());
            } else if (title.toLowerCase().contains(RSS.NYT.getLowerCaseName())) {
                feed = new NYTRssFeed(entry.getTitle(), entry.getDescription().getValue(), entry.getUri());
            }
        }
        return feed;
    }
}
