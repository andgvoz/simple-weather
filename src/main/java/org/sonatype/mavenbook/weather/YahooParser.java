package org.sonatype.mavenbook.weather;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YahooParser {

    private static Logger log = Logger.getLogger(YahooParser.class);

    public Weather parse(InputStream inputStream) throws Exception {
        Weather weather = new Weather();

        log.info("Creating XML reader");

        SAXReader xmlReader = createXmlReader();
        Document doc = xmlReader.read(inputStream);

        log.info("Parsing XML Response");
        weather.setCity("");
        weather.setRegion("");
        weather.setCountry("");
        weather.setCondition("");
        weather.setTemp("");
        weather.setChill("");
        weather.setHumidity("");

        return weather;
    }

    private SAXReader createXmlReader() {
        Map<String, String> uris = new HashMap<String, String>();
        uris.put("y", "http://xml.weather.yahoo.com/ns/rss/1.0");

        DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs(uris);

        SAXReader xmlReader = new SAXReader();
        xmlReader.setDocumentFactory(factory);
        return xmlReader;
    }
}
