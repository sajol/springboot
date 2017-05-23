package com.springboot.camel.route.feed;

import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Author: sazal
 * Date: 5/23/17
 */
@Component
@Scope("singleton")
public class NewsFeedXmlJsonFormat{

    private static final XmlJsonDataFormat xmlJsonDataFormat;

    private NewsFeedXmlJsonFormat(){
    }

    static {
        xmlJsonDataFormat = new XmlJsonDataFormat();
        xmlJsonDataFormat.setEncoding("UTF-8");
        xmlJsonDataFormat.setForceTopLevelObject(true);
        xmlJsonDataFormat.setTrimSpaces(true);
        xmlJsonDataFormat.setRootName("feed");
        xmlJsonDataFormat.setSkipNamespaces(true);
        xmlJsonDataFormat.setRemoveNamespacePrefixes(true);
        xmlJsonDataFormat.setExpandableProperties(Arrays.asList("d", "e"));
    }

    public XmlJsonDataFormat getFormat(){
        return xmlJsonDataFormat;
    }
}
