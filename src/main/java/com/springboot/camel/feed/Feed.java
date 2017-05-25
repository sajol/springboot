package com.springboot.camel.feed;

/**
 * Author: sazal
 * Date: 5/25/17
 */
public abstract class Feed {
    private final String type;
    private String title;
    private String description;
    private String url;

    public Feed(String title, String description, String url, String type) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
}