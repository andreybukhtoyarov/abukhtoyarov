package ru.job4j.archiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.search.SearchFileTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ZipArchiverTest {

    private SearchFileTest test;

    private final File rootDir = new File(String.format("%s/rootDir", System.getProperty("java.io.tmpdir")));

    private final String tmpOut = String.format("%s/archive", System.getProperty("java.io.tmpdir"));

    private List<File> expected;

    private PrintStream stdOut = System.out;

    private ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

    private File zip;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(byteOut));
    }

    @Before
    public void setFiles() throws IOException {
        test = new SearchFileTest();
        test.createFiles();
        expected = test.getFiles();
    }

    @After
    public void backOut() {
        System.setOut(this.stdOut);
    }

    @After
    public void deleteZip() {
        boolean bool = this.zip != null && this.zip.delete();
    }

    @Test
    public void whenZipArchiverArchiveThenCreateArchive() {
        String[] args = {"-e", "html", "-d", rootDir.getPath(), "ddd", "-o", tmpOut, "ooo"};
        new ZipArchiver(args).archive();
        zip = new File(String.format("%s.zip", tmpOut));
        assertThat(zip.exists(), is(true));
    }

    @Test
    public void whenZipArchiverArchiveThenGetFilesFromArchive() throws IOException {
        String[] args = {"-e", "html", "-d", rootDir.getPath(), "-o", tmpOut};
        new ZipArchiver(args).archive();
        zip = new File(String.format("%s.zip", tmpOut));
        ZipFile zipFile = new ZipFile(String.format("%s.zip", tmpOut));
        List<String> result = zipFile.stream().map(ZipEntry::getName).sorted().collect(Collectors.toList());
        List<String> expected = this.expected.stream().map(File::getPath).sorted().collect(Collectors.toList());
        assertThat(result.equals(expected), is(true));
    }

    @Test
    public void whenRootDirIsNotExistThenIllegalArgumentException() {
        String[] args = {"-e", "html", "-d", "/krakoziabra", "-o", tmpOut};
        new ZipArchiver(args).archive();
        assertThat(byteOut.toString(), is("Wrong way specified\n"));
    }

    @Test
    public void whenOutputWrongWayThenIllegalArgumentException() {
        String out = String.format("%s/krakoziabra/archive", tmpOut);
        String[] args = {"-e", "html", "-d", rootDir.getPath(), "-o", out};
        new ZipArchiver(args).archive();
        assertThat(byteOut.toString(), is("Output directory is not exist\n"));
    }

    @Test
    public void whenOutputFileNotHaveNameButHavePatchThenHisNameIsRootDirName() {
        String out = String.format("%s/", System.getProperty("java.io.tmpdir"));
        String[] args = {"-e", "html", "-d", rootDir.getPath(), "-o", out};
        new ZipArchiver(args).archive();
        zip = new File(String.format("%srootDir.zip", out));
        assertThat(zip.exists(), is(true));
    }

    @Test
    public void whenOutputNotSetThenOutputIsRootDir() {
        String[] args = {"-e", "html", "-d", rootDir.getPath()};
        new ZipArchiver(args).archive();
        zip = new File(String.format("%s/rootDir.zip", rootDir.getPath()));
        assertThat(zip.exists(), is(true));
    }
}
