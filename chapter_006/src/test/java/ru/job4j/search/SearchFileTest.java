package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static java.lang.String.format;

public class SearchFileTest {

    private final SearchFile search = new SearchFile();
    private final List<String> exts = new ArrayList<>(Arrays.asList("txt", "jpg"));

    private ArrayList<File> folders;
    private ArrayList<File> files;
    private List<File> expected;

    @Before
    public void createFiles() throws IOException {
        folders = new ArrayList<>();
        folders.add(new File(String.format("%s/rootDir", System.getProperty("java.io.tmpdir"))));
        folders.get(0).mkdir();
        folders.get(0).deleteOnExit();
        files = new ArrayList<>();
        expected = new ArrayList<>();
        for (int iteration = 0; iteration < 6; ++iteration) {
            for (int innerIter = 0; innerIter < 3; ++innerIter) {
                File dir = new File(folders.get(iteration), format("folder%s", innerIter));
                dir.mkdir();
                dir.deleteOnExit();
                folders.add(dir);
            }
            for (int innerIter = 0; innerIter < 3; ++innerIter) {
                File txt = new File(folders.get(iteration), format("txtFile%s.txt", innerIter));
                txt.createNewFile();
                txt.deleteOnExit();
                expected.add(txt);
            }
            for (int innerIter = 0; innerIter < 3; ++innerIter) {
                File html = new File(folders.get(iteration), format("htmlFile%s.html", innerIter));
                html.createNewFile();
                html.deleteOnExit();
                files.add(html);
            }
            for (int innerIter = 0; innerIter < 3; ++innerIter) {
                File jpg = new File(folders.get(iteration), format("jpgFile%s.jpg", innerIter));
                jpg.createNewFile();
                jpg.deleteOnExit();
                expected.add(jpg);
            }
        }
    }

    @Test
    public void whenSearchThen36Files() {
        List<File> result = search.files(format("%s/rootDir", System.getProperty("java.io.tmpdir")), exts);
        assertThat(result.size() == 36, is(true));
    }

    @Test
    public void whenSearchThenListResultEqualsListExpected() {
        List<File> result = new ArrayList<>(
                search.files(format("%s/rootDir", System.getProperty("java.io.tmpdir")), exts)
        );
        Collections.sort(result);
        Collections.sort(expected);
        assertThat(result.equals(expected), is(true));
    }
}
