package ru.job4j.sqllite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * Класс парсит XML, полученную из класса ConvertXSQT, складывает значения из атрибутов field
 * и выводит полученную сумму на печать.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SAXParserForStoreXML {
    /**
     * XML файл для парсинга.
     */
    private final File xml;
    /**
     * Поле с суммой field.
     */
    private long sum = 0L;
    /**
     * OutputStream.
     */
    private final OutputStream out;
    /**
     * Логер.
     */
    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());

    public SAXParserForStoreXML(File xml, OutputStream out) {
        this.xml = xml;
        this.out = out;
    }

    /**
     * Обработчик для SAX, с переопределнным методом startElement().
     * Метод находит элемент entry, берет значение атрибута field и прибавляет его к общей сумме.
     */
    private final DefaultHandler handler = new DefaultHandler() {
        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) {
            if ("entry".equals(qName)) {
                sum += Integer.valueOf(attributes.getValue("field"));
            }
        }
    };

    /**
     * Выводит сумму на печать в this.out.
     */
    private void print() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.out));
            writer.write(String.valueOf(this.sum));
            writer.flush();
        } catch (IOException e) {
            LOG.error("In print {}", e);
        }
    }

    /**
     * Запускает парсинг
     */
    private void parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(this.xml, this.handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("In parse {}", e);
        }
    }

    /**
     * Старт.
     */
    public void init() {
        parse();
        print();
    }
}
