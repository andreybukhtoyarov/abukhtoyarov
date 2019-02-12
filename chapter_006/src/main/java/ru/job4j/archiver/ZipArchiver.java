package ru.job4j.archiver;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.job4j.search.SearchFile;

/**
 * Zip archivator.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ZipArchiver {
    /**
     * Args.
     */
    private final Args args;
    /**
     * Files to archiving.
     */
    private List<File> files;
    /**
     * List exclude.
     */
    private List<String> exclude;
    /**
     * File root directory to archiving.
     */
    private File rootDir;
    /**
     * File out put directory to archiving.
     */
    private File output;

    public ZipArchiver(String[] args) {
        this.args = new Args(args);
    }

    /**
     * Filling exclude, rootDir, output.
     */
    private void getArgs() {
        this.exclude = args.exclude();
        this.rootDir = new File(args.directory());
        this.output = new File(String.format("%s.zip", args.output()));
    }

    /**
     * If the source directory does not exist, an exception will be thrown.
     * If the path to the output directory is not correct, an exception will be thrown.
     * If a directory is specified, but the name is not specified,
     * the file will be given a new name.
     * If the output field is empty - the file will be saved in the folder root.
     */
    private void validate() {
        if (!this.rootDir.exists()) {
            throw new IllegalArgumentException("Wrong way specified");
        }
        if (this.output.getParent() != null && !new File(this.output.getParent()).exists()) {
            throw new IllegalArgumentException("Output directory is not exist");
        }
        if (".zip".equals(this.output.getName())
                && this.output.getParent() != null && new File(this.output.getParent()).exists()) {
            this.output = new File(this.output.getParent(), String.format("%s.zip", this.rootDir.getName()));
        } else if (".zip".equals(this.output.getName())) {
            this.output = new File(this.rootDir, String.format("%s.zip", this.rootDir.getName()));
        }
    }

    /**
     * Get files to archiving.
     */
    private void fillFiles() {
        List<File> allFiles = new SearchFile().files(this.rootDir.getPath(), null);
        this.files = allFiles.stream().filter(file -> exclude.stream().noneMatch(
                extension -> file.getName().endsWith(String.format(".%s", extension))
        )).collect(Collectors.toList());
    }

    /**
     * Write file to zip.
     * @param file file to write.
     * @param zipOut ZipOutputStream.
     */
    private void readAndWrite(File file, ZipOutputStream zipOut) {
        try (InputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
            int length = fileInput.available();
            if (length == 0) {
                length = 1024;
            }
            byte[] buffer = new byte[length];
            while ((fileInput.read(buffer)) != -1) {
                zipOut.write(buffer);
            }
        } catch (IOException ioe) {
            try (PrintWriter pw = new PrintWriter(System.out, true)) {
                pw.println(ioe.getMessage());
            }
        }
    }

    /**
     * Archive.
     */
    public void archive() {
        getArgs();
        try {
            validate();
            fillFiles();
            try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(this.output))) {
                this.files.forEach(file -> {
                    try {
                        zipOut.putNextEntry(new ZipEntry(file.getPath()));
                        readAndWrite(file, zipOut);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            try (PrintWriter pw = new PrintWriter(System.out, true)) {
                pw.println(e.getMessage());
            }
        }
    }
}
