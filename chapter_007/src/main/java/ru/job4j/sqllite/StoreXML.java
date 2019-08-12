package ru.job4j.sqllite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс для создания xml файла, из БД из таблицы entry.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StoreXML {
    /**
     * Логер.
     */
    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());
    /**
     * Файл, в который запишется xml.
     */
    private final File target;

    /**
     * Конструктор.
     * @param target - файл, в который запишется xml.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Создает контекст по двум классам Entries.class, Entry.class.
     * Маршализует их в XML.
     * @param list - список классов Entry, для создания структуры Entries.
     */
    public void save(List<Entry> list) {
        Entries entries = new Entries(list);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(this.target), true)) {
            JAXBContext context = JAXBContext.newInstance(Entries.class, Entry.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(entries, writer);
        } catch (FileNotFoundException | JAXBException e) {
            LOG.error("In save {}", e);
        }
    }
}
