package ru.job4j.control;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenLoadThenLoad() throws IOException {
        Path file = mkFile("e-mail=my@gmail.com", "line", "password=qwerty");
        Config config = new Config(file.toString()).load();
        test(file, config, "my@gmail.com\n\nqwerty", "e-mail", "line", "password");
    }

    @Test
    public void whenPutExistKeyThenNewValueInFile() throws IOException {
        Path file = mkFile("e-mail=my@gmail.com", "line", "password=qwerty");
        Config config = new Config(file.toString()).load();
        config.put("password", "newPassword");
        config.save().load();
        test(file, config, "my@gmail.com\n\nnewPassword", "e-mail", "line", "password");
    }

    @Test
    public void whenPutEmptyValueThenEmptyValueInFile() throws IOException {
        Path file = mkFile("e-mail=my@gmail.com", "line", "password=qwerty");
        Config config = new Config(file.toString()).load();
        config.put("password", "");
        config.save().load();
        test(file, config, "my@gmail.com\n\n", "e-mail", "line", "password");
    }

    @Test
    public void whenPutNewKeyAndValueThenNewKeyAndValueInFile() throws IOException {
        Path file = mkFile("e-mail=my@gmail.com", "line", "password=qwerty");
        Config config = new Config(file.toString()).load();
        config.put("newKey", "newValue");
        config.save().load();
        test(file, config, "my@gmail.com\n\nqwerty\nnewValue", "e-mail", "line", "password", "newKey");
    }

    private void test(Path file, Config config, String is, String... res) throws IOException {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        Stream.of(res).forEach(line -> result.add(config.get(line)));
        Files.deleteIfExists(file);
        assertThat(result.toString(), is(is));
    }

    private Path mkFile(String... prop) throws IOException {
        String sep = System.getProperty("file.separator");
        Path file = Paths.get(String.format("%s%s%s.properties",
                System.getProperty("java.io.tmpdir"), sep, String.valueOf(System.currentTimeMillis())));
        Files.createFile(file);
        try (PrintWriter writer = new PrintWriter(file.toFile())) {
            Stream.of(prop).forEach(writer::println);
        }
        return file;
    }

}