package ru.job4j.dropabuses;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DropAbuseInStreamTest {

    private final DropAbuseInStream drop = new DropAbuseInStream();

    private final String[] abuses = new String[] {"abuse", "abuseTwo"};

    private final StubInput input = new StubInput(
            new String[]{"line one\n", "line two\n", "abuse one\n", "line three\n", "abuseTwo two\n"}
    );

    private final String expected = "line oneline two oneline threeTwo two";
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void when() {
        drop.dropAbuses(input, out, abuses);
        assertThat(out.toString(), is(expected));
    }

}