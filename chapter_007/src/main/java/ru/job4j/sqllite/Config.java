package ru.job4j.sqllite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс-обертка над классом Properties.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Config {
    /**
     * Поле с объектом Properties.
     */
    private final Properties values = new Properties();
    /**
     * Поле хранит название с файлом настроек.
     */
    private final String propFile;

    /**
     * Конструктор. При создании указывается файл с настройками.
     * @param propFile - название файла с настройками.
     */
    public Config(String propFile) {
        this.propFile = propFile;
    }

    /**
     * Загружает настройки из файла с настройками.
     * @return - this.
     */
    public Config init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(this.propFile)) {
            this.values.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Взять параметр по ключу.
     * @param key - ключ параметра.
     * @return - параметр.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}
