package ru.job4j.sqllite;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SAXParserForStoreXMLTest {

    @Test
    public void whenFieldsOneTwoThreeThenSix() throws IOException {
        StringBuilder sb = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<entries>\n")
                .append("<entry id=\"1\" field=\"1\"/>\n")
                .append("<entry id=\"2\" field=\"2\"/>\n")
                .append("<entry id=\"3\" field=\"3\"/>\n")
                .append("</entries>");
        File xml = new File(String.format("%s/xmltosaxparser.xml", System.getProperty("java.io.tmpdir")));
        if (xml.createNewFile()) {
            xml.deleteOnExit();
            try (Writer writer = new FileWriter(xml)) {
                writer.write(sb.toString());
            }
            try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
                SAXParserForStoreXML parser = new SAXParserForStoreXML(xml, byteOut);
                parser.init();
                assertThat(byteOut.toString(), is("6"));
            }
        }
    }

}