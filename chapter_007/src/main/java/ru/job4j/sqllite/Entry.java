package ru.job4j.sqllite;

import javax.xml.bind.annotation.*;

/**
 * Содержит пару id - значение из БД.
 * Поддерживает структуру XML:
 * <entry>
 *    <id>1000001</id>
 *    <field>1</field>
 * </entry>
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@XmlRootElement (name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {

    public Entry() {

    }

    private Entry(EntryBuilder builder) {
        this.id = builder.id;
        this.field = builder.field;
    }

    /**
     * id в БД.
     */
    @XmlElement (name = "id")
    private long id;

    /**
     * Значение поля field из БД.
     */
    @XmlElement (name = "field")
    private int field;

    /**
     * Геттер для поля id.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Геттер для поля field.
     * @return
     */
    public int getField() {
        return field;
    }

    @Override
    public String toString() {
        return String.format("id = %s : field = %s", this.id, this.field);
    }

    /**
     * Builder.
     */
    public static class EntryBuilder {
        /**
         * id в БД.
         */
        private long id;
        /**
         * Значение поля field из БД.
         */
        private int field;

        /**
         * Сеттер билдера для поля id.
         * @param id - значение поля id.
         * @return
         */
        public EntryBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Сеттер билдера для поля field.
         * @param field - значение поля field.
         * @return
         */
        public EntryBuilder setField(int field) {
            this.field = field;
            return this;
        }

        public Entry build() {
            return new Entry(this);
        }
    }
}
