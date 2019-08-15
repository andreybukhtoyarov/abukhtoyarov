package ru.job4j.sqllite;

import org.junit.Test;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertXSQTTest {

    @Test
    public void whenThen() throws IOException {
        String strSourse = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<entries>\n")
                .append("<entry>\n").append("<id>1</id>\n").append("<field>1</field>\n").append("</entry>\n")
                .append("<entry>\n").append("<id>2</id>\n").append("<field>2</field>\n").append("</entry>\n")
                .append("<entry>\n").append("<id>3</id>\n").append("<field>3</field>\n").append("</entry>\n")
                .append("</entries>\n").toString();
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<entries>\n")
                .append("<entry id=\"1\" field=\"1\"/>\n")
                .append("<entry id=\"2\" field=\"2\"/>\n")
                .append("<entry id=\"3\" field=\"3\"/>\n")
                .append("</entries>");
        File source = new File(String.format("%s/soursexmltest.xml", System.getProperty("java.io.tmpdir")));
        File dest = new File(String.format("%s/destenationxmltest.xml", System.getProperty("java.io.tmpdir")));
        File schema = new File("src/main/resources/converterStoreSQL.xsl");
        if (source.createNewFile() && dest.createNewFile()) {
            source.deleteOnExit();
            dest.deleteOnExit();
            try (Writer writer = new FileWriter(source)) {
                writer.write(strSourse);
            }
        }
        ConvertXSQT converter = new ConvertXSQT();
        converter.convert(source, dest, schema);
        StringJoiner result = new StringJoiner("\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(dest))) {
            reader.lines().forEach(result::add);
        }
        assertThat(result.toString(), is(expected.toString()));
    }

}