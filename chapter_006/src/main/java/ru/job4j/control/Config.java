package ru.job4j.control;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Config for file properties.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Config {
    /**
     * Path to config file.
     */
    private final String path;
    /**
     * Separator.
     */
    private static final String SP = "=";
    /**
     * Properties.
     */
    private final Map<String, String> properties = new LinkedHashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Load properties from properties file.
     * @return this.
     */
    public Config load() {
        this.properties.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(line -> {
                if (line.contains(SP)) {
                    int position = line.indexOf(SP);
                    properties.put(line.substring(0, position), line.substring(position + 1));
                } else {
                    properties.put(line, "");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Save change to properties file.
     * @return this.
     */
    public Config save() {
        try (PrintWriter writer = new PrintWriter(this.path)) {
            this.properties.forEach((key, value) -> {
                writer.append(key);
                if (!value.isEmpty()) {
                    writer.append(SP).append(value);
                }
                writer.println();
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Get value from property.
     * @param key key.
     * @return value.
     */
    public String get(String key) {
        return this.properties.get(key);
    }

    /**
     * Put property.
     * @param key key.
     * @param value value.
     */
    public void put(String key, String value) {
        this.properties.put(key, value);
    }
}
