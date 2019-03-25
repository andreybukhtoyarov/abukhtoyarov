package ru.job4j.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.search.SearchFileTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileScannerTest {

    private final String tmp = System.getProperty("java.io.tmpdir");

    private final SearchFileTest sft = new SearchFileTest();

    private final Path log = Paths.get(String.format("%s/rootDir/log.txt", this.tmp));

    private ByteArrayOutputStream out = new ByteArrayOutputStream();


    @Before
    public void setFiles() throws IOException {
        sft.createFiles();
    }

    @Before
    public void setOut() {
        this.out = new ByteArrayOutputStream();
    }

    @After
    public void delLog() throws IOException {
        if (Files.exists(this.log)) {
            Files.delete(this.log);
        }
    }

    private String readFileLog() throws Exception {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(String.format("%s/rootDir/log.txt", this.tmp))
        )) {
            reader.lines().forEach(result::add);
        }
        Collections.sort(result);
        result.forEach(sj::add);
        return sj.toString();
    }

    private void executeTest(String[] args, StringJoiner expected) throws Exception {
        new FileScanner(this.out, args).start();
        String result = readFileLog();
        assertThat(result, is(expected.toString()));
    }

    private void executeExceptionTest(String[] args, StringJoiner expected) {
        new FileScanner(this.out, args).start();
        assertThat(this.out.toString(), is(expected.toString()));
    }

    @Test
    public void whenSearchByFullNameThenOk() throws Exception {
        executeTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "htmlFile2.html",
                        "-f",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("/tmp/rootDir/folder0/folder0/htmlFile2.html")
                        .add("/tmp/rootDir/folder0/folder1/htmlFile2.html")
                        .add("/tmp/rootDir/folder0/htmlFile2.html")
                        .add("/tmp/rootDir/folder1/htmlFile2.html")
                        .add("/tmp/rootDir/folder2/htmlFile2.html")
                        .add("/tmp/rootDir/htmlFile2.html")
        );
    }

    @Test
    public void whenSearchByMaskThenOk() throws Exception {
        executeTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "*mlFile2.ht*",
                        "-m",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("/tmp/rootDir/folder0/folder0/htmlFile2.html")
                        .add("/tmp/rootDir/folder0/folder1/htmlFile2.html")
                        .add("/tmp/rootDir/folder0/htmlFile2.html")
                        .add("/tmp/rootDir/folder1/htmlFile2.html")
                        .add("/tmp/rootDir/folder2/htmlFile2.html")
                        .add("/tmp/rootDir/htmlFile2.html")
        );
    }

    @Test
    public void whenStartDirIsNotDirectoryThenWrongStartPath() {
        executeExceptionTest(
                new String[] {
                        "-d", "fff",
                        "-n", "htmlFile2.html",
                        "-m",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("wrong start path").add("")
        );
    }

    @Test
    public void whenFileNameNotSetThenNameNotSpecified() {
        executeExceptionTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "",
                        "-m",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("name not specified").add("")
        );
    }

    @Test
    public void whenGetByNotSetThenNameNotSpecified() {
        executeExceptionTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "fff",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("no search method selected").add("")
        );
    }

    @Test
    public void whenPathToFileLogGoToFileThenAlreadyExistThenFileLogAlreadyExist() throws IOException {
        Files.createFile(this.log);
        executeExceptionTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "fff",
                        "-m",
                        "-o", String.format("%s/rootDir/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("wrong log path, or a file with that name already exists").add("")
        );
    }

    @Test
    public void whenPathToFileLogWrongThenWrongLogPath() {
        executeExceptionTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "fff",
                        "-m",
                        "-o", String.format("%s/rootDiyyyr/log.txt", this.tmp)
                },
                new StringJoiner(System.lineSeparator())
                        .add("wrong log path, or a file with that name already exists").add("")
        );
    }

    @Test
    public void whenHelpThenHelp() {
        executeExceptionTest(
                new String[] {
                        "-d", String.format("%s/rootDir", this.tmp),
                        "-n", "*mlFile2.ht*",
                        "-help"
                },
                new StringJoiner(System.lineSeparator())
                        .add("To find a file or folder, enter a command like:")
                        .add("-d path to start search -n file name -m / -f type of search by mask / full name -o path to save the result")
                        .add("all parts of the team separated by spaces.")
                        .add("-help to get help")
                        .add("")
        );
    }

}