package ru.job4j.sqllite;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Хранит список записей данных из таблицы entry.
 * Поддерживает структуру XML:
 * <entries>
 *   <entry>
 *      *****
 *   </entry>
 *   **********
 *   <entry>
 *      *****
 *   </entry>
 * <entries/>
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@XmlRootElement (name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

    /**
     * Хранит записи из таблицы entry.
     */
    @XmlElement(name = "entry")
    private List<Entry> entries;

    public Entries() {

    }

    /**
     * Конструктор с параметром, создает объект со списком данных из таблицы entry.
     * @param entries - список с данными из таблицы entry.
     */
    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Геттер списка записей из таблицы entry.
     * @return - список с данными из таблицы entry.
     */
    public List<Entry> getEntries() {
        return entries != null ? entries : new ArrayList<>();
    }

    /**
     * Сеттер для поля, хранящего список с данными из таблицы entry.
     * @param entries - список с данными из таблицы entry.
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
