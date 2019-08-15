package ru.job4j.sqllite;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void whenThen() throws Exception {
        String expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<entries>\n")
                .append("    <entry>\n")
                .append("        <id>1</id>\n").append("        <field>1</field>\n")
                .append("    </entry>\n")
                .append("    <entry>\n")
                .append("        <id>2</id>\n").append("        <field>2</field>\n")
                .append("    </entry>\n")
                .append("    <entry>\n")
                .append("        <id>3</id>\n").append("        <field>3</field>\n")
                .append("    </entry>\n")
                .append("</entries>").toString();
        StringJoiner result = new StringJoiner("\n");
        File target = new File(String.format("%s/targetxmltest.xml", System.getProperty("java.io.tmpdir")));
        Entry.EntryBuilder entryBuilder = new Entry.EntryBuilder();
        List<Entry> entries = new ArrayList<>();
        for (int count = 1; count < 4; ++count) {
            entries.add(entryBuilder.setField(count).setId(count).build());
        }
        if (target.createNewFile()) {
            target.deleteOnExit();
            StoreXML st = new StoreXML(target);
            st.save(entries);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::add);
        }
        assertThat(result.toString(), is(expected));
    }
}
